package com.org.mortgage.loans.config;

import com.org.mortgage.loans.model.DocumentationProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurator {

    @Value("${mortgage.loans.service.security.unrestricted.paths}")
    private String[] unrestrictedPaths;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http, DocumentationProperties documentationProperties) throws Exception {
        http.cors().and().cors().disable().authorizeHttpRequests((requests) -> requests
                        .requestMatchers(unrestrictedPaths).permitAll()
                        .anyRequest()
                        .permitAll())
                .httpBasic();
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().anyRequest();
    }

}
