package com.excilys.training.persistence;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=com.excilys.training.configuration.ProjectConfig.class)
@Component
public class ConnectionTest {
    
        
    @Autowired
    private CompanyDAO companyDAOImp ;
    
    @Test
    public void test() {
        System.out.println("des tests");
        
        try{
        System.out.println(companyDAOImp);
        System.out.println(companyDAOImp.getById(1));
        }
        catch (Exception e) {e.printStackTrace();}
    }

}
