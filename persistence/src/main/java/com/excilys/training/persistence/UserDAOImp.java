package com.excilys.training.persistence;

import java.util.function.Supplier;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import com.excilys.training.model.QUser;
import com.excilys.training.model.User;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

@Repository
public class UserDAOImp implements UserDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    private static QUser Quser = QUser.user;
    
    private Supplier<HibernateQueryFactory> queryFactory = () -> new HibernateQueryFactory(
            sessionFactory.getCurrentSession());
    
    @Autowired
    private PlatformTransactionManager transactionManager;
    
    
    public User check(User user) {

        
        User UserResult = (User) queryFactory.get().select(Quser).from(Quser).where(Quser.name.eq(user.getName()),Quser.password.eq(user.getPassword())).fetchOne();
        return UserResult;
    }
    
    public User getByName(String name) {
       
        User UserResult = (User) queryFactory.get().select(Quser).from(Quser).where(Quser.name.eq(name)).fetchOne();
        return UserResult;
    }

}
