package com.excilys.training.exceptions;

public class NoNameException extends Exception {
    // name.length en arg pour renvoyer soit pas de nom, soit nom trop court?
    public NoNameException(){
        System.out.println("Vous essayez de créer ou faites référence à un ordinateur sans nom ou avec un nom trop court");
      }
}