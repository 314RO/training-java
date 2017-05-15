package com.excilys.training.ui;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.exception.NegativeValueException;
import com.excilys.training.mapper.MapperCompany;
import com.excilys.training.mapper.MapperComputer;
import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;
import com.excilys.training.service.CompanyService;
import com.excilys.training.service.CompanyServiceImp;
import com.excilys.training.service.ComputerService;
import com.excilys.training.service.ComputerServiceImp;
import com.excilys.training.validator.ValidatorWeb;

@WebServlet("/editComputer")
public class editComputer extends HttpServlet {
    private static final long serialVersionUID = 2L;
    // 1) On définit tous les attributs possibles qu'on peut récuperer par l'URL.
   private int id = 0;
   String name ="";
   @Autowired
   ComputerService computerServiceImp;
   @Autowired
   CompanyService companyServiceImp;

    public editComputer() {
        super();
        
    }
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        
        ArrayList<Company> companyList = new ArrayList<Company>();
        ArrayList<CompanyDTO> companyListDTO = new ArrayList<CompanyDTO>();
        companyList=companyServiceImp.fetchAll();
        for (int i = 0; i < companyList.size(); i++) {
            companyListDTO.add(MapperCompany.ObjToDTO(companyList.get(i)));
         }
        request.setAttribute("companyList", companyListDTO);  

        // 2) try catch sur chacun des atttributs pour s'assurer de leur validité.
        // On rappelle la jsp quelque soit la validité de l'attribut (message d'erreur)
        try{
            if (request.getParameter( "id" )!=null) {
                if(Integer.parseInt(request.getParameter( "id" ))<1) {
                    throw new NegativeValueException();
                }
                else {
                id = Integer.parseInt(request.getParameter( "id" ));
                }
            }
                //name = ValidatorCLI.validName(request.getParameter( "computerName" ));
            ComputerDTO cdto = new ComputerDTO();
            Computer c = null;
                cdto.setId(id);
                System.out.println(id);
                cdto.setName((request.getParameter("computerName")));
                System.out.println(request.getParameter("computerName"));
                cdto.setIntroduced((request.getParameter("introduced")));
                System.out.println((request.getParameter("introduced")));
                cdto.setDiscontinued((request.getParameter("discontinued")));
                System.out.println(request.getParameter("discontinued"));
                cdto.setCompanyName((Long.parseLong(request.getParameter("companyId"))!=0l)? companyServiceImp.getById(Long.parseLong(request.getParameter("companyId"))).getName() :null);
                System.out.println("tous les champs attribués");
                if (ValidatorWeb.validName(cdto.getName()) && 
                        ValidatorWeb.validIntroduced(cdto.getIntroduced()) &&
                        ValidatorWeb.validDiscontinued(cdto.getDiscontinued(), cdto.getIntroduced())){
                System.out.println("avant de convertir dto to obj");
                c = MapperComputer.DTOToObj(cdto);
                System.out.println("juste apres");
                computerServiceImp.update(id,c);
                System.out.println("et juste apres l'update");
                }
        }
        catch(NumberFormatException | NegativeValueException e){
            request.setAttribute("Erreur", 1);// non utilisé pour l'instant
            } 
        finally{
            request.setAttribute("id", id);
            this.getServletContext().getRequestDispatcher( "/WEB-INF/views/editComputer.jsp" ).forward( request, response );}
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
