package com.excilys.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;
import com.excilys.training.persistence.CompanyDAO;
import com.excilys.training.persistence.ComputerDAO;


@Service
@Transactional
public class CompanyServiceImp implements CompanyService {

    @Autowired
    CompanyDAO queryDSLCompany;

    @Autowired
    ComputerDAO queryDSLComputer;
    
    // missing javadoc
    
    public List<Company> fetchAll() {
        List<Company> companyList = queryDSLCompany.fetchAll();
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
        Company company = queryDSLCompany.getById(id);
        return company;
    }

    // missing javadoc
    
    public Company getByName(String name) {
        Company company = queryDSLCompany.getByName(name);
        return company;
    }
    
    @Override
    
    public boolean delete(long id) {
        List<Computer> computersResult = queryDSLComputer.getByCompany(id);
        
        for(Computer computer : computersResult) {
            queryDSLComputer.delete(computer.getId());
        }
        return queryDSLCompany.delete(id);
       
    }
}
