package com.excilys.training.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginController {
    
    @RequestMapping(method = RequestMethod.GET)
    public String GET(ModelMap model){
        System.out.println("GETlogin");
        return "login";
    }
    
    
    @RequestMapping(method = RequestMethod.POST)
    public String POST(ModelMap model){
        System.out.println("POSTlogin");
        return "login";
    }

}
