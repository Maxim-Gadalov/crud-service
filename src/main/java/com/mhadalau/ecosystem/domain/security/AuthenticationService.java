package com.mhadalau.ecosystem.domain.security;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthenticationService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationService.class);

    private static final String INTERNAL_SERVICE_PATTERN = "http://%s:%s";

    private EurekaClient eurekaClient;
    private RestTemplate restTemplate;
    private String verifyTokenPath;

    @Autowired
    public AuthenticationService(@Qualifier("eurekaClient") EurekaClient eurekaClient, RestTemplate restTemplate,
                                 @Value("${security.verify.token.path}") String verifyTokenPath) {
        this.eurekaClient = eurekaClient;
        this.restTemplate = restTemplate;
        this.verifyTokenPath = verifyTokenPath;
    }

    public boolean isAuthenticated(HttpServletRequest request){
        boolean isAuthenticated;
        String authorizationHeaderValue = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(StringUtils.hasText(authorizationHeaderValue)){

            InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("security-service", false);

            String hostName = instanceInfo.getHostName();
            Integer port = instanceInfo.getPort();

            String verifyTokenUrl = String.format(INTERNAL_SERVICE_PATTERN, hostName, port) + verifyTokenPath;

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", authorizationHeaderValue);

            HttpEntity<String> httpEntity = new HttpEntity<>(headers);

            ResponseEntity<String> response = null;

            try {
                response = restTemplate.exchange(verifyTokenUrl, HttpMethod.GET, httpEntity, String.class);
            } catch (Exception ex){
                LOG.error(ex.getMessage(), ex);
            }

            if (response != null && response.getStatusCode().is2xxSuccessful()){
                isAuthenticated = true;
            } else {
                isAuthenticated = false;
            }

        } else {
            isAuthenticated = false;
        }

        return isAuthenticated;
    }
}
