package com.excilys.training.ui;

import java.util.*;

import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;

public class UserInterface {
	
	private Scanner sc = new Scanner(System.in);


	public UserInterface(){}
	
	public int Menu(){
		System.out.println("Faites votre choix\n1) List computers\n2) List companies\n3) Show computer details\n4) Create computers\n5) Update computer \n6) Delete computer");
		int answer= (int)sc.nextLine().charAt(0)-48;
		if( answer <=6 && answer >=1){
			return answer;
		}
		else {return 0;}
	}
	
	
	public int askIndex(){	
	
	// moche mais on n'a aucun probleme en demandant de supprimer une ligne qui n'existe pas
	// aucun controle sur le type de l'entrée clavier => exception
		System.out.println("Index computer? i>0");
	int answer= sc.nextInt();
	if(answer>0){
		return answer;
	}
	else {return 0;}
}
	
	public Computer askComputer(){	
		System.out.println("Computer data : \nname");
		String name = sc.nextLine();
		System.out.println("Computer data : \nIntroduced");
		String intro = sc.nextLine();
		System.out.println("Computer data : \nDiscontinued");
		String disc = sc.nextLine();
		System.out.println("Computer data : \nidCompany");
		int company = sc.nextInt();
		
		return new Computer(name,intro,disc,company);
						
	}
		
	
	
	
	
	
	
	
	
	
	
	
	public void displayAComputer(ArrayList<Computer> results){
		for (int i=0;i<results.size();i++)
			System.out.println(results.get(i));
		
	}
	public void displayACompany(ArrayList<Company> results){
		for (int i=0;i<results.size();i++)
			System.out.println(results.get(i));
		
	}
	
	
	
	
	
	
}
