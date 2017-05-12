package com.excilys.training.mapper;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.model.Company;

public class MapperCompany {

        public MapperCompany(){}
        
        public static CompanyDTO ObjToDTO(Company company){
            CompanyDTO companyDTO = new CompanyDTO();
            companyDTO.setName(company.getName());
            companyDTO.setId(company.getId());
            return companyDTO; 
         }
        
        public static Company DTOToObj(CompanyDTO companyDTO){
            String name = companyDTO.getName();
            long id = companyDTO.getId();

            return new Company.Builder(name).id(id).build();
        }
        

}
