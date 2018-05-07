package com.mhadalau.ecosystem.domain;

import com.mhadalau.ecosystem.domain.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
@Import(AppConfig.class)
public class ApplicationStarter {

    public static void main(String...args){
        SpringApplication.run(ApplicationStarter.class, args);
    }
}
