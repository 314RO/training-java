package com.excilys.training.persistence;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class DAO<T> {
  protected Connection connect = null;
   
  public DAO(Connection conn){
    this.connect = conn;
  }
   
  /**
  * Méthode de création
  * @param obj
  * @return boolean 
  */
  public abstract boolean create(T obj);

  /**
  * Méthode pour effacer
  * @param int
  * @return boolean 
  */
  public abstract boolean delete(int id);

  /**
  * Méthode de mise à jour
  * @param obj
  * @return boolean
  */
  public abstract boolean update(T obj);

  /**
  * Méthode d'affichage de toutes les données
  * @param none
  * @return void
  */
  public abstract ArrayList<T> list();
  
  /**
   * Méthode d'affichage des informations d'un seul élément
   * @param id
   * @return boolean
   */
   public abstract T detail(int id);
   
}