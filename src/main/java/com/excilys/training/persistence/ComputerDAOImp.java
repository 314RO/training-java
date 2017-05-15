package com.excilys.training.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.excilys.training.exception.NullComputerException;
import com.excilys.training.model.Computer;

@Repository
public class ComputerDAOImp implements ComputerDAO {
    
    
    private static final String ADD_QUERY = "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES(?,?,?,?)";
    private static final String DELETE_QUERY = "DELETE FROM computer WHERE id =?";
    private static final String FETCH_QUERY = "SELECT * FROM computer LIMIT ? OFFSET ? ";
    // fetch ordered query est incomplète. Cf l'implémentation de la requete plus bas.
    private static final String FETCH_ORDERED_QUERY = "SELECT * FROM computer ORDER BY ";
    private static final String ID_QUERY = "SELECT * FROM computer WHERE id = ?";
    private static final String NAME_QUERY = "SELECT * FROM computer WHERE name LIKE ?";
    private static final String UPDATE_QUERY = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?,company_id=? WHERE id = ?";
    private static final String COUNT_QUERY = "SELECT COUNT(*) FROM computer";
    
    @Autowired @Qualifier("SQLConnection")
    SQLConnection sqlConnection;
    
    // doit disparaitre. Pas de lien entre DAO.
    @Autowired
    CompanyDAOImp companyDAOImp;
    
    /**
     * Constructeur par défaut de la classe.
     */
    public ComputerDAOImp() {}

    /**
     * Ajout d'un ordinateur à la bdd.
     * @param obj (Computer à ajouter)
     * @return true si réussi, false sinon
     */
    public boolean add(Computer obj) {
        
        try (Connection connect = sqlConnection.getInstance();
                PreparedStatement preparedStatement = connect.prepareStatement(ADD_QUERY);
            ) {
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setObject(2, (obj.getIntroduced()!=null)? obj.getIntroduced().toString():null);
            preparedStatement.setObject(3, (obj.getDiscontinued()!=null)? obj.getDiscontinued().toString():null);
            preparedStatement.setObject(4, (obj.getCompany().getId()!=0)?obj.getCompany().getId():null);
            preparedStatement.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Suppression d'un élément.
     * @param id (long)
     * @return true si réussi, false sinon
     */
    public boolean delete(long id) {
        
        try (Connection connect = sqlConnection.getInstance();
                PreparedStatement preparedStatement = connect.prepareStatement(DELETE_QUERY);
           ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Mise à jour d'un élément.
     * @param  index (long, id de l'élément à modifier)
     * @param  obj (Computer contenant les nouvelles données)
     * @return true si réussi, false sinon
     */
    public boolean update(long index, Computer obj) {
        
        try (Connection connect = sqlConnection.getInstance();
                PreparedStatement preparedStatement = connect.prepareStatement(UPDATE_QUERY);
            ) {

            preparedStatement.setString(1, obj.getName());
            preparedStatement.setObject(2, (obj.getIntroduced()!=null)? obj.getIntroduced().toString():null);
            preparedStatement.setObject(3, (obj.getDiscontinued()!=null)? obj.getDiscontinued().toString():null);
            preparedStatement.setObject(4, (obj.getCompany().getId()!=0)?obj.getCompany().getId():null);
            preparedStatement.setLong(5, index);
            preparedStatement.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Renvoie une page de la bdd.
     * @param  page (int)
     * @return ArrayList<Computer>
     */
    public ArrayList<Computer> fetchPage(int page, int itemPerPage) {
        ArrayList<Computer> arrayResults = new ArrayList<Computer>();
        
        try (Connection connect = sqlConnection.getInstance();
            PreparedStatement preparedStatement = connect.prepareStatement(FETCH_QUERY);

                ) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            preparedStatement.setInt(1, itemPerPage);
            preparedStatement.setInt(2, (page-1) * itemPerPage);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                long id = (long) result.getObject("id");
                String name = result.getObject("name").toString();
                LocalDate intro = (result.getObject("introduced") != null) ? LocalDate.parse(result.getString(3), formatter) : null;   
                LocalDate disc = (result.getObject("discontinued") != null) ? LocalDate.parse(result.getString(4), formatter) : null;
                long indexCompany = (result.getObject("company_id") != null) ? (long) result.getObject(5) : 0;
                arrayResults.add(new Computer.Builder(name).id(id).introduced(intro).discontinued(disc)
                        .company(companyDAOImp.getById(indexCompany)).build());
            }
            
            result.close();
            return arrayResults;

        } catch (Exception e) {
            e.printStackTrace();
            return arrayResults;
        }

    }

    /**
     * Trouver un élément (id).
     * @param  id (long)
     * @return computer
     * @throws NullComputerException
     */
    public Computer getById(long id) throws NullComputerException {
        String name = null;
        LocalDate intro = null;
        LocalDate disc= null;
        Computer computer = null;
        long indexCompany = 0;
        
        try (Connection connect = sqlConnection.getInstance();) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            PreparedStatement preparedStatement = connect.prepareStatement(ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet result = preparedStatement.executeQuery();

            if (result.first()) {
                name = result.getObject("name").toString();
                intro = (result.getObject("introduced") != null) ? LocalDate.parse(result.getString(3), formatter) : null;               
                disc = (result.getObject("discontinued") != null) ? LocalDate.parse(result.getString(4), formatter) : null;
                indexCompany = (result.getObject("company_id") != null) ? (long) result.getObject("company_id") : 0;
                computer = new Computer.Builder(name).id(id).introduced(intro).discontinued(disc).company(companyDAOImp.getById(indexCompany)).build();
            }
            
            return computer;
        } catch (SQLException e) {
            throw new NullComputerException();   
        } 
    }

    /**
     * Trouver des éléments (nom).
     * @param  name (String)
     * @return ArrayList<Computer>
     */
    public ArrayList<Computer> getByName(String name) {
        ArrayList<Computer> arrayResults = new ArrayList<Computer>();
        
        
        try (Connection connect = sqlConnection.getInstance();) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            PreparedStatement preparedStatement = connect.prepareStatement(NAME_QUERY);
            preparedStatement.setString(1, name + "%");
            ResultSet result = preparedStatement.executeQuery();
            
            while (result.next()) {
                long id = (long) result.getObject("id");
                LocalDate intro = (result.getObject("introduced") != null) ? LocalDate.parse(result.getString("introduced"), formatter) : null;              
                LocalDate disc = (result.getObject("discontinued") != null) ? LocalDate.parse(result.getString("discontinued"), formatter) : null; 
                long indexCompany = (result.getObject("company_id") != null) ? (long) result.getObject("company_id") : 0;
                arrayResults.add(new Computer.Builder(result.getString("name")).id(id).introduced(intro).discontinued(disc).company(companyDAOImp.getById(indexCompany)).build());
                
            }

            result.close();
            preparedStatement.close();
            return arrayResults;

        } catch (Exception e) {
            e.printStackTrace();
            return arrayResults;
        }

    }
    
    
    
    public long getCount(){
       long count = 0l;
       
        try (Connection connect = sqlConnection.getInstance();){
            PreparedStatement preparedStatement = connect.prepareStatement(COUNT_QUERY);
            ResultSet result = preparedStatement.executeQuery();
            if (result.first()) {
                count =result.getLong(1);}
            result.close();
            preparedStatement.close();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return -1l;
        }
    }
    
    
    public ArrayList<Computer> fetchOrderedPage(int page, int itemPerPage, String orderBy, String way) {
        ArrayList<Computer> arrayResults = new ArrayList<Computer>();
        
        try (Connection connect = sqlConnection.getInstance();
            PreparedStatement preparedStatement = connect.prepareStatement(FETCH_ORDERED_QUERY +orderBy+" "+ way +" LIMIT "+itemPerPage+" OFFSET "+(page-1) * itemPerPage);

                ) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            
            System.out.println(preparedStatement);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                long id = (long) result.getObject("id");
                String name = result.getObject("name").toString();
                LocalDate intro = (result.getObject("introduced") != null) ? LocalDate.parse(result.getString(3), formatter) : null;   
                LocalDate disc = (result.getObject("discontinued") != null) ? LocalDate.parse(result.getString(4), formatter) : null;
                long indexCompany = (result.getObject("company_id") != null) ? (long) result.getObject(5) : 0;
                arrayResults.add(new Computer.Builder(name).id(id).introduced(intro).discontinued(disc)
                        .company(companyDAOImp.getById(indexCompany)).build());
            }

            result.close();
            return arrayResults;

        } catch (Exception e) {
            e.printStackTrace();
            return arrayResults;
        }

    }
    
    
    
    
    
    
    
    
    
    
}