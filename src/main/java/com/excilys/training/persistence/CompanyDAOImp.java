package com.excilys.training.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.training.model.Company;

public class CompanyDAOImp implements CompanyDAO {
    private static final int ITEM_PER_PAGE = 10;
    private static final String FETCH_QUERY = "SELECT * FROM computer LIMIT " + ITEM_PER_PAGE + " OFFSET ? ";
    private static final String ID_QUERY = "SELECT * FROM computer WHERE id = ?";
    private Connection connect = SQLConnection.getInstance();

    /**
     * Constructeur par défaut de la classe.
     */
    public CompanyDAOImp() {
    }

    /**
     * Renvoie une page de la bdd.
     * @param page (int)
     * @return ArrayList<Company>
     */
    public ArrayList<Company> fetchPage(int page) {
        ArrayList<Company> arrayResults = new ArrayList<Company>();
        try {

            PreparedStatement preparedStatement = connect.prepareStatement(FETCH_QUERY);
            preparedStatement.setInt(1, page * ITEM_PER_PAGE);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                long index = (long) result.getObject(1);
                String name = result.getObject(2).toString();
                arrayResults.add(new Company.Builder(name).id(index).build());
            }

            result.close();
            preparedStatement.close();
            return arrayResults;

        } catch (Exception e) {
            e.printStackTrace();
            return arrayResults;
        }

    }

    /**
     * Trouver un élément.
     * @param id (long)
     * @return Company
     */
    public Company getById(long id) {
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet result = preparedStatement.executeQuery();

            if (result.first()) {
                return new Company.Builder(result.getString("name")).id(id).build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Company.Builder("null").id(-1).build();
    }

}
