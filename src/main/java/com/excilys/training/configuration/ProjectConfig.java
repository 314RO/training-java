package com.excilys.training.configuration;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.excilys.training.persistence.SQLConnection;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.excilys.training")
@Configuration
public class ProjectConfig {
    
        @Bean
        public HikariDataSource dataSource() {
        String url=null;
        String user=null;
        String passwd=null;
        System.out.println("coucou");
        try {
          Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
        ResourceBundle rb = ResourceBundle.getBundle("connection");
        url = rb.getString("url");
        user = rb.getString("user");
        passwd = rb.getString("passwd");
        System.out.println(user);
               
        HikariConfig config = new HikariConfig();
        
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(passwd);   
        config.setMaximumPoolSize(25);        
        return new HikariDataSource(config);
        }
        catch (MissingResourceException e) {
            e.printStackTrace();
            return null;
    }
}
    

    
    
    
    
    
   
    
    

}
