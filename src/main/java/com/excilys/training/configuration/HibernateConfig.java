package com.excilys.training.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;
import com.querydsl.sql.Configuration;
import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;

@Component
public class HibernateConfig {
    
    
    @Autowired
    DataSource datasource;
    
    SQLTemplates templates = new MySQLTemplates();
    Configuration configuration = new Configuration(templates);
    
    @Bean
    public SQLQueryFactory queryFactory() {
        SQLQueryFactory queryFactory = new SQLQueryFactory(configuration, datasource);
            return queryFactory;
        
    }
    

}
