package com.excilys.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.training.exception.NullComputerException;
import com.excilys.training.model.Computer;
import com.excilys.training.persistence.JDBCTemplateComputer;

@Service
@Transactional
public class ComputerServiceImp implements ComputerService {

    @Autowired
    JDBCTemplateComputer jdbcTemplateComputer;

    /**
     * Ajoute l'ordinateur passé en argument à la base de données.
     * 
     * @param obj
     *            (Computer)
     * @return true si réussi, false sinon
     */

    public long add(Computer obj) {

        return jdbcTemplateComputer.add(obj);
    }

    /**
     * Renvoie l'ordinateur correspondant à l'id passé en argument.
     * 
     * @param id
     *            (long)
     * @return Computer
     */
    public Computer getById(long id) {

        Computer computer = null;
        try {
            computer = jdbcTemplateComputer.getById(id);
        } catch (NullComputerException e) {
            e.printStackTrace();
        }
        return computer;
    }

    /**
     * Renvoie tous les ordinateurs correspondant au nom passé en argument.
     * 
     * @param name
     *            (String)
     * @return ArrayList<Computer>
     */
    public List<Computer> getByName(String name) {

        return jdbcTemplateComputer.getByName(name);
    }

    /**
     * Supprime de la bdd l'ordinateur correspondant à l'id passé en argument.
     * 
     * @param id
     *            (long)
     * @return true si réussi, false sinon
     */
    public void delete(long id) {

        jdbcTemplateComputer.delete(id);
    }

    /**
     * Modifie l'ordinateur correspondant à l'id dans la bdd en lui donnant les
     * paramètres de l'ordinateur passé en argument.
     * 
     * @param index
     *            (long)
     * @param obj
     *            (Computer)
     * @return true si réussi, false sinon
     */
    public void update(long index, Computer obj) {

        jdbcTemplateComputer.update(index, obj);
    }

    /**
     * Renvoie une page d'éléments de la base de données. Le nombre d'éléments
     * par page est défini dans les DAOs.
     * 
     * @param page
     *            (int)
     * @return ArrayList <Computer>
     */
    public List<Computer> fetchPage(int page, int itemPerPage) {

        List<Computer> computerList = jdbcTemplateComputer.fetchPage(page, itemPerPage);
        return computerList;
    }

    public List<Computer> fetchOrderedPage(int page, int itemPerPage, String a, String b) {

        List<Computer> computerList = jdbcTemplateComputer.fetchOrderedPage(page, itemPerPage, a, b);
        return computerList;
    }

    /**
     * Obtenir le nombre d'ordinateur dans la bdd.
     * 
     * @return int
     */
    public long getCount() {
        return jdbcTemplateComputer.getCount();
    }
}
