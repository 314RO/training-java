package com.excilys.training.service;

import java.util.ArrayList;

import com.excilys.training.exceptions.NullComputerException;
import com.excilys.training.model.Computer;
import com.excilys.training.persistence.ComputerDAOImp;

public class ComputerServiceImp implements ComputerService {

    /**
     * Ajoute l'ordinateur passé en argument à la base de données.
     * @param  obj (Computer)
     * @return true si réussi, false sinon
     */

    public boolean add(Computer obj) {
        ComputerDAOImp computerDAOImp = new ComputerDAOImp();
        return computerDAOImp.add(obj);
    }

    /**
     * Renvoie l'ordinateur correspondant à l'id
     * passé en argument.
     * @param  id (long)
     * @return Computer
     */
    public Computer getById(long id) {
        ComputerDAOImp computerDAOImp = new ComputerDAOImp();
        Computer computer=null;
        try {
            computer = computerDAOImp.getById(id);
        } catch (NullComputerException e) {
            e.printStackTrace();
        }
        return computer;
    }

    /**
     * Renvoie tous les ordinateurs correspondant au nom
     * passé en argument.
     * @param  name (String)
     * @return ArrayList<Computer>
     */
    public ArrayList<Computer> getByName(String name) {
        ComputerDAOImp computerDAOImp = new ComputerDAOImp();
        return computerDAOImp.getByName(name);
    }

    /**
     * Supprime de la bdd l'ordinateur correspondant à l'id passé en argument.
     * @param  id (long)
     * @return true si réussi, false sinon
     */
    public boolean delete(long id) {
        ComputerDAOImp computerDAOImp = new ComputerDAOImp();
        return computerDAOImp.delete(id);
    }

    /**
     * Modifie l'ordinateur correspondant à l'id dans la bdd
     * en lui donnant les paramètres de l'ordinateur passé en argument.
     * @param  index (long)
     * @param  obj (Computer)
     * @return true si réussi, false sinon
     */
    public boolean update(long index, Computer obj) {
        ComputerDAOImp computerDAOImp = new ComputerDAOImp();
        return computerDAOImp.update(index, obj);
    }

    /**
     * Renvoie une page d'éléments de la base de données.
     * Le nombre d'éléments par page est défini dans les DAOs.
     * @param  page (int)
     * @return ArrayList <Computer>
     */
    public ArrayList<Computer> fetchPage(int page, int itemPerPage) {
        ComputerDAOImp computerDAOImp = new ComputerDAOImp();
        ArrayList<Computer> computerList = computerDAOImp.fetchPage(page,itemPerPage);
        return computerList;
    }
    
    /**
     * Obtenir le nombre d'ordinateur dans la bdd.
     * @return int
     */
    public long getCount(){
        ComputerDAOImp computerDAOImp = new ComputerDAOImp();
        return computerDAOImp.getCount();
    }
}
