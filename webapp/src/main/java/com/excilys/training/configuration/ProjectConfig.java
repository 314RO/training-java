package com.excilys.training.configuration;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mysql.cj.jdbc.MysqlDataSource;

import org.springframework.context.annotation.ComponentScan;

@Configuration
@ComponentScan(basePackages = "com.excilys.training")
@EnableTransactionManagement
public class ProjectConfig {

    @Bean
    public DataSource dataSource() {
        String url = null;
        String user = null;
        String passwd = null;

        try {
            ResourceBundle rb = ResourceBundle.getBundle("connection");

            url = rb.getString("url");
            user = rb.getString("user");
            passwd = rb.getString("passwd");

            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUrl(url);
            dataSource.setUser(user);
            dataSource.setPassword(passwd);
            return dataSource;
            
        }   catch (MissingResourceException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    @Bean
    public PlatformTransactionManager transactionManager(){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }
    
    
    
    
    

}