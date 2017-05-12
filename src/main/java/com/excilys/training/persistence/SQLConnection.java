package com.excilys.training.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.excilys.training.configuration.ProjectConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class SQLConnection {
    
    @Autowired 
    private HikariDataSource dataSource;

    /**
     * Constructeur par défaut de la classe.
     */
    public SQLConnection(){}
    
  /*  static {
        ApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        dataSource = (HikariDataSource) context.getBean("dataSource");
   */
    /**
     * Crée la connection si elle n'existe pas.
     * @return Connection
     * @throws SQLException 
     */
    public Connection getInstance() throws SQLException {
        try {            
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }  
    }

}