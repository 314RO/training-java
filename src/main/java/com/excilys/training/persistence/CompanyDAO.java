package com.excilys.training.persistence;

import java.util.List;
import com.excilys.training.model.Company;

public interface CompanyDAO {
    
    
 
    /**
     *  Renvoie toutes les compagnies.
     * @return List<Company>
     */
    
    List<Company> fetchAll();

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
