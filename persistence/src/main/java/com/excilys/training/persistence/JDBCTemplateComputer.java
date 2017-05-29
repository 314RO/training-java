package com.excilys.training.persistence;

import java.util.List;
import java.util.function.Supplier;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import com.excilys.training.exception.NullComputerException;
import com.excilys.training.model.Computer;
import com.excilys.training.model.QCompany;
import com.excilys.training.model.QComputer;

import com.querydsl.jpa.hibernate.HibernateQuery;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

@Repository
public class JDBCTemplateComputer implements ComputerDAO {


    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private static QCompany Qcompany = QCompany.company;
    private static QComputer Qcomputer = QComputer.computer;

    private Supplier<HibernateQueryFactory> queryFactory = () -> new HibernateQueryFactory(
            sessionFactory.getCurrentSession());

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    JDBCTemplateCompany JdbcTemplateCompany;

    public long add(Computer obj) {

        sessionFactory.getCurrentSession().save(obj);

        return obj.getId();

    }

    public void delete(long id) {
        queryFactory.get().delete(Qcomputer).where(Qcomputer.id.eq(id)).execute();
    }

    public void update(long index, Computer computer) {
        queryFactory.get().update(Qcomputer).where(Qcomputer.id.eq(index)).set(Qcomputer.name, computer.getName())
                .set(Qcomputer.introduced, computer.getIntroduced())
                .set(Qcomputer.discontinued, computer.getDiscontinued()).set(Qcomputer.company, computer.getCompany())
                .execute();

    }

    public List<Computer> fetchPage(int page, int itemPerPage) {

        List<Computer> computersResult = queryFactory.get().select(Qcomputer).from(Qcomputer).leftJoin(Qcompany)
                .on(Qcompany.id.eq(Qcomputer.company.id)).limit(itemPerPage).offset((page-1) * itemPerPage).fetch();
        return computersResult;
    }

    public Computer getById(long id) throws NullComputerException {
        Computer computerResult = (Computer) queryFactory.get().select(Qcomputer).from(Qcomputer).leftJoin(Qcompany)
                .on(Qcomputer.company.id.eq(Qcompany.id)).where(Qcomputer.id.eq((Long) id)).fetchOne();

        return computerResult;

    }

    public List<Computer> getByName(String name) {
        List<Computer> computersResult = queryFactory.get().select(Qcomputer).from(Qcomputer).leftJoin(Qcompany)
                .on(Qcompany.id.eq(Qcomputer.company.id)).where(Qcomputer.name.like(name + "%")).fetch();
        return computersResult;
    }

    public long getCount() {
       

        return (int) queryFactory.get().from(Qcomputer).fetchCount();
    }

    public List<Computer> fetchOrderedPage(int page, int itemPerPage, String orderBy, String way) {
        HibernateQuery<Computer> request = queryFactory.get().select(Qcomputer).from(Qcomputer).leftJoin(Qcompany)
                .on(Qcompany.id.eq(Qcomputer.company.id)).limit(itemPerPage).offset((page-1) * itemPerPage);

        switch (way) {
        case "ASC":
            switch (orderBy) {
            case "name":
                request.orderBy(Qcomputer.name.asc());
                break;
            case "introduced":
                request.orderBy(Qcomputer.introduced.asc());
                break;
            case "discontinued":
                request.orderBy(Qcomputer.discontinued.asc());
                break;
            }
            break;
        case "DESC":
            switch (orderBy) {
            case "name":
                request.orderBy(Qcomputer.name.desc());
                break;
            case "introduced":
                request.orderBy(Qcomputer.introduced.desc());
                break;
            case "discontinued":
                request.orderBy(Qcomputer.discontinued.desc());
                break;
            }
            break;
        }

        List<Computer> computersResult = request.fetch();
        return computersResult;
    }

}
