package com.excilys.training.ui;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.training.DTO.ComputerDTO;
import com.excilys.training.exceptions.NegativeValueException;
import com.excilys.training.mappers.MapperComputer;
import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;
import com.excilys.training.service.CompanyServiceImp;
import com.excilys.training.service.ComputerServiceImp;


/**
 * Servlet implementation class WebUI
 */
@WebServlet("/WebUI")
public class WebUI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 1) On définit tous les attributs possibles qu'on peut récuperer par l'URL.
	private int itemPerPage=10;
	private int page = 1;
	 
	//MapperComputer mapperComputer = new MapperComputer();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebUI() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    ComputerServiceImp computerServiceImp = new ComputerServiceImp();
	    CompanyServiceImp companyServiceImp = new CompanyServiceImp(); 
	    
	    long count = computerServiceImp.getCount();
	    request.setAttribute("count",count);
	    

	    // 2) try catch sur chacun des atttributs pour s'assurer de leur validité.
	    // On rappelle la jsp quelque soit la validité de l'attribut (message d'erreur)
	    try{
	        if (request.getParameter( "page" )!=null) {
	            if(Integer.parseInt(request.getParameter( "page" ))<1) {
	                throw new NegativeValueException();
	            }
	            else {
	            page = Integer.parseInt(request.getParameter( "page" ));
	            System.out.println(page);
	            }
	        }
	        if (request.getParameter( "itemPerPage" )!=null) {
	            if ( Integer.parseInt(request.getParameter( "itemPerPage" ))<1) {
	                throw new NegativeValueException();
	            }
	            else {
	            itemPerPage = Integer.parseInt(request.getParameter( "itemPerPage" ));
	            System.out.println(itemPerPage);
	            }
	        }
	       ArrayList<Computer> computerList = new ArrayList<Computer>();
	       ArrayList<ComputerDTO> computerListDTO = new ArrayList<ComputerDTO>();
	       computerList=computerServiceImp.fetchPage(page,itemPerPage);
	       for (int i = 0; i < computerList.size(); i++) {
	           computerListDTO.add(MapperComputer.ObjToDTO(computerList.get(i)));
	        }
	       request.setAttribute("computerList", computerListDTO);  
	    }
	    catch(NumberFormatException | NegativeValueException e){
	        request.setAttribute("Erreur", 1);
	    }
	    finally{this.getServletContext().getRequestDispatcher( "/WEB-INF/views/dashboard.jsp" ).forward( request, response );}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
