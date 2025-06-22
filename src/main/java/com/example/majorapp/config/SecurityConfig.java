package com.example.majorapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())               // CSRF 비활성
                .authorizeHttpRequests(auth ->
                        auth.anyRequest().permitAll()            // 모든 요청 인증 없이 허용
                )
                .httpBasic(Customizer.withDefaults());       // 기본 HTTP Basic 필터만 등록
        return http.build();
    }
}
