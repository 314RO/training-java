package com.excilys.training.ui;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.training.exceptions.NegativeValueException;
import com.excilys.training.mappers.MapperComputer;
import com.excilys.training.model.Company;
import com.excilys.training.model.ComputerDTO;
import com.excilys.training.service.CompanyServiceImp;
import com.excilys.training.service.ComputerServiceImp;
import com.sun.mail.imap.protocol.MailboxInfo;


/**
 * Servlet implementation class WebUI
 */
@WebServlet("/WebUI")
public class WebUI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ComputerServiceImp computerServiceImp = new ComputerServiceImp();
	CompanyServiceImp companyServiceImp = new CompanyServiceImp();  
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
		// TODO Auto-generated method stub
	    // 1) On définit tous les attributs possibles qu'on peut récuperer par l'URL.
	    
	    int page;
	    //...
	    //...
	    
	    // 2) try catch sur chacun des atttributs pour s'assurer de leur validité.
	    // On rappelle la jsp quelque soit la validité de l'attribut (message d'erreur?)
	    try{
	        page = Integer.parseInt(request.getParameter( "page" ));
	        System.out.println(page);
	       
	       if(page<0){throw new NegativeValueException();}
	       ArrayList<ComputerDTO> computerList = new ArrayList<ComputerDTO>();
	       for (int i = 0; i < computerServiceImp.fetchPage(page).size(); i++) {
	           // mal codé. pas appeler fetch a chaque itération
	           computerList.add(MapperComputer.ObjToDTO(computerServiceImp.fetchPage(page).get(i)));
	            System.out.println(computerServiceImp.fetchPage(page).get(i));
	        }
	       request.setAttribute("computerList", computerList);  
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
