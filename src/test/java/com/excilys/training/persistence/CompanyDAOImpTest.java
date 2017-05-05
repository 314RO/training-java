package com.excilys.training.persistence;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import com.excilys.training.model.Company;

public class CompanyDAOImpTest {
    
    @Test 
    public void getByIdTest(){
        CompanyDAO companyDAOImp = new CompanyDAOImp();
        Company company = new Company.Builder("Apple Inc.").id(1l).build();
        Company result = companyDAOImp.getById(1);
        
        assertTrue(result.equals(company));
    }

}
