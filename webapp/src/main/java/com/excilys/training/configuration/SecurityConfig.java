package com.excilys.training.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("1234").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("1234").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("dba").password("1234").roles("DBA");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/dashboard")
                .access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA') or hasRole('ROLE_USER')")
                .antMatchers("/editComputer").access("hasRole('ROLE_ADMIN')").antMatchers("/addComputer")
                .access("hasRole('ROLE_ADMIN')").and().formLogin();

    }
}