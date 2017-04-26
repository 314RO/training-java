package com.excilys.training.exceptions;

public class ChronologicalException extends Exception {
    
    public ChronologicalException(){
        System.out.println("Date introduced doit être plus ancienne que Date discontinued");
        
    }
}
