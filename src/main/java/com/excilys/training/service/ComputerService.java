package com.excilys.training.service;

import java.util.List;

import com.excilys.training.model.Computer;

public interface ComputerService {

    /**
     * Ajout.
     * @param obj (Computer)
     * @return boolean
     */
    long add(Computer obj);

    /**
     * Trouver un élément avec l'id.
     * @param  id (long)
     * @return Computer
     */
    Computer getById(long id);

    /**
     * Trouver des éléments avec le nom.
     * @param  name (String)
     * @return ArrayList<Computer>
     */
    List<Computer> getByName(String name);

    /**
     * Supprime élément.
     * @param  id (long)
     * @return boolean
     */
    void delete(long id);

    /**
     * Mise à jour d'un élément.
     * @param  index (long)
     * @param  obj (Computer)
     * @return boolean
     */
    void update(long index, Computer obj);

    /**
     * Afficher page de la bdd.
     * @param  page (int)
     * @return ArrayList<Computer>
     */
    List<Computer> fetchPage(int page, int itemPerPage);
    
    /**
     * Obtenir le nombre d'ordinateur dans la bdd.
     * @return int
     */
    long getCount();
    
    
    List<Computer> fetchOrderedPage(int page, int itemPerPage, String a, String b);
    
 

}
