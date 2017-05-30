package com.excilys.training.persistence;

import static org.junit.Assert.*;

import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.function.Supplier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.hibernate.SessionFactory;

import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.mapper.MapperComputer;
import com.excilys.training.model.Computer;
import com.excilys.training.model.QCompany;
import com.excilys.training.model.QComputer;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

public class TestAddComputer {
    
    LocalSessionFactoryBean sessionFactory;
    MysqlDataSource dataSource;
    QCompany Qcompany = QCompany.company;
    QComputer Qcomputer = QComputer.computer;
    

    @Before
    public void setUp() throws Exception {

        String url = null;
        String user = null;
        String passwd = null;

        try {
            ResourceBundle rb = ResourceBundle.getBundle("connection");

            url = rb.getString("url");
            user = rb.getString("user");
            passwd = rb.getString("passwd");

            dataSource = new MysqlDataSource();
            dataSource.setUrl(url);
            dataSource.setUser(user);
            dataSource.setPassword(passwd);

        } catch (MissingResourceException e) {
            e.printStackTrace();
        }

        Properties properties = new Properties();
        properties.put("hibernate.dialect", org.hibernate.dialect.MySQLDialect.class);

        sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.excilys.training");
        sessionFactory.setHibernateProperties(properties);
        Supplier<HibernateQueryFactory> queryFactory = () -> new HibernateQueryFactory( ((SessionFactory) sessionFactory).getCurrentSession());

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        ComputerDTO computerdto = new ComputerDTO();
        computerdto.setName("name");
        computerdto.setIntroduced("1990-10-10");
        computerdto.setIntroduced("1994-10-10");
        computerdto.setCompanyName("Apple Inc.");
        Computer computer = MapperComputer.DTOToObj(computerdto);

        ((SessionFactory) sessionFactory).getCurrentSession().save(computer);

        System.out.println( computer.getId() );
        
        
    }

}
