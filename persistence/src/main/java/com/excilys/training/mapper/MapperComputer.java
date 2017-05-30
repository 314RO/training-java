package com.excilys.training.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;

@Component

public class MapperComputer {
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
 
        public MapperComputer() {}
                       
        public ComputerDTO ObjToDTO(Computer computer){
           ComputerDTO computerDTO = new ComputerDTO();
           computerDTO.setId(computer.getId());
           computerDTO.setName(computer.getName());
           computerDTO.setIntroduced((computer.getIntroduced()!=null)?  computer.getIntroduced().format(formatter) : null);
           computerDTO.setDiscontinued((computer.getDiscontinued()!=null)?  computer.getDiscontinued().format(formatter) : null);
           computerDTO.setCompanyName((computer.getCompany()!=null)?  computer.getCompany().getName() : null);
           
           return computerDTO; 
        }
        
        
        public Computer DTOToObj(ComputerDTO computerDTO){
            Computer computer = null;
            Company company = null;
            long id = computerDTO.getId();
            String name = computerDTO.getName();
            LocalDate introduced = (computerDTO.getIntroduced()!=null && !computerDTO.getIntroduced().equals(""))? LocalDate.parse(computerDTO.getIntroduced(),formatter) : null;
            LocalDate discontinued = (computerDTO.getDiscontinued ()!=null && !computerDTO.getDiscontinued().equals(""))? LocalDate.parse(computerDTO.getDiscontinued(),formatter) : null;

            if (computerDTO.getCompanyName()!=null) {
            company = new Company.Builder(computerDTO.getCompanyName()).id(computerDTO.getCompanyId()).build();
            }
            computer = new Computer.Builder(name).id(id).introduced(introduced).discontinued(discontinued).company(company).build();
            return computer;
            }
}
  