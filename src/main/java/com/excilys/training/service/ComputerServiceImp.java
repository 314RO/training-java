package com.excilys.training.service;

import java.util.ArrayList;

import com.excilys.training.exceptions.NullComputerException;
import com.excilys.training.model.Computer;
import com.excilys.training.persistence.ComputerDAOImp;

public class ComputerServiceImp implements ComputerService {

    private ComputerDAOImp computerDAOImp = new ComputerDAOImp();

    /**
     * Ajoute l'ordinateur passé en argument à la base de données.
     * @param  obj (Computer)
     * @return true si réussi, false sinon
     */

    public boolean add(Computer obj) {
        return computerDAOImp.add(obj);
    }

    /**
     * Renvoie l'ordinateur correspondant à l'id
     * passé en argument.
     * @param  id (long)
     * @return Computer
     */
    public Computer getById(long id) {
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
        return computerDAOImp.getByName(name);
    }

    /**
     * Supprime de la bdd l'ordinateur correspondant à l'id passé en argument.
     * @param  id (long)
     * @return true si réussi, false sinon
     */
    public boolean delete(long id) {
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
        return computerDAOImp.update(index, obj);
    }

    /**
     * Renvoie une page d'éléments de la base de données.
     * Le nombre d'éléments par page est défini dans les DAOs.
     * @param  page (int)
     * @return ArrayList <Computer>
     */
    public ArrayList<Computer> fetchPage(int page, int itemPerPage) {
        ArrayList<Computer> computerList = computerDAOImp.fetchPage(page,itemPerPage);
        return computerList;
    }
    
    public long getCount(){
        return computerDAOImp.getCount();
    }

    
}
