package com.beerlot.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //http.addFilterAfter(jwtAuthenticationFilter, LogoutFilter.class);
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/**").permitAll()
                //.and()
                //.oauth2ResourceServer().jwt()
                .and();

        return http.build();
    }

}
