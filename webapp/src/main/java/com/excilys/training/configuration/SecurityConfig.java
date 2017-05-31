package com.excilys.training.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource datasource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

      auth.jdbcAuthentication().dataSource(datasource)
        .usersByUsernameQuery(
            "select name,password,enabled from user where name=?")
        .authoritiesByUsernameQuery(
            "select name, role from user where name=?");
    }
    
    
    
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/fonts/**");
      }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/dashboard").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA') or hasRole('ROLE_USER')")
                .antMatchers("/editComputer").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/addComputer").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/computer").permitAll()
                .and().formLogin().loginPage("/login");
        http.csrf().ignoringAntMatchers("/computer/**");
     
        http.logout()                                                             
        .logoutUrl("/dashboard?logout")                                                
        .logoutSuccessUrl("/dashboard?logout")  // valeur par défaut                                
        .invalidateHttpSession(true) // par défaut
        .clearAuthentication(true).deleteCookies("JSESSIONID");
        
        
    }
}