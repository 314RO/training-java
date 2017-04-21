package com.excilys.training.mappers;

import com.excilys.training.model.Company;
import com.excilys.training.model.CompanyDTO;

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
