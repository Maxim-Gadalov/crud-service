package com.mhadalau.ecosystem.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Import({
        SwaggerConfig.class,
        RestTemplateConfig.class,
        InterceptorConfig.class
})
@PropertySources({
        @PropertySource("classpath:swagger.properties")
})
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
