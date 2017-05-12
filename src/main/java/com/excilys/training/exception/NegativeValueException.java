package com.excilys.training.exception;

public class NegativeValueException extends Exception {
    public NegativeValueException(){
        System.out.println("Vous avez entré une valeur négative ou nulle");
      }
}