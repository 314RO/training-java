package com.excilys.training.persistence;

import java.util.List;

import java.util.function.Supplier;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import com.excilys.training.model.Company;
import com.excilys.training.model.QCompany;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

@Repository
public class JDBCTemplateCompany implements CompanyDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private static QCompany Qcompany = QCompany.company;

    private Supplier<HibernateQueryFactory> queryFactory = () -> new HibernateQueryFactory(
            sessionFactory.getCurrentSession());

    @Autowired
    PlatformTransactionManager transactionManager;

    public List<Company> fetchAll() {
        List<Company> companyResult = queryFactory.get().select(Qcompany).from(Qcompany).fetch();
        return companyResult;
    }

    public Company getById(long id) {
        Company companyResult = (Company) queryFactory.get().select(Qcompany).from(Qcompany).where(Qcompany.id.eq(id))
                .fetchOne();
        return companyResult;

    }

    public Company getByName(String name) {
        Company companyResult = (Company) queryFactory.get().select(Qcompany).from(Qcompany)
                .where(Qcompany.name.eq(name)).fetchOne();
        return companyResult;

    }

}
