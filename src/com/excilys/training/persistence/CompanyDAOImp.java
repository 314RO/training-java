package com.excilys.training.persistence;

import java.sql.*;
import java.util.ArrayList;
import com.excilys.training.model.*;

public class CompanyDAOImp implements CompanyDAO{

	private Connection connect = null;
	
	public CompanyDAOImp(Connection conn) {
		connect = conn;
	}


public ArrayList<Company> fetchPage() {
	ArrayList<Company> arrayResults = new ArrayList<Company>();
	try {

		
		Statement state = connect.createStatement();
		ResultSet result = state.executeQuery("SELECT * FROM company");
	
		while(result.next()){
			long index = (long) result.getObject(1);
			String name = result.getObject(2).toString();
			arrayResults.add(new Company.Builder(name).id(index).build()); }
		
		result.close();
		state.close();
		return arrayResults;

	} catch (Exception e) {
		e.printStackTrace();
		return arrayResults;
	} 

}


public Company getById(long id) {
	  try {
		Statement state = connect.createStatement();
	    ResultSet result = state.executeQuery("SELECT * FROM company WHERE id = " + id);
	    if(result.first())
	      return new Company.Builder(result.getString("name")).id(id).build(); 
	    
	  } catch (SQLException e) {
	    e.printStackTrace();
	  }
	  return new Company.Builder("null").id(-1).build(); 
	}



}

