package com.excilys.training.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;

import com.excilys.training.model.Company;
import com.excilys.training.persistence.JDBCTemplateCompany;

@ContextConfiguration(classes = com.excilys.training.configuration.ProjectConfig.class)
@Service
public class CompanyServiceImp implements CompanyService {

    @Autowired
    JDBCTemplateCompany jdbcTemplateCompany;

    /**
     * Renvoie une page d'éléments de la base de données. Le nombre d'éléments
     * par page est défini dans les DAOs.
     * 
     * @param page
     *            (int)
     * @return ArrayList <Company>
     */
    public ArrayList<Company> fetchPage(int page) {
        ArrayList<Company> companyList = jdbcTemplateCompany.fetchPage(page);
        return companyList;
    }

    // missing javadoc
    public ArrayList<Company> fetchAll() {
        ArrayList<Company> companyList = jdbcTemplateCompany.fetchAll();
        return companyList;
    }

    /**
     * Renvoie la compagnie correspondant à l'id passé en argument.
     * 
     * @param id
     *            (long)
     * @return Company
     */
    public Company getById(long id) {
        Company company = jdbcTemplateCompany.getById(id);
        return company;
    }

    // missing javadoc
    public Company getByName(String name) {
        Company company = jdbcTemplateCompany.getByName(name);
        return company;
    }
}