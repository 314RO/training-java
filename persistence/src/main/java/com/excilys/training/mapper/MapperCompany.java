package com.excilys.training.mapper;

import org.springframework.stereotype.Component;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.model.Company;

@Component
public class MapperCompany {

        public MapperCompany() {}
        
        public CompanyDTO ObjToDTO(Company company){
            CompanyDTO companyDTO = new CompanyDTO();
            companyDTO.setName(company.getName());
            companyDTO.setId(company.getId());
            return companyDTO; 
         }
        
        public Company DTOToObj(CompanyDTO companyDTO){
            String name = companyDTO.getName();
            long id = companyDTO.getId();

            return new Company.Builder(name).id(id).build();
        }
        

}
