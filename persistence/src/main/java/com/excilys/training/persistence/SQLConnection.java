package com.excilys.training.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SQLConnection {
    
    @Autowired
    private DataSource dataSource;

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