package com.qpassessment.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends GlobalMethodSecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       return http.authorizeRequests(authorizeRequests ->
                authorizeRequests.requestMatchers("/grocery/item/**").hasAuthority("ADMIN").
               anyRequest().
                        authenticated()).authorizeHttpRequests(authorizeRequests ->
               authorizeRequests.requestMatchers("/grocery/list/**").hasAnyAuthority("ADMIN","USER").
                       anyRequest().
                       authenticated()).build();
    }

}
