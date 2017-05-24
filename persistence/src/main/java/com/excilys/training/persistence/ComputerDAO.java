package com.excilys.training.persistence;

import java.util.List;

import com.excilys.training.exception.NullComputerException;
import com.excilys.training.model.Computer;

public interface ComputerDAO {

    
    /**
     * Ajout d'un élément.
     * 
     * @param obj
     *            (Computer)
     * @return boolean
     */
    long add(Computer obj);

    /**
     * Trouver un élément.
     * 
     * @param id
     *            (long)
     * @return Computer
     * @throws NullComputerException
     */
    Computer getById(long id) throws NullComputerException;

    /**
     * Renvoie une page de la bdd.
     * 
     * @param name
     *            (String)
     * @return ArrayList<Computer>
     */
    List<Computer> getByName(String name);

    /**
     * Supprimer un élément.
     * 
     * @param id
     *            (long)
     * @return boolean
     */
    void delete(long id);

    /**
     * Mise à jour d'un élément.
     * 
     * @param index
     *            (long)
     * @param obj
     *            (Computer)
     * @return boolean
     */
    void update(long index, Computer obj);

    /**
     * Renvoie une page de la bdd.
     * 
     * @param page
     *            (int)
     * @return ArrayList<Computer>
     */
    List<Computer> fetchPage(int page, int itemPerPage);

    /**
     * Obtenir le nombre d'ordinateur dans la bdd.
     * 
     * @return int
     */
    long getCount();

    List<Computer> fetchOrderedPage(int page, int itemPerPage, String a, String b);

}
