package com.krugger.challenge.config;

import com.krugger.challenge.auth.JWTAuthenticationFilter;
import com.krugger.challenge.auth.KruggerAuthenticationManager;
import com.krugger.challenge.auth.KruggerChallengeUserDetails;
import com.krugger.challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig  {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, PasswordEncoder bCryptPasswordEncoder) throws Exception {
        http.authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/employee/**").hasAnyAuthority("Employee", "Administrator")
                .antMatchers("/app/**").hasAnyAuthority("Administrator")
                .anyRequest().authenticated()
                .and()
                .logout().permitAll();

        http.headers().frameOptions().sameOrigin();
        http.authenticationProvider(authenticationProvider(bCryptPasswordEncoder));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(PasswordEncoder bCryptPasswordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService(bCryptPasswordEncoder));
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder bCryptPasswordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        List<com.krugger.challenge.entity.User> users = userRepository.findAll();
        users.stream().filter(user -> user.getEmployee().getActive()).forEach(user -> user.getRoles().forEach(role ->
                manager.createUser(User.withUsername(user.getUserName())
                .password(user.getPassword())
                .roles(role.getName())
                .build())));
        return manager;
    }


    @Bean
    JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter(this.kruggerAuthenticationManager());
    }

    @Bean
    KruggerAuthenticationManager kruggerAuthenticationManager() {
        return new KruggerAuthenticationManager();
    }

    @Bean
    KruggerChallengeUserDetails userDetails() {
        return new KruggerChallengeUserDetails();
    }


}