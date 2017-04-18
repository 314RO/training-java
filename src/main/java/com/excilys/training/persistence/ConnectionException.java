package com.excilys.training.persistence;

public class ConnectionException extends Exception {
    /**
     * Indique que l'on est déjà connecté.
     */
    public ConnectionException() {
        System.out.println("Déjà connecté");
    }
}