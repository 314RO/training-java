package com.excilys.training.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.exception.NegativeValueException;
import com.excilys.training.mapper.MapperComputer;
import com.excilys.training.model.Computer;
import com.excilys.training.service.CompanyService;
import com.excilys.training.service.ComputerService;

@Controller
public class DashboardController {

    private int itemPerPage = 10;
    private int page = 1;
    private String search = "";
    private String column = "default";
    private String order = "ASC";
    private static String lastRequest = "none";
    
    @Autowired
    ComputerService computerServiceImp;
    @Autowired
    CompanyService companyServiceImp;

    @RequestMapping(value="/dashboard",method = RequestMethod.GET)
    public String dashboard(Locale locale, ModelMap model, @RequestParam(value = "itemPerPage", required = false) Integer itemPerPage,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "column", required = false) String column,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "order", required = false) String order,
            @RequestParam(value = "Erreur", required = false) Integer erreur
            ) {
        System.out.println(locale);
        List<Computer> computerList = new ArrayList<Computer>();
        List<ComputerDTO> computerListDTO = new ArrayList<ComputerDTO>();

        
        if (erreur != null) {
            model.addAttribute("Erreur", erreur);
        }
        
        if (page != null) {
            this.page = page;
        }

        if (search != null) {
            this.search = search;
            lastRequest = "search";
            System.out.println("search : " + search);
        }

        if (order != null) {
            switch (order) {
            case "ASC":
            case "DESC":
                this.order = order;
                break;
            default:
                order = "ASC";
                this.order = order;
            }

            System.out.println("order :  " + order);

        }

        if (column != null) {

            switch (column) {
            case "name":
            case "introduced":
            case "discontinued":
                this.column = column;
                lastRequest = "column";
                break;
            default:
                this.column = "none";
                lastRequest = "none";
            }
            System.out.println("column : " + column);

        }

        long count = computerServiceImp.getCount();

       
        try {
            
            if (this.page < 0) {
                throw new NegativeValueException();
            } else {
                System.out.println("page : " + this.page);
            }

            if (itemPerPage != null)
                if (itemPerPage < 1) {
                    throw new NegativeValueException();
                } else {
                    this.itemPerPage = itemPerPage;
                    this.page = 1;
                    System.out.println("itemPerPage : " + this.itemPerPage);
                }

            switch (lastRequest) {

            case "search":
                System.out.println(this.search);
                computerList = computerServiceImp.getByName(this.search);

                for (int i = 0; i < computerList.size(); i++) {
                    computerListDTO.add(MapperComputer.ObjToDTO(computerList.get(i)));
                }
                model.addAttribute("computerList", computerListDTO);
                break;
            case "column":
                computerList = computerServiceImp.fetchOrderedPage(this.page, this.itemPerPage, this.column,
                        this.order);
                for (int i = 0; i < computerList.size(); i++) {
                    computerListDTO.add(MapperComputer.ObjToDTO(computerList.get(i)));
                }
                model.addAttribute("computerList", computerListDTO);
                break;
            case "none":
                computerList = computerServiceImp.fetchPage(this.page, this.itemPerPage);
                for (int i = 0; i < computerList.size(); i++) {
                    computerListDTO.add(MapperComputer.ObjToDTO(computerList.get(i)));
                }
                model.addAttribute("computerList", computerListDTO);
                break;
            default:
                model.addAttribute("Erreur", 2);
            }

        } catch (NumberFormatException | NegativeValueException e) {
            model.addAttribute("Erreur", 1);
        } finally {

            System.out.println(lastRequest);

        }
        model.addAttribute("count", count);

        model.addAttribute("pageNbr", (int) Math.ceil((double) count / this.itemPerPage));
        model.addAttribute("page", this.page);
        model.addAttribute("column", this.column);

        return "dashboard";
    }
    
    
    
    
    @RequestMapping(value="/dashboard",method = RequestMethod.POST)
    public String printHello(ModelMap model, @RequestParam(value = "selection") int[] id){
        for (int i :id) {System.out.println(i); computerServiceImp.delete(i);}
       
        return "redirect:/dashboard";
    }
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
    

}
