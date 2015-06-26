package business;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import business.Company;
import data.CompanyDB;

public class AddToCompanyListServlet extends HttpServlet
{    

	protected void doPost(HttpServletRequest request, 
                          HttpServletResponse response) 
                          throws ServletException, IOException
    {
		String deleteCompany=new String("");
		deleteCompany =request.getParameter("removeButton");
		String deleteCompanyName=request.getParameter("name");
		String addCompany = request.getParameter("addCompany");
		ArrayList<Company> companyList;
		
		
		if(addCompany!=null && addCompany.equalsIgnoreCase("doAdd"))
		{
			// get parameters from the request
			
	    	String companyName = request.getParameter("name");
	        String companyCity = request.getParameter("city");
	        String companyState = request.getParameter("state");
	
	
	        Company company = new Company();
	        company.setName(companyName);
	        company.setCity(companyCity);
	        company.setState(companyState);
	
	        
	        CompanyDB.insert(company);
	        
	        String message = "";
	        String url="";
	        
	        if(companyName.length()==0||companyCity.length()==0||companyState.length()==0){
	        	message = "Please fill out all the text boxes! You ASSHOLE!";
	        	url = "/add_company.jsp";
	        }
	        else
	        {
	        	message="";
	        	ServletContext context = getServletContext();
	        	// String path = "C:\\Users\\Nitin\\workspace\\Chapter7\\WebContent\\WEB-INF\\EmailList";
	           //  UserIO.addRecord(user, path);        
	             url = "/index.jsp";
	        }
		}//check delete

		if(deleteCompany!=null)
		{
			CompanyDB.delete(deleteCompanyName);
		
		}
	        
		
		
		    companyList = CompanyDB.selectCompany();
	        // store the company object in the request object
	        request.setAttribute("companyList", companyList);
	        String url="/index.jsp";
	        RequestDispatcher dispatcher =
	             getServletContext().getRequestDispatcher(url);
	        dispatcher.forward(request, response);              
	   
		
		}//post Method
	
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	 doPost(request, response);
 }
	
	
	
	
}//class
