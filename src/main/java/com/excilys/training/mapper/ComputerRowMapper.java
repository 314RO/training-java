package com.excilys.training.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.training.model.Computer;
import com.excilys.training.persistence.JDBCTemplateCompany;

@Component
public class ComputerRowMapper implements RowMapper {
    @Autowired
    JDBCTemplateCompany jdbcTemplateCompany;

    public Computer mapRow(ResultSet result, int rowNum) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        if (result.first()) {
            long id = (long) result.getObject("id");
            String name = result.getObject("name").toString();
            LocalDate intro = (result.getObject("introduced") != null) ? LocalDate.parse(result.getString(3), formatter) : null;
            LocalDate disc = (result.getObject("discontinued") != null) ? LocalDate.parse(result.getString(4), formatter) : null;
            long indexCompany = (result.getObject("company_id") != null) ? (long) result.getObject("company_id") : 0;
            return new Computer.Builder(name).id(id).introduced(intro).discontinued(disc)
                    .company(jdbcTemplateCompany.getById(indexCompany)).build();
        }
        return null;
    }

}