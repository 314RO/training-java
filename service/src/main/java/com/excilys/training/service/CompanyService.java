package com.excilys.training.service;

import java.util.List;
import com.excilys.training.model.Company;

public interface CompanyService {

    
    /**
     * Récupérer toutes les compagnies (pour la liste déroulante).
     * @return  List<Company>
     */
    List<Company> fetchAll();
    
    
    /**
     * Trouver un élément.
     * @param  id (long)
     * @return Company
     */
    Company getById(long id);
  
    
    /**
     * Trouver un élément avec le nom.
     * @param  name (String)
     * @return Company
     */
    Company getByName(String name);
    
    /**
     * Supprimer une compangie.
     * @param  id (long)
     * @return boolean
     */
    boolean delete(long id);

}
