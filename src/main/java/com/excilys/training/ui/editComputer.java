package com.excilys.training.ui;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.training.DTO.CompanyDTO;
import com.excilys.training.DTO.ComputerDTO;
import com.excilys.training.exceptions.NegativeValueException;
import com.excilys.training.mappers.MapperCompany;
import com.excilys.training.mappers.MapperComputer;
import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;
import com.excilys.training.service.CompanyServiceImp;
import com.excilys.training.service.ComputerServiceImp;
import com.excilys.training.validators.ValidatorWeb;

@WebServlet("/editComputer")
public class editComputer extends HttpServlet {
    private static final long serialVersionUID = 2L;
    // 1) On définit tous les attributs possibles qu'on peut récuperer par l'URL.
   private int id = 0;
   String name ="";

    public editComputer() {
        super();
        
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ComputerServiceImp computerServiceImp = new ComputerServiceImp();
        CompanyServiceImp companyServiceImp = new CompanyServiceImp();
        
        
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
                cdto.setName((request.getParameter("computerName")));
                cdto.setIntroduced((request.getParameter("introduced")));
                cdto.setDiscontinued((request.getParameter("discontinued")));
                cdto.setCompanyName((Long.parseLong(request.getParameter("companyId"))!=0l)? companyServiceImp.getById(Long.parseLong(request.getParameter("companyId"))).getName() :null);
                
                if (ValidatorWeb.validName(cdto.getName()) && 
                        ValidatorWeb.validIntroduced(cdto.getIntroduced()) &&
                        ValidatorWeb.validDiscontinued(cdto.getDiscontinued(), cdto.getIntroduced())){
                c = MapperComputer.DTOToObj(cdto);
                computerServiceImp.update(id,c);
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
