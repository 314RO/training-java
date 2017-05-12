package com.excilys.training.ui;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.mapper.MapperCompany;
import com.excilys.training.mapper.MapperComputer;
import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;
import com.excilys.training.service.CompanyServiceImp;
import com.excilys.training.service.ComputerServiceImp;
import com.excilys.training.validator.ValidatorWeb;

@WebServlet("/addComputer")
public class addComputer extends HttpServlet {
    private static final long serialVersionUID = 3L;

    public addComputer() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ComputerServiceImp computerServiceImp = new ComputerServiceImp();
        CompanyServiceImp companyServiceImp = new CompanyServiceImp();
        int id = 0;
        System.out.println("10");
        ArrayList<Company> companyList = new ArrayList<Company>();
        ArrayList<CompanyDTO> companyListDTO = new ArrayList<CompanyDTO>();
        companyList = companyServiceImp.fetchAll();
        for (int i = 0; i < companyList.size(); i++) {
            companyListDTO.add(MapperCompany.ObjToDTO(companyList.get(i)));
        }
        request.setAttribute("companyList", companyListDTO);
        System.out.println("2");
        ComputerDTO cdto = new ComputerDTO();
        Computer c = null;
        cdto.setId(id);
        System.out.println("3");
        cdto.setName((request.getParameter("computerName")));
        cdto.setIntroduced((request.getParameter("introduced")));
        cdto.setDiscontinued((request.getParameter("discontinued")));
        System.out.println(request.getParameter("companyId"));
        System.out.println("7");
        cdto.setCompanyName((request.getParameter("companyId") != null)
                ? companyServiceImp.getById(Long.parseLong(request.getParameter("companyId"))).getName() : null);
        System.out.println("8");
        if (ValidatorWeb.validName(cdto.getName()) && ValidatorWeb.validIntroduced(cdto.getIntroduced())
                && ValidatorWeb.validDiscontinued(cdto.getDiscontinued(), cdto.getIntroduced())) {
            c = MapperComputer.DTOToObj(cdto);
            computerServiceImp.add(c);
            System.out.println("ajouté");
        }
        System.out.println("9");
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
