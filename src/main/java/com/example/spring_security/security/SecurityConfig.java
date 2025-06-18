package com.example.spring_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/permitted").permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(configurer -> configurer
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                        .accessDeniedHandler(new CustomAccessDeniedHandler())
                );

        return http.build();
    }

//    @Bean
//    public RoleHierarchy roleHierarchy() {
//        return RoleHierarchyImpl.withDefaultRolePrefix()
//                .role("ADMIN").implies("MEMBER")
//                .role("MEMBER").implies("GUEST")
//                .role("ANONYMOUS").implies("GUEST")
//                .build();
//    }
}
