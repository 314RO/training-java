package com.excilys.training.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/dashboard")
public class DashboardController {

    // 1) On définit tous les attributs possibles que l'on peut récuperer par
    // l'URL.

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

    @RequestMapping(method = RequestMethod.GET)
    public String printHello(ModelMap model, @RequestParam(value = "itemPerPage", required = false) Integer itemPerPage,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "column", required = false) String column,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "order", required = false) String order) {
        ArrayList<Computer> computerList = new ArrayList<Computer>();
        ArrayList<ComputerDTO> computerListDTO = new ArrayList<ComputerDTO>();

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
            case "company":
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

        // 2) try catch sur chacun des atttributs pour s'assurer de leur
        // validité.
        // On rappelle la jsp quelque soit la validité de l'attribut (message
        // d'erreur)
        try {
            // le try au dessus catch la NumberFormatException du parseInt
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

        return "dashboard";
    }

}
