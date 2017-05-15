package com.excilys.training.persistence;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.training.configuration.ProjectConfig;
import com.excilys.training.model.Company;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=com.excilys.training.configuration.ProjectConfig.class)
@Component
public class CompanyDAOImpTest {
    
    @Autowired @Qualifier("SQLConnection")
    SQLConnection sqlConnection;
    
    
    @Autowired
    CompanyDAO companyDAOImp ;
    
    @Test 
    public void getByIdTest(){
        System.out.println("dans le test");
        System.out.println(companyDAOImp);
        Company company = new Company.Builder("Apple Inc.").id(1l).build();
       
        Company result = companyDAOImp.getById(1);
        System.out.println(result);
        
        assertTrue(result.equals(company));
    }

}
