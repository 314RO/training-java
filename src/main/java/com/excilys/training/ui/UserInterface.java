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
import com.excilys.training.validators.ValidatorCLI;

public class UserInterface {
    private CompanyServiceImp companyServiceImp = new CompanyServiceImp();
    private ComputerServiceImp computerServiceImp = new ComputerServiceImp();
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInterface.class);
    private static final Scanner SC = new Scanner(System.in);
    

    /**
     * Constructeur par défaut de la classe.
     */
    public UserInterface() {}

    /**
     * Demande de l'action à réaliser.
     */
    public void menu() {
        
        int choice=0;
        try {
            choice = (int) askPosIntValue("Faites votre choix\n1) List computers\n2) List companies\n3) Show computer details\n4) Create computer\n5) Update computer \n6) Delete computer \n7) Exit");
        } catch (NegativeValueException e) {
            LOGGER.debug("Valeur négative ou nulle demandée");
        }
        switch (choice) {
        case 1:
            ArrayList<Computer> arrayComputer;
            try {
                arrayComputer = computerServiceImp.fetchPage((int)askPosIntValue("page"),10);
                displayArray(arrayComputer);
            } catch (NegativeValueException e) {
                LOGGER.debug("Valeur négative ou nulle demandée");
            }
            
            break;

        case 2:
            ArrayList<Company> arrayCompany;
            try {
                arrayCompany = companyServiceImp.fetchPage((int)askPosIntValue("page"));
                displayArray(arrayCompany);
            } catch (NegativeValueException e) {
                LOGGER.debug("Valeur négative ou nulle demandée");
            }
            
            break;

        case 3:
            Computer computer;
            try {
                computer = computerServiceImp.getById(askPosIntValue("computer"));
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
                computerServiceImp.update(askPosIntValue("computer"), askComputer());
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
                computerServiceImp.delete(askPosIntValue("computer"));
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
    public long askPosIntValue(String context) throws NegativeValueException {

        System.out.println(context+"  (>0)");
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
        try{
        System.out.println("Computer data : \nname");
        String name = ValidatorCLI.validName(SC.nextLine());
        System.out.println("Computer data : \nIntroduced");
        LocalDate introduced = ValidatorCLI.validIntroduced(SC.nextLine());
        System.out.println("Computer data : \nDiscontinued");
        LocalDate discontinued = ValidatorCLI.validDiscontinued(SC.nextLine(),introduced);
        System.out.println("Computer data : \nidCompany");
        String id = SC.nextLine();
        long idCompany = (!id.equals("")) ? Long.parseLong(id) : 0;
        return new Computer.Builder(name).introduced(introduced).discontinued(discontinued)
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