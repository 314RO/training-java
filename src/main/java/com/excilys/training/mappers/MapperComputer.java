package com.excilys.training.mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.excilys.training.DTO.ComputerDTO;
import com.excilys.training.exceptions.NullComputerException;
import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;
import com.excilys.training.persistence.CompanyDAOImp;

public class MapperComputer {
    private static CompanyDAOImp companyDAO = new CompanyDAOImp();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        public MapperComputer(){}
        
        public static ComputerDTO ObjToDTO(Computer computer){
           ComputerDTO computerDTO = new ComputerDTO();
           computerDTO.setName(computer.getName());
           computerDTO.setIntroduced((computer.getIntroduced()!=null)?  computer.getIntroduced().format(formatter) : null);
           computerDTO.setDiscontinued((computer.getDiscontinued()!=null)?  computer.getDiscontinued().format(formatter) : null);
           computerDTO.setCompanyName((computer.getCompany().getName()!=null)?  computer.getCompany().getName() : null);
           
           return computerDTO; 
        }
        public static Computer DTOToObj(ComputerDTO computerDTO) throws NullComputerException{
            Computer computer = null;
            String name = computerDTO.getName();
            LocalDate introduced = (computerDTO.getIntroduced()!=null)? LocalDate.parse(computerDTO.getIntroduced(),formatter) : null;
            LocalDate discontinued = (computerDTO.getDiscontinued ()!=null)? LocalDate.parse(computerDTO.getDiscontinued(),formatter) : null;
            Company company = new Company.Builder(computerDTO.getCompanyName()).id(companyDAO.getByName(computerDTO.getCompanyName()).getId()).build();
            try {
                computer = new Computer.Builder(name).introduced(introduced).discontinued(discontinued).company(company).build();
                return computer;
            } catch (Exception e) {
             throw new NullComputerException();
             }
         }
}
  