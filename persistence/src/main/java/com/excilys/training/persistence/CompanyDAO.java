package com.excilys.training.persistence;

import java.util.List;

import org.hibernate.SessionFactory;

import com.excilys.training.model.Company;

public interface CompanyDAO {
    
    
    void setSessionFactory(SessionFactory sessionFactory);
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
    
    
    /**
     * Supprime la compagnie, et les ordinateurs associés.
     * @param  id (long)
     * @return bool
     */
    boolean delete(long id);
}
