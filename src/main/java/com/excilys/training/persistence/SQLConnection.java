package com.excilys.training.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public enum SQLConnection {
    INSTANCE;

    private static String url;
    private static String user;
    private static String passwd;
    private static Connection connect;

    static {
        ResourceBundle rb = ResourceBundle.getBundle("connection");
        url = rb.getString("url");
        user = rb.getString("user");
        passwd = rb.getString("passwd");
    }
    /**
     * Constructeur par défaut de la classe.
     */
    private SQLConnection() {
    }

    /**
     * Crée la connection si elle n'existe pas.
     * @return Connection
     */
    public static Connection getInstance() {
        try {
            connect = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return connect;
    }

}