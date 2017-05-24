package com.excilys.training.service;

import java.util.List;
import com.excilys.training.model.Company;

public interface CompanyService {

    
    // missing javadoc
    List<Company> fetchAll();
    
    
    /**
     * Trouver un élément.
     * @param  id (long)
     * @return Company
     */
    Company getById(long id);
  
    
 // missing javadoc
    Company getByName(String name);

}
