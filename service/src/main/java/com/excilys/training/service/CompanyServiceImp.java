package com.excilys.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.training.model.Company;
import com.excilys.training.persistence.JDBCTemplateCompany;

@Service
@Transactional
public class CompanyServiceImp implements CompanyService {

    @Autowired
    JDBCTemplateCompany jdbcTemplateCompany;



    // missing javadoc
    public List<Company> fetchAll() {
        List<Company> companyList = jdbcTemplateCompany.fetchAll();
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
