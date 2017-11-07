package com.fm.school.config.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * Created by fadlymunandar on 11/7/17.
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private static final String RESOURCE_ID = "school_api";

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.anonymous().disable()
                .requestMatchers().antMatchers("/api/v1/**")
                .and()
                .authorizeRequests().antMatchers("/api/v1/**").access("hasRole('ADMIN')")
                .and()
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //super.configure(resources);
        resources.resourceId(RESOURCE_ID).stateless(true);
    }
}