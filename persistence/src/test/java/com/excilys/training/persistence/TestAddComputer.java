//package com.excilys.training.persistence;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
//
//import com.excilys.training.configuration.ProjectConfig;
//import com.excilys.training.model.Computer;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {ProjectConfig.class})
//@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
//public class TestAddComputer {
//    
//    @Autowired
//    private ComputerDAO queryDSLComputer;
//    
//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//    @Test
//    public void test() {
//        Computer computer = new Computer.Builder("ordi").build();
//        assertEquals(queryDSLComputer.add(computer),575);
//    }
//}