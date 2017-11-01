package com.fm.school.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by fadlymunandar on 10/18/17.
 */

@Configuration
@EnableWebMvc
@ComponentScan("com.fm.school.web")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}
