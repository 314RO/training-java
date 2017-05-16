package com.excilys.training.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.training.model.Company;

@Component
public class CompanyRowMapper implements RowMapper {
    
    public Company mapRow(ResultSet result, int rowNum) throws SQLException {

        if (result.first()) {
            long id = (long) result.getObject("id");
            String name = result.getObject("name").toString();
            
            return new Company.Builder(name).id(id).build();
        }
        return null;
    }

}
       