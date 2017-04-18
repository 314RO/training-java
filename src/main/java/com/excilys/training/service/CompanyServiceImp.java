package com.excilys.training.service;

import java.util.ArrayList;
import com.excilys.training.model.Company;
import com.excilys.training.persistence.CompanyDAOImp;

public class CompanyServiceImp implements CompanyService {

    private CompanyDAOImp companyDAOImp = new CompanyDAOImp();

    /**
     * Renvoie une page d'éléments de la base de données.
     * Le nombre d'éléments par page est défini dans les DAOs.
     * @param  page (int)
     * @return ArrayList <Company>
     */
    public ArrayList<Company> fetchPage(int page) {
        ArrayList<Company> companyList = companyDAOImp.fetchPage(page);
        return companyList;
    }

    /**
     * Renvoie la compagnie correspondant à l'id
     * passé en argument.
     * @param  id (long)
     * @return Company
     */
    public Company getById(long id) {
        Company company = companyDAOImp.getById(id);
        return company;
    }
}