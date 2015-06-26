package business;

import java.io.Serializable;

public class Company implements Serializable{
	
	private int ID;
	private String name;
	private String latitude;
	private String longitude;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private String phoneNumber;
	private String email;
	private String description;
	private String owner;
    
	public Company(){
		ID=0;
		name="";
		latitude="";
		longitude="";
		address="";
		city="";
		state="";
		zipCode="";
		phoneNumber="";
		email="";
		description="";
		owner="";
		
	}
	
	public Company(int companyID, String companyName, String companyLatitude, String companyLongitude, String companyAddress, String companyCity, String companyState, String companyZipCode, String companyPhoneNumber, String companyEmail, String companyDescription, String companyOwner ){
		ID= companyID;
		name= companyName;
		latitude= companyLatitude;
		longitude= companyLongitude;
		address=companyAddress;
		city=companyCity;
		state=companyState;
		zipCode= companyZipCode;
		phoneNumber=companyPhoneNumber;
		email= companyEmail;
		description= companyDescription;
		owner=companyOwner;
		
	}
	
    public void setID(int i)
    {
    	ID= i;
    }
    
    public int getID(){
    	return ID;
    }
	
    public void setName(String n)
    {
    	name= n;
    }
    
    public String getName(){
    	return name;
    }
	
    public void setLatitude(String n)
    {
    	latitude= n;
    }
    
    public String getLatitude(){
    	return latitude;
    }
	
    public void setLongitude(String n)
    {
    	longitude= n;
    }
    
    public String getLongitude(){
    	return longitude;
    }

  
    public void setAddress(String a)
    {
    	address= a;
    }
    
    public String getAddress(){
    	return address;
    }
  
    public void setCity(String c)
    {
    	city= c;
    }
    
    public String getCity(){
    	return city;
    }

    public void setState(String s)
    {
    	state= s;
    }
    
    public String getState(){
    	return state;
    }
    
    public void setZipcode(String z)
    {
    	zipCode= z;
    }
    
    public String getZipcode(){
    	return zipCode;
    }
    
    public void setPhonenumber(String p)
    {
    	phoneNumber= p;
    }
    
    public String getPhonenumber(){
    	return phoneNumber;
    }
    
    public void setEmail(String e)
    {
    	email= e;
    }
    
    public String getEmail(){
    	return email;
    }
    
    public void setDescription(String d)
    {
    	description= d;
    }
    
    public String getDescription(){
    	return description;
    }

    public void setOwner(String o)
    {
    	owner= o;
    }
    
    public String getOwner(){
    	return owner;
    }

    
}
