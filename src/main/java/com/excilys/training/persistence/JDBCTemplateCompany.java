package com.excilys.training.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.mapper.CompanyRowMapper;
import com.excilys.training.mapper.MapperCompany;
import com.excilys.training.model.Company;

@Repository
public class JDBCTemplateCompany implements CompanyDAO {

    private static final int ITEM_PER_PAGE = 10;
    private static final String FETCH_QUERY = "SELECT * FROM company LIMIT " + ITEM_PER_PAGE + " OFFSET ? ";
    private static final String FETCH_ALL_QUERY = "SELECT * FROM company";
    private static final String ID_QUERY = "SELECT * FROM company WHERE id = ?";
    private static final String NAME_QUERY = "SELECT * FROM company WHERE name = ?";
    
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public ArrayList<Company> fetchPage(int page) {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(FETCH_QUERY, page * ITEM_PER_PAGE);
        return MapToArray(rows);

    }

    public ArrayList<Company> fetchAll() {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(FETCH_ALL_QUERY);
        return MapToArray(rows);
    }

    public Company getById(long id) {
        Company company = null;

        company = (Company) jdbcTemplate.queryForObject(ID_QUERY, new Object[] { id }, new CompanyRowMapper());

        return company;

    }

    public Company getByName(String name) {
        Company company = null;

        company = (Company) jdbcTemplate.queryForObject(NAME_QUERY, new Object[] { name }, new CompanyRowMapper());

        return company;

    }

    public ArrayList<Company> MapToArray(List<Map<String, Object>> rows) {
        ArrayList<Company> arrayResults = new ArrayList<Company>();

        for (Map row : rows) {
            CompanyDTO companyDTO = new CompanyDTO();
            companyDTO.setId((long) row.get("id"));
            companyDTO.setName(row.get("name").toString());
            arrayResults.add(MapperCompany.DTOToObj(companyDTO));
        }

        return arrayResults;

    }
}
