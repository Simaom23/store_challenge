package com.simaom23.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/h2-console/**").and()
                .authorizeRequests()
                .mvcMatchers("/prices").permitAll()
                .and().authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .and().httpBasic();
        http.headers().frameOptions().disable();
        return http.build();
    }
}
