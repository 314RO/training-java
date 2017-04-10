package com.excilys.training.persistence;

import java.sql.*;

public class Connect {
public static void main(String[] args) {      
  try {
    Class.forName("com.mysql.jdbc.Driver");

    String url = "jdbc:mysql://127.0.0.1:3306/computer-database-db";
    String user = "root";
    String passwd = "";

    Connection conn = DriverManager.getConnection(url, user, passwd);
    System.out.println("Connexion effective !");         
       
  } catch (Exception e) {
    e.printStackTrace();
  }      
}
}