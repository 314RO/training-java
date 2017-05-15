package com.excilys.training.persistence;

import java.util.ArrayList;

import com.excilys.training.exception.NullComputerException;
import com.excilys.training.model.Computer;

public interface ComputerDAO {

    /**
     * Ajout d'un élément.
     * @param  obj (Computer)
     * @return boolean
     */
    boolean add(Computer obj);

    /**
     * Trouver un élément.
     * @param  id (long)
     * @return Computer
     * @throws NullComputerException 
     */
    Computer getById(long id) throws NullComputerException;

    /**
     * Renvoie une page de la bdd.
     * @param  name (String)
     * @return ArrayList<Computer>
     */
    ArrayList<Computer> getByName(String name);

    /**
     * Supprimer un élément.
     * @param  id (long)
     * @return boolean
     */
    boolean delete(long id);

    /**
     * Mise à jour d'un élément.
     * @param  index (long)
     * @param  obj (Computer)
     * @return boolean
     */
    boolean update(long index, Computer obj);

    /**
     * Renvoie une page de la bdd.
     * @param  page (int)
     * @return ArrayList<Computer>
     */
    ArrayList<Computer> fetchPage(int page, int itemPerPage);
    
    /**
     * Obtenir le nombre d'ordinateur dans la bdd.
     * @return int
     */
    long getCount();
    
    ArrayList<Computer> fetchOrderedPage(int page, int itemPerPage, String a, String b);
    

}
