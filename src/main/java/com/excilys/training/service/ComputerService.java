package com.excilys.training.service;

import java.util.ArrayList;
import com.excilys.training.model.Computer;

public interface ComputerService {

    /**
     * Ajout.
     * @param obj (Computer)
     * @return boolean
     */
    boolean add(Computer obj);

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
    ArrayList<Computer> getByName(String name);

    /**
     * Supprime élément.
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
     * Afficher page de la bdd.
     * @param  page (int)
     * @return ArrayList<Computer>
     */
    ArrayList<Computer> fetchPage(int page);

}
