package com.excilys.training.persistence;

import java.sql.*;
import java.util.ArrayList;

import com.excilys.training.model.*;

public class ComputerDAO extends DAO<Computer> {
public ComputerDAO(Connection conn) {
  super(conn);
}

public boolean create(Computer obj) {
		try {

			Statement state = connect.createStatement();

			state.executeUpdate("INSERT INTO computer(name,introduced,discontinued,company_id) VALUES('"+obj.getName()+"','"+obj.getIntroduced()+"','"+obj.getDiscontinued()+"','"+obj.getCompanyId()+"')");
			state.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 

	}

public boolean delete(int id) {
	try {
	Statement state = connect.createStatement();
	state.executeUpdate("DELETE FROM computer WHERE id = "+id);
	state.close();
	return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	} 
}
 
public boolean update(Computer obj) {
  return false;
}

public ArrayList<Computer> list() {
	ArrayList<Computer> arrayResults = new ArrayList<Computer>();
	try {

		
		Statement state = connect.createStatement();
		ResultSet result = state.executeQuery("SELECT * FROM computer");
		ResultSetMetaData resultMeta = result.getMetaData();
		
		while(result.next()){
			long z = (result.getObject(1)!=null)?(long)result.getObject(1):-1;
			String a = (result.getObject(2)!=null)?result.getObject(2).toString():null;
			String b = (result.getObject(3)!=null)?result.getObject(3).toString():null;
			String c = (result.getObject(4)!=null)?result.getObject(4).toString():null;
			long d = (result.getObject(5)!=null)?(long)result.getObject(5):-1;
			arrayResults.add(new Computer(z,a,b,c,d)); }
		
		result.close();
		state.close();
		return arrayResults;

	} catch (Exception e) {
		e.printStackTrace();
		return arrayResults;
	} 

}

public Computer detail(int id) {
  Computer c = new Computer();      
    
  try {
    ResultSet result = this.connect.createStatement(
      ResultSet.TYPE_SCROLL_INSENSITIVE,
      ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM computer WHERE id = " + id);
    if(result.first())
      c = new Computer(id,result.getString("name"),result.getString("introduced"),result.getString("discontinued"),result.getInt("company_id"));         
  } catch (SQLException e) {
    e.printStackTrace();
  }
  return c;
}
}