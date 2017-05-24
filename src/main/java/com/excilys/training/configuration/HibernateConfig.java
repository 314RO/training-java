package com.excilys.training.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import com.querydsl.sql.Configuration;
import com.querydsl.sql.MySQLTemplates;

import com.querydsl.sql.SQLTemplates;

@Component
public class HibernateConfig {

    @Autowired
    DataSource datasource;

    SQLTemplates templates = new MySQLTemplates();
    Configuration configuration = new Configuration(templates);

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(datasource);
        sessionFactory.setPackagesToScan("com.excilys.training");
        sessionFactory.setHibernateProperties(additionalProperties());

        return sessionFactory;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", org.hibernate.dialect.MySQLDialect.class);

        return properties;
    }

}
