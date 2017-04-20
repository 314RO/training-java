package com.excilys.training.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CompanyTest {

    @Before
    public void setUp() throws Exception {
        
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        Company c = new Company.Builder(null).build();
        System.out.println(c);
    }

}
