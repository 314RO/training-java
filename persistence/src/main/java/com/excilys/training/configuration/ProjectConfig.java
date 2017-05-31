package com.excilys.training.configuration;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

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

            config.setMaximumPoolSize(10);
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

            HikariDataSource ds = new HikariDataSource(config);
            return ds;

        } catch (MissingResourceException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

}
