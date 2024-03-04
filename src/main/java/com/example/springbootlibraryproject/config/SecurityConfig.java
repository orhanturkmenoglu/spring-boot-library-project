package com.example.springbootlibraryproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_name , password , active from users where user_name=?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_name , role  from roles where user_name = ?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(configurer ->
                configurer
                        .antMatchers(HttpMethod.POST, "api/v1/books/createBook").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "api/v1/books/booksAll").hasRole("USER")
                        .antMatchers(HttpMethod.GET, "api/v1/books/getBooksCreationDateAsc").hasRole("USER")
                        .antMatchers(HttpMethod.GET, "api/v1/books/getBooksCreationDateDesc").hasRole("USER")
                        .antMatchers(HttpMethod.GET, "api/v1/books/getBooksByName").hasRole("USER")
                        .antMatchers(HttpMethod.GET, "api/v1/books/getBooksAmountOfStockOrderByAsc").hasRole("USER")
                        .antMatchers(HttpMethod.GET, "api/v1/books/getBooksAmountOfStockOrderByDesc").hasRole("USER")
                        .antMatchers(HttpMethod.GET, "api/v1/books/getBooksAmountOfStockOrderByDesc").hasRole("USER")
                        .antMatchers(HttpMethod.DELETE, "api/v1/books/deleteBook/**").hasRole("USER")
                        .antMatchers(HttpMethod.PUT, "api/v1/books/updateBook").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/api/v1/").hasRole("ADMIN").anyRequest().permitAll()
        );

        // use HTTP Basic Authentication
        httpSecurity.httpBasic(Customizer.withDefaults());

        // disable Cross Site Request Forgery (CSFR)
        // in general not required for stateles rest apıs that use post,
        // put delete and/or patch

        httpSecurity.csrf(csfr -> csfr.disable());


        return httpSecurity.build();
    }

}

