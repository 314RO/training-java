package com.excilys.training.persistence;

import java.sql.*;
import java.util.ArrayList;

import com.excilys.training.model.*;

public class CompanyDAO extends DAO<Company> {
public CompanyDAO(Connection conn) {
  super(conn);
}

public boolean create(Company obj) {
	System.out.println("Cette méthode n'est pas disponible pour cette bdd");
	return false;
	}

public boolean delete(int id) {
	System.out.println("Cette méthode n'est pas disponible pour cette bdd");
		return false;
}
 
public boolean update(Company obj) {
	System.out.println("Cette méthode n'est pas disponible pour cette bdd");
	return false;
}

public ArrayList<Company> list() {
	ArrayList<Company> arrayResults = new ArrayList<Company>();
	try {

		
		Statement state = connect.createStatement();
		ResultSet result = state.executeQuery("SELECT * FROM company");
		ResultSetMetaData resultMeta = result.getMetaData();
		
		while(result.next()){
			long index = (result.getObject(1)!=null)?(long)result.getObject(1):-1;
			String name = (result.getObject(2)!=null)?result.getObject(2).toString():null;
			arrayResults.add(new Company(index,name)); }
		
		result.close();
		state.close();
		return arrayResults;

	} catch (Exception e) {
		e.printStackTrace();
		return arrayResults;
	} 

}

public Company detail(int id) {
  Company c = new Company();      
    
  try {
    ResultSet result = this.connect.createStatement(
      ResultSet.TYPE_SCROLL_INSENSITIVE,
      ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM company WHERE id = " + id);
    if(result.first())
      c = new Company(id,result.getString("name"));         
  } catch (SQLException e) {
    e.printStackTrace();
  }
  return c;
}
}