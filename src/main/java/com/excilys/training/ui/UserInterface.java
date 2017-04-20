package com.excilys.training.ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.training.exceptions.ChronologicalException;
import com.excilys.training.exceptions.CustomDateException;
import com.excilys.training.exceptions.NegativeValueException;
import com.excilys.training.exceptions.NoNameException;
import com.excilys.training.exceptions.NullComputerException;
import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;
import com.excilys.training.service.CompanyServiceImp;
import com.excilys.training.service.ComputerServiceImp;

public class UserInterface {
    private CompanyServiceImp companyServiceImp = new CompanyServiceImp();
    private ComputerServiceImp computerServiceImp = new ComputerServiceImp();
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInterface.class);
    private static final Scanner SC = new Scanner(System.in);

    /**
     * Constructeur par défaut de la classe.
     */
    public UserInterface() {
        }

    /**
     * Demande de l'action à réaliser.
     */
    public void menu() {
        
        System.out.println("Faites votre choix\n1) List computers\n2) List companies\n3) Show computer details\n4) Create computer\n5) Update computer \n6) Delete computer \n7) Exit");
        
        while (!SC.hasNextInt()) {
            System.out.println("Vous devez entrer un entier");
            SC.next();
        }
        int answer = SC.nextInt();
        SC.nextLine();

        switch (answer) {
        case 1:
            ArrayList<Computer> arrayComputer;
            try {
                arrayComputer = computerServiceImp.fetchPage((int)askPosValue("page"));
                displayArray(arrayComputer);
            } catch (NegativeValueException e) {
                LOGGER.debug("Valeur négative ou nulle demandée");
            }
            
            break;

        case 2:
            ArrayList<Company> arrayCompany;
            try {
                arrayCompany = companyServiceImp.fetchPage((int)askPosValue("page"));
                displayArray(arrayCompany);
            } catch (NegativeValueException e) {
                LOGGER.debug("Valeur négative ou nulle demandée");
            }
            
            break;

        case 3:
            Computer computer;
            try {
                computer = computerServiceImp.getById(askPosValue("computer"));
                System.out.println(computer.toString());
            } catch (NegativeValueException e) {
                LOGGER.debug("Valeur négative ou nulle demandée");
            }
            break;
        case 4:
            try {
                computerServiceImp.add(askComputer());
            } catch (NullComputerException e) {
                LOGGER.debug("Valeur négative ou nulle demandée");
            } catch (ChronologicalException e) {
                LOGGER.debug("Erreur dans les dates");
            } catch (CustomDateException e) {
                LOGGER.debug("Format de date incorrect");
            }
            break;

        case 5:
            try {
                computerServiceImp.update(askPosValue("computer"), askComputer());
            } catch (NullComputerException e) {
                LOGGER.debug("Update computer avec nom incorrect");
            } catch (NegativeValueException e) {
                LOGGER.debug("Valeur négative ou nulle demandée");
            } catch (ChronologicalException e) {
                LOGGER.debug("Erreur dans les dates");
            } catch (CustomDateException e) {
                LOGGER.debug("Format de date incorrect");
            }
            break;

        case 6:
            try {
                computerServiceImp.delete(askPosValue("computer"));
            } catch (NegativeValueException e) {
                LOGGER.debug("Valeur négative ou nulle demandée"); 
            }
            break;
        
        case 7:
            break;

        default :
            System.out.println("Commande invalide");
        }
    }

    /**
     * Demande d'un index (ordinateur ou compagnie).
     * @return long
     * @throws NegativeValueException 
     */
    public long askPosValue(String name) throws NegativeValueException {

        System.out.println("Index "+name+ " ?  (>0)");
        while (!SC.hasNextInt()) {
            System.out.println("Vous devez entrer un entier");
            SC.next();
        }
        int answer = SC.nextInt();
        SC.nextLine();
        if (answer > 0) {
            return (long) answer;
        } else {
            throw new NegativeValueException();
        }
    }


    /**
     * Demande d'un ordinateur, complet ou non.
     * @return Computer
     * @throws NullComputerException 
     * @throws ChronologicalException 
     */
    public Computer askComputer() throws NullComputerException, ChronologicalException,CustomDateException {
        System.out.println("Computer data : \nname");
        String name = SC.nextLine();
        System.out.println("Computer data : \nIntroduced");
        // nouvelle version
        String intro = SC.nextLine();
        intro = (!intro.equals("")) ? intro : null;
        System.out.println("Computer data : \nDiscontinued");
        // ancienne version
        String disc = SC.nextLine();
        disc= (!disc.equals("")) ? disc : null;
        System.out.println("Computer data : \nidCompany");
        String id = SC.nextLine();
        long idCompany = (!id.equals("")) ? Long.parseLong(id) : 0;
        try {
            return new Computer.Builder(name).introduced(intro).discontinued(disc)
                    .company(companyServiceImp.getById(idCompany)).build();
        } catch (NoNameException e) {
            
            throw new NullComputerException();
        } catch (ChronologicalException | CustomDateException e) {
            throw e;
        }

    }

    /**
     * Affichage d'un ArrayList (les résultats de fetchPage pour computer et company).
     * @param  results (ArrayList)
     */
    public void displayArray(ArrayList results) {
        for (int i = 0; i < results.size(); i++) {
            System.out.println(results.get(i));
        }
    }
}