package com.excilys.training.exception;

public class NullComputerException extends Exception{
    public NullComputerException(){
        System.out.println("L'ordinateur n'a pas été instancié");
       }
}