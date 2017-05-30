package com.excilys.training.persistence;

import java.util.List;

import org.hibernate.SessionFactory;

import com.excilys.training.exception.NullComputerException;
import com.excilys.training.model.Computer;

public interface ComputerDAO {

    void setSessionFactory(SessionFactory sessionFactory);
    /**
     * Ajout d'un élément.
     * 
     * @param obj (Computer)
     * @return boolean
     */
    long add(Computer obj);

    /**
     * Trouver un élément avec l'id.
     * 
     * @param id (long)
     * @return Computer
     * @throws NullComputerException
     */
    Computer getById(long id) throws NullComputerException;

    /**
     * Renvoie les ordinateurs dont le nom contient l'argument.
     * 
     * @param name (String)
     * @return ArrayList<Computer>
     */
    List<Computer> getByName(String name);

    
    /**
     * Renvoie les ordinateurs associés à la compagnie.
     * 
     * @param id (long)
     *
     * @return ArrayList<Computer>
     */
    List<Computer> getByCompany(long id);
    
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
