package com.excilys.training.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public enum SQLConnection {
    INSTANCE;

   
    private static HikariDataSource dataSource;
    
   
       
    
    /**
     * Constructeur par défaut de la classe.
     */
    static {
        String url=null;
        String user=null;
        String passwd=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
        ResourceBundle rb = ResourceBundle.getBundle("connection");
        url = rb.getString("url");
        user = rb.getString("user");
        passwd = rb.getString("passwd");
        System.out.println(url);
        System.out.println(user);
        System.out.println(passwd);
        
        
        HikariConfig config = new HikariConfig();
        
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(passwd);   
        config.setMaximumPoolSize(25);        
        dataSource = new HikariDataSource(config);
        }
        catch (Exception e) {
            e.printStackTrace();
    }
        
        
    }

    /**
     * Crée la connection si elle n'existe pas.
     * @return Connection
     * @throws SQLException 
     */
    public  Connection getInstance() throws SQLException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }  
    }

}