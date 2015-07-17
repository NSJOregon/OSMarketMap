/*
This servlet checks to see if a company should be added or deleted based on the post 
This servlet created a Company bean and stores it to the mysql database as well as 
taking user input to delete the company from the database
*/

package business;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

//Business and data handling classes
import business.Company;
import data.CompanyDB;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class AddToCompanyListServlet extends HttpServlet
{    

	protected void doPost(HttpServletRequest request, 
                          HttpServletResponse response) 
                          throws ServletException, IOException
    {
		
		/*
		//Gets parameters that may have been be in the request object
		String deleteCompany=new String("");
		deleteCompany =request.getParameter("removeButton");
		String deleteCompanyName=request.getParameter("name");
		String addCompany = request.getParameter("addCompany");
		ArrayList<Company> companyList;
		
		*/
		String deleteCompany=new String("");
		deleteCompany =request.getParameter("removeCompany");
		String deleteCompanyName=request.getParameter("name");
		if(deleteCompany!=null)
		{
			CompanyDB.delete(deleteCompanyName);		
		}
		
		
		//////////////////////////////////////////////////////////////////////
	    Company company = new Company();
	    Boolean addCompany=false;
	    Boolean removeCompany=false;
	    String addCompanyName="";
	    String addCompanyCity="";
	    String addCompanyState="";
	    FileItem addCompanyLogo=null;    
	    
	    
	    // Create a factory for disk-based file items
        FileItemFactory factory = new DiskFileItemFactory();
         // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // Parse the request
        List<FileItem> items;
	
        try {			
		items = upload.parseRequest(request);	
		
		// Process the uploaded items
         
    	Iterator<FileItem> iter = items.iterator();
        while (iter.hasNext()) {
            
        	
        	FileItem item = (FileItem) iter.next();
            
            if (!item.isFormField()) {
            	addCompanyLogo=item;
 //           	company.setLogo(item);
            }
            
            if(item.isFormField())
            {
            	if (item.getFieldName().equals("name")) {
                    addCompanyName = item.getString();
                    System.out.println("This is the name of the company:" + addCompanyName);
                    // Do something with the value
                }
            	else if(item.getFieldName().equals("city"))
            	{
                    addCompanyCity = item.getString();
                    System.out.println("This is the name of the company:" + addCompanyCity);
            	}
            	else if(item.getFieldName().equals("state"))
            	{
                    addCompanyState = item.getString();
                    System.out.println("This is the name of the company:" + addCompanyState);
            	}
            	else if(item.getFieldName().equals("addCompany"))
            	{
            	   addCompany=true;	
            	}
            	else if(item.getFieldName().equals("removeCompany"))
            	{
            		removeCompany=true;
            	}
            	
            	////////////////////
            }
         }
  		
		 } catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }	
		
        
        if(addCompany==true){

        	company.setName(addCompanyName);
	        company.setCity(addCompanyCity);
	        company.setState(addCompanyState);
           	company.setLogo(addCompanyLogo);
	        CompanyDB.insert(company);	
        }
        
        
		//Checks to see if the add company value was added to the request object
		/*
		if(addCompany!=null && addCompany.equalsIgnoreCase("doAdd"))
		{
			// get parameters from the request
			String companyName = request.getParameter("name");
	        String companyCity = request.getParameter("city");
	        String companyState = request.getParameter("state");
	        Company company1 = new Company();
       
	
	        //Create a new company object and add the company information
	        
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
	        
		*/
		ArrayList<Company> companyList = CompanyDB.selectCompanies();
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
