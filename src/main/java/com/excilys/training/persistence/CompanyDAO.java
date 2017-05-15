package com.excilys.training.persistence;

import java.util.ArrayList;
import com.excilys.training.model.Company;

public interface CompanyDAO {

    /**
     *  Renvoie une page de la bdd.
     * @param  page (int)
     * @return ArrayList<Company>
     */
    ArrayList<Company> fetchPage(int page);
    
    /**
     *  Renvoie toutes les compagnies.
     * @return ArrayList<Company>
     */
    
    ArrayList<Company> fetchAll();

    /**
     * Renvoie une compagnie.
     * @param  id (long)
     * @return Company
     */
    Company getById(long id);
    
    
    /**
     * Renvoie une compagnie.
     * @param  name (String)
     * @return Company
     */
    Company getByName(String name);
}
