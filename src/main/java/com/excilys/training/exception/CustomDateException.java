package com.excilys.training.exception;

public class CustomDateException extends Exception {

    public CustomDateException(){
        System.out.println("Le format de date doit être yyyy-MM-dd");
      }
}
