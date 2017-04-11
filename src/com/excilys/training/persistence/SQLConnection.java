package com.excilys.training.persistence;

import java.sql.*;

public class SQLConnection {

	private String url = "jdbc:mysql://127.0.0.1:3306/computer-database-db?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
	private String user = "root";
	private String passwd = "";
	private static Connection connect;

	//Constructeur privé pour le design pattern Singleton
	private SQLConnection(){
		try {
			connect = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Méthode qui va nous retourner notre instance et la créer si elle n'existe pas
	public static Connection getInstance(){
		if(connect == null){
			new SQLConnection();
			System.out.println("INSTANCIATION DE LA CONNEXION SQL ! ");
		}
		else{
			System.out.println("CONNEXION SQL EXISTANTE ! ");
		}
		return connect;   
	}
}

