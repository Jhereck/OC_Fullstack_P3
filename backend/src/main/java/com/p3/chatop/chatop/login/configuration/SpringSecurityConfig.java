package com.p3.chatop.chatop.login.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import io.swagger.v3.oas.models.PathItem.HttpMethod;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().sameOrigin().and()
                .authorizeHttpRequests()
                // authorize.anyRequest().authenticated()
                .requestMatchers("/**")
                .permitAll()
                .requestMatchers("/api/**").permitAll()
                .anyRequest().authenticated();
        return http.build();
    }

    /*
     * @Bean
     * public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     * 
     * http
     * .httpBasic();
     * 
     * .and()
     * .authorizeHttpRequests()
     * .requestMatchers("/index.html", "/home", "/login").permitAll()
     * .requestMatchers("/admin").hasRole("ADMIN")
     * .requestMatchers("/user").hasRole("USER")
     * .anyRequest().authenticated()
     * .and()
     * .formLogin();
     * 
     * return http.build();
     * 
     * }
     */

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("pass")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("pass")
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    /*
     * @Bean
     * public PasswordEncoder passwordEncoder() {
     * return new BCryptPasswordEncoder();
     * }
     * 
     */

}
