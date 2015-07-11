/*
This servlet checks to see if a company should be added or deleted based on the post 
This servlet created a Company bean and stores it to the mysql database as well as 
taking user input to delete the company from the database
*/

package business;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
//Business and data handling classes
import business.Company;
import data.CompanyDB;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class AddToCompanyListServlet extends HttpServlet
{    

	protected void doPost(HttpServletRequest request, 
                          HttpServletResponse response) 
                          throws ServletException, IOException
    {
		//Gets parameters that may have been be in the request object
		String deleteCompany=new String("");
		deleteCompany =request.getParameter("removeButton");
		String deleteCompanyName=request.getParameter("name");
		String addCompany = request.getParameter("addCompany");
		ArrayList<Company> companyList;
		
		
		//Checks to see if the add company value was added to the request object
		if(addCompany!=null && addCompany.equalsIgnoreCase("doAdd"))
		{
			// get parameters from the request
			String companyName = request.getParameter("name");
	        String companyCity = request.getParameter("city");
	        String companyState = request.getParameter("state");
	
	
	        //Create a new company object and add the company information
	        Company company = new Company();
	        company.setName(companyName);
	        company.setCity(companyCity);
	        company.setState(companyState);
	        CompanyDB.insert(company);
	        
	        //Initializes message and url variables
	        String message = "";
	        String url="";
	        
	        //Checks to see that all the fields were completed
	        if(companyName.length()==0||companyCity.length()==0||companyState.length()==0){
	        	message = "Please fill out all the text boxes! You ASSHOLE!";
	        	url = "/add_company.jsp";
	        	
	        }
	        else
	        {
	            //If all fields are completed, we are setting the URK to go to as the index page to list all 
	        	//of the companies
	        	message="";
	            url = "/index.jsp";
	        }
		}//check Add Company

		//Check if the deleteCompany value is in the request object
		if(deleteCompany!=null)
		{
			CompanyDB.delete(deleteCompanyName);		
		}
	        
		
		companyList = CompanyDB.selectCompany();
	    // store the company object in the request object
	    request.setAttribute("companyList", companyList);
	    String url="/index.jsp";
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
	    dispatcher.forward(request, response);              	        
	       
		
		}//post Method
	
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	 doPost(request, response);
    //All get requests get forwarded to POST
 }

	
	
}//class
