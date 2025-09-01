package com.course.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.*;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableConfigurationProperties(CrossOriginProperties.class)
public class CrossOriginConfig {

    @Autowired
    private CrossOriginProperties crossOriginProperties;

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOriginPatterns(crossOriginProperties.getAllowedOrigins());
//        config.setAllowedOriginPatterns(Arrays.asList("*"));
//        config.setAllowCredentials(true);	
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
