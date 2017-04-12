package com.excilys.training.persistence;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import com.excilys.training.model.*;

public class ComputerDAOImp implements ComputerDAO {
	
	private Connection connect = null;
	
public ComputerDAOImp(Connection conn) {
	connect = conn;
}

public boolean add(Computer obj) {
		try {

			Statement state = connect.createStatement();

			state.executeUpdate("INSERT INTO computer(name,introduced,discontinued,company_id) VALUES('"+obj.getName()+"','"+obj.getIntroduced()+"','"+obj.getDiscontinued()+"','"+obj.getCompany()+"')");
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

public ArrayList<Computer> fetchPage() {
	ArrayList<Computer> arrayResults = new ArrayList<Computer>();
	try {

		
		Statement state = connect.createStatement();
		ResultSet result = state.executeQuery("SELECT * FROM computer");
		ResultSetMetaData resultMeta = result.getMetaData();
		
		while(result.next()){
			long id = (long)result.getObject(1);
			String name =result.getObject(2).toString();
			String introduced = (result.getObject(3)!=null)?result.getObject(3).toString():null;
			String discontinued = (result.getObject(4)!=null)?result.getObject(4).toString():null;
			long indexCompany = (result.getObject(5)!=null)?(long)result.getObject(5):-1;
			arrayResults.add(new Computer.Builder(name).id(id).introduced(LocalDate.parse(introduced)).discontinued(LocalDate.parse(discontinued)).company(company).build()); }
		
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