package com.krugger.challenge.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
@EnableConfigurationProperties
public class WebSecurityConfig implements SecurityFilterChain {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return false;
    }

    @Override
    public List<Filter> getFilters() {
        return new ArrayList<>();
    }
}
