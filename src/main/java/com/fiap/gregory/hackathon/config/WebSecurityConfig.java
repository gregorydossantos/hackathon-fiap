package com.fiap.gregory.hackathon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static com.fiap.gregory.hackathon.rest.path.Routes.PATH_EXCHANGES;
import static com.fiap.gregory.hackathon.rest.path.Routes.PATH_GAMES;
import static com.fiap.gregory.hackathon.rest.path.Routes.PATH_USERS;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private static final String PATH_USERS_ID = PATH_USERS + "/**";
    private static final String PATH_GAME_ID = PATH_GAMES + "/**";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                //.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                //.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/v3/**", "/swagger-ui/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers(HttpMethod.GET, PATH_USERS).permitAll()
                        .requestMatchers(HttpMethod.POST, PATH_USERS).permitAll()
                        .requestMatchers(HttpMethod.POST, PATH_USERS_ID).permitAll()
                        .requestMatchers(HttpMethod.DELETE, PATH_USERS_ID).permitAll()
                        .requestMatchers(HttpMethod.GET, PATH_GAMES).permitAll()
                        .requestMatchers(HttpMethod.POST, PATH_GAMES).permitAll()
                        .requestMatchers(HttpMethod.DELETE, PATH_GAME_ID).permitAll()
                        .requestMatchers(HttpMethod.POST, PATH_EXCHANGES).permitAll()
                        .anyRequest().authenticated())
                .build();
    }

}
