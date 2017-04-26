package com.excilys.training.exceptions;

public class NotNameException extends Exception {
    public NotNameException(){
        System.out.println("Vous essayez de créer ou faites référence à un ordinateur sans nom ou avec un nom trop court");
        
    }
}