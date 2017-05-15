package com.excilys.training.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;


@Repository

public class SQLConnection {
    
    @Autowired
    private DataSource dataSource;

   
    

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