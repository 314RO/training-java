package com.excilys.training.ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        LOGGER.info("Faites votre choix\n1) List computers\n2) List companies\n3) Show computer details\n4) Create computers\n5) Update computer \n6) Delete computer");

        while (!SC.hasNextInt()) {
            SC.next();
        }
        int answer = SC.nextInt();

        switch (answer) {
        case 1:
            ArrayList<Computer> arrayComputer = computerServiceImp.fetchPage(askPage());
            displayArray(arrayComputer);
            break;

        case 2:
            ArrayList<Company> arrayCompany = companyServiceImp.fetchPage(askPage());
            displayArray(arrayCompany);
            break;

        case 3:
            Computer computer = computerServiceImp.getById(askIndex());
            LOGGER.info(computer.toString());
            break;

        case 4:
            computerServiceImp.add(askComputer());
            break;

        case 5:
            computerServiceImp.update(askIndex(), askComputer());
            break;

        case 6:
            computerServiceImp.delete(askIndex());
            break;

        default :
            LOGGER.info("Commande invalide");
        }
    }

    /**
     * Demande d'un index (ordinateur ou compagnie).
     * @return long
     */
    public long askIndex() {

        LOGGER.info("Index du computer?");
        int answer = SC.nextInt();
        SC.nextLine();
        if (answer > 0) {
            return (long) answer;
        } else {
            return 0L;
        }
    }

    /**
     * Demande de la page à afficher.
     * @return int
     */
    public int askPage() {

        LOGGER.info("numéro de page?  (>0)");
        int answer = SC.nextInt();
        if (answer > 0) {
            return answer;
        } else {
            return 0;
        }
    }

    /**
     * Demande d'un ordinateur, complet ou non.
     * @return Computer
     */
    public Computer askComputer() {
        LOGGER.info("Computer data : \nname");
        String name = SC.nextLine();
        LOGGER.info("Computer data : \nIntroduced");
        String intro = SC.nextLine();
        LocalDate introduced = (!intro.equals("")) ? LocalDate.parse(intro) : null;
        LOGGER.info("Computer data : \nDiscontinued");
        String disc = SC.nextLine();
        LocalDate discontinued = (!disc.equals("")) ? LocalDate.parse(disc) : null;
        LOGGER.info("Computer data : \nidCompany");
        long idCompany = SC.nextInt();
        return new Computer.Builder(name).introduced(introduced).discontinued(discontinued)
                .company(companyServiceImp.getById(idCompany)).build();

    }

    /**
     * Affichage d'un ArrayList (les résultats de fetchPage pour computer et company).
     * @param  results (ArrayList)
     */
    public void displayArray(ArrayList results) {
        for (int i = 0; i < results.size(); i++) {
            LOGGER.info(results.get(i).toString());
        }
    }
}