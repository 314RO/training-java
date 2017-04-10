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


	//Méthode pour afficher toute la table Computer/company
	// La gestion de l'exception s!=company || computer se fera dans la partie ui/service?
	public static void print(String s){ 
		try {

			//Création d'un objet Statement
			Statement state = connect.createStatement();
			//L'objet ResultSet contient le résultat de la requête SQL
			ResultSet result = state.executeQuery("SELECT * FROM "+s);
			//On récupère les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();

			System.out.println("\n**********************************");
			//On affiche le nom des colonnes
			for(int i = 1; i <= resultMeta.getColumnCount(); i++)
				System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");

			System.out.println("\n**********************************");


			while(result.next()){         
				for(int i = 1; i <= resultMeta.getColumnCount(); i++){
					if(result.getObject(i)!=null)
						System.out.print("\t" + result.getObject(i).toString() + "\t |");
					else 
						System.out.print("\t ***** \t |");
				}

				System.out.println("\n---------------------------------");

			}
			result.close();
			state.close();

		} catch (Exception e) {
			e.printStackTrace();
		} 

	}

	// Afficher les infos d'un seul computer
	public static void printComputer(int index){ 
		try {

			//Création d'un objet Statement
			Statement state = connect.createStatement();
			//L'objet ResultSet contient le résultat de la requête SQL
			ResultSet result = state.executeQuery("SELECT * FROM computer WHERE id="+index);
			//On récupère les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();

			System.out.println("\n**********************************");
			//On affiche le nom des colonnes
			for(int i = 1; i <= resultMeta.getColumnCount(); i++)
				System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");

			System.out.println("\n**********************************");

			// Le while ne sert pas puisque 1 seul résultat ici.
			while(result.next()){         
				for(int i = 1; i <= resultMeta.getColumnCount(); i++){
					if(result.getObject(i)!=null)
						System.out.print("\t" + result.getObject(i).toString() + "\t |");
					else 
						System.out.print("\t ***** \t |");
				}

			}
			result.close();
			state.close();

		} catch (Exception e) {
			e.printStackTrace();
		} 

	}


	public static void addComputer(String n, String i, String d, int company){ 
		try {

			Statement state = connect.createStatement();

			state.executeUpdate("INSERT INTO computer(name,introduced,discontinued,company_id) VALUES('"+n+"','"+i+"','"+d+"','"+company+"')");
			state.close();

		} catch (Exception e) {
			e.printStackTrace();
		} 

	}




}

