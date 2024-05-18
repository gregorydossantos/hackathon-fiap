package com.fiap.gregory.hackathon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static com.fiap.gregory.hackathon.rest.path.Routes.PATH_GAMES;
import static com.fiap.gregory.hackathon.rest.path.Routes.PATH_USERS;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, PATH_USERS).permitAll()
                        .requestMatchers(HttpMethod.POST, PATH_USERS).permitAll()
                        .requestMatchers(HttpMethod.PUT, "/v1/users/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/v1/users/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, PATH_GAMES).permitAll()
                        .anyRequest().authenticated())
                .build();
    }

}
