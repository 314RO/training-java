package com.excilys.training.ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import com.excilys.training.exceptions.ChronologicalException;
import com.excilys.training.exceptions.CustomDateException;
import com.excilys.training.exceptions.NegativeValueException;
import com.excilys.training.exceptions.NotNameException;
import com.excilys.training.exceptions.NullComputerException;
import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;
import com.excilys.training.service.CompanyServiceImp;
import com.excilys.training.service.ComputerServiceImp;
import com.excilys.training.validators.ValidatorCLI;

public class UserInterface {
    private CompanyServiceImp companyServiceImp = new CompanyServiceImp();
    private ComputerServiceImp computerServiceImp = new ComputerServiceImp();
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

        int choice = 0;
        try {
            choice = (int) askPosLongValue(
                    "Faites votre choix\n1) List computers\n2) List companies\n3) Show computer details\n4) Create computer\n5) Update computer \n6) Delete computer \n7) Exit");
        } catch (NegativeValueException e) {}
        
        switch (choice) {
        case 1:
            ArrayList<Computer> arrayComputer;
            try {
                arrayComputer = computerServiceImp.fetchPage((int) askPosLongValue("page  (>0)"), 10);
                displayArray(arrayComputer);
            } catch (NegativeValueException e) {}

            break;

        case 2:
            ArrayList<Company> arrayCompany;
            try {
                arrayCompany = companyServiceImp.fetchPage((int) askPosLongValue("page  (>0)"));
                displayArray(arrayCompany);
            } catch (NegativeValueException e) {}
            break;

        case 3:
            Computer computer;
            try {
                computer = computerServiceImp.getById(askPosLongValue("computer  (>0)"));
                System.out.println(computer.toString());
            } catch (NegativeValueException e) {}
            break;

        case 4:
            try {
                computerServiceImp.add(askComputer());
            } catch (NullComputerException e) {}
            break;

        case 5:
            try {
                computerServiceImp.update(askPosLongValue("computer  (>0)"), askComputer());
            } catch (NullComputerException | NegativeValueException e) {}
            break;

        case 6:
            try {
                computerServiceImp.delete(askPosLongValue("computer  (>0)"));
            } catch (NegativeValueException e) {}
            break;

        case 7:
            break;

        default:
            System.out.println("Commande invalide");
        }
    }

    /**
     * Demande d'un index (ordinateur ou compagnie).
     * 
     * @return long
     * @throws NegativeValueException
     */
    public long askPosLongValue(String context) throws NegativeValueException {

        System.out.println(context);
        while (!SC.hasNextLong()) {
            System.out.println("Vous devez entrer un entier");
            SC.next();
        }
        long answer = SC.nextLong();
        SC.nextLine();
        if (answer > 0) {
            return answer;
        } else {
            throw new NegativeValueException();
        }
    }

    /**
     * Demande d'un ordinateur, complet ou non.
     * 
     * @return Computer
     * @throws NullComputerException
     */
    public Computer askComputer() throws NullComputerException {
        try {
            System.out.println("Computer data : \nname");
            String name = ValidatorCLI.validName(SC.nextLine());
            System.out.println("Computer data : \nIntroduced");
            LocalDate introduced = ValidatorCLI.validIntroduced(SC.nextLine());
            System.out.println("Computer data : \nDiscontinued");
            LocalDate discontinued = ValidatorCLI.validDiscontinued(SC.nextLine(), introduced);
            long idCompany = askPosLongValue("Computer data : \nidCompany");
            return new Computer.Builder(name).introduced(introduced).discontinued(discontinued)
                    .company(companyServiceImp.getById(idCompany)).build();

        } catch (NotNameException | ChronologicalException | CustomDateException | NegativeValueException e) {
            throw new NullComputerException();
        }
    }

    /**
     * Affichage d'un ArrayList (les résultats de fetchPage pour computer et
     * company).
     * 
     * @param results
     *            (ArrayList)
     */
    public void displayArray(ArrayList results) {
        for (int i = 0; i < results.size(); i++) {
            System.out.println(results.get(i));
        }
    }
}