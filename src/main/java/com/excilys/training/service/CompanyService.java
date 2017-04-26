package com.excilys.training.service;

import java.util.ArrayList;
import com.excilys.training.model.Company;

public interface CompanyService {

    /**
     * Affiche une page de la bdd.
     * @param  page (int)
     * @return ArrayList<Company>
     */
    ArrayList<Company> fetchPage(int page);

    
    // missing javadoc
    ArrayList<Company> fetchAll();
    
    
    /**
     * Trouver un élément.
     * @param  id (long)
     * @return Company
     */
    Company getById(long id);
  
    
 // missing javadoc
    Company getByName(String name);

}