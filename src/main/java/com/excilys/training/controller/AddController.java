package com.excilys.training.controller;

import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.mapper.MapperCompany;
import com.excilys.training.mapper.MapperComputer;
import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;
import com.excilys.training.service.CompanyService;
import com.excilys.training.service.ComputerService;
import com.excilys.training.validator.ValidatorWeb;

@Controller
@RequestMapping("/addComputer")
public class AddController {

    @Autowired
    ComputerService computerServiceImp;
    @Autowired
    CompanyService companyServiceImp;

    int id = 0;

    @RequestMapping(method = RequestMethod.GET)
    public String add(ModelMap model) {
        ArrayList<CompanyDTO> companyListDTO = new ArrayList<CompanyDTO>();
        ArrayList<Company> companyList = new ArrayList<Company>();

        companyList = companyServiceImp.fetchAll();
        for (int i = 0; i < companyList.size(); i++) {
            companyListDTO.add(MapperCompany.ObjToDTO(companyList.get(i)));
        }

        model.addAttribute("companyList", companyListDTO);

        return "addComputer";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView add(ModelMap model, @RequestParam(value = "computerName") String name,
            @RequestParam(value = "introduced", required = false)  String introduced,
            @RequestParam(value = "discontinued", required = false) String discontinued,
            @RequestParam(value = "companyId", required = false) Long id) {

        ComputerDTO cdto = new ComputerDTO();
        Computer c = null;
        cdto.setId(id);
        cdto.setName(name);
        cdto.setIntroduced(introduced);
        cdto.setDiscontinued(discontinued);
        System.out.println(id);
        cdto.setCompanyName((id != null && id !=0) ? companyServiceImp.getById(id).getName() : null);
        if (ValidatorWeb.validName(name) && ValidatorWeb.validIntroduced(introduced)
                && ValidatorWeb.validDiscontinued(discontinued, introduced)) {
            c = MapperComputer.DTOToObj(cdto);
            System.out.println(computerServiceImp);
            computerServiceImp.add(c);
            System.out.println("ajouté");
        }
        else { model.addAttribute("Erreur",3);}
        
        return new ModelAndView("redirect:/dashboard");
    }
}
