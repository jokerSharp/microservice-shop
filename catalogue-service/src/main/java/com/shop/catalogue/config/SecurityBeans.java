package com.shop.catalogue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityBeans {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(autorizeHttpRequests ->
                        autorizeHttpRequests.requestMatchers("/catalogue-api/**")
                                .hasRole("ADMIN"))
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(sessionManaegment -> sessionManaegment
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
