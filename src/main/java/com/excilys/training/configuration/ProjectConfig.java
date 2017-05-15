package com.excilys.training.configuration;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@ComponentScan(basePackages = "com.excilys.training")

public class ProjectConfig {
    
        @Bean
        public DataSource dataSource() {
        String url=null;
        String user=null;
        String passwd=null;
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
               
        HikariConfig config = new HikariConfig();
        
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(passwd);   
        config.setMaximumPoolSize(25);      
        System.out.println("ça marche?");
        System.out.println(new HikariDataSource(config));
        return new HikariDataSource(config);
        }
        catch (MissingResourceException e) {
            e.printStackTrace();
            return null;
    }
}
    
        

}
