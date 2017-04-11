package com.excilys.training.service;

import java.util.ArrayList;

import com.excilys.training.model.*;
import com.excilys.training.persistence.*;
import com.excilys.training.ui.*;


public class Service {
	
	private	DAO<Computer> computerDao = new ComputerDAO(SQLConnection.getInstance());
	private	DAO<Company> companyDao = new CompanyDAO(SQLConnection.getInstance());
	private UserInterface ui = new UserInterface();
	private static int mode;
	
	public Service() {};
	
	
	public void work(){
		mode=ui.Menu();
		switch(mode){
		
		case 1 : ArrayList<Computer> computerList = computerDao.list();
		ui.displayAComputer(computerList);
		break;
		
		case 2 : ArrayList<Company> companyList = companyDao.list();
		ui.displayACompany(companyList);
		break;
		
		case 3 : Computer computer = computerDao.detail(ui.askIndex());
		System.out.println(computer);
		break;
		
		case 4 : Computer new_computer = ui.askComputer();
		computerDao.create(new_computer);
		break;
		
		
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}	
