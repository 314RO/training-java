package com.excilys.training.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;
import com.excilys.training.persistence.JDBCTemplateCompany;

@Component
public class MapperComputer {
   
    static JDBCTemplateCompany jdbcTemplateCompany;
    
    @Autowired
    public void setjdbcTemplateCompany(JDBCTemplateCompany jdbcTemplateCompany){
        MapperComputer.jdbcTemplateCompany = jdbcTemplateCompany;
    }
    
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
        public MapperComputer(){}
                       
        public static ComputerDTO ObjToDTO(Computer computer){
           ComputerDTO computerDTO = new ComputerDTO();
           computerDTO.setId(computer.getId());
           computerDTO.setName(computer.getName());
           computerDTO.setIntroduced((computer.getIntroduced()!=null)?  computer.getIntroduced().format(formatter) : null);
           computerDTO.setDiscontinued((computer.getDiscontinued()!=null)?  computer.getDiscontinued().format(formatter) : null);
           computerDTO.setCompanyName((computer.getCompany().getName()!=null)?  computer.getCompany().getName() : null);
           
           return computerDTO; 
        }
        public static Computer DTOToObj(ComputerDTO computerDTO){
            Computer computer = null;
            long id = computerDTO.getId();
            String name = computerDTO.getName();
            LocalDate introduced = (computerDTO.getIntroduced()!=null && !computerDTO.getIntroduced().equals(""))? LocalDate.parse(computerDTO.getIntroduced(),formatter) : null;
            LocalDate discontinued = (computerDTO.getDiscontinued ()!=null && !computerDTO.getDiscontinued().equals(""))? LocalDate.parse(computerDTO.getDiscontinued(),formatter) : null;
            
            System.out.println("name  " + computerDTO.getCompanyName());
            System.out.println(jdbcTemplateCompany);
            if 
            Company company = new Company.Builder(computerDTO.getCompanyName()).id(jdbcTemplateCompany.getByName(computerDTO.getCompanyName()).getId()).build();
            
            computer = new Computer.Builder(name).id(id).introduced(introduced).discontinued(discontinued).company(company).build();
            return computer;
            }
}
  