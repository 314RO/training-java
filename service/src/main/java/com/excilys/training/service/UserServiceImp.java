package com.excilys.training.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.training.model.User;
import com.excilys.training.persistence.UserDAO;

public class UserServiceImp implements UserService{
    
    @Autowired
    private UserDAO userDAO;
    @Override
    
    public User check(User user) {
        return userDAO.check(user);
        
    }

    @Override
    public User getByName(String name) {
        
        return userDAO.getByName(name);
    }

}
