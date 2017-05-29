package com.excilys.training.service;

import com.excilys.training.model.User;

public interface UserService{
    
    User check(User user);
    
    User getByName(String name);

}