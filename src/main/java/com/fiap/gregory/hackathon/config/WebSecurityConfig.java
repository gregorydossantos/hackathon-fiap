package com.fiap.gregory.hackathon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static com.fiap.gregory.hackathon.rest.path.Routes.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private static final String PATH_USERS_ID = PATH_USERS + "/**";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/v3/**", "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.GET, PATH_USERS).permitAll()
                        .requestMatchers(HttpMethod.GET, PATH_GAMES).permitAll()
                        .requestMatchers(HttpMethod.POST, PATH_USERS).permitAll()
                        .requestMatchers(HttpMethod.POST, PATH_USERS_ID).permitAll()
                        .requestMatchers(HttpMethod.POST, PATH_EXCHANGES).permitAll()
                        .requestMatchers(HttpMethod.DELETE, PATH_USERS_ID).permitAll()
                        .anyRequest().authenticated())
                .build();
    }

}
