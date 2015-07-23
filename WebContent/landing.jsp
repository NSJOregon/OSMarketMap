
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import ="business.*,java.util.ArrayList"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.*"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MarketMaps</title>

</head>
<body>
	<h1>WELCOME TO MARKET MAPS</h1>
	<h3>MarketMaps works with many local companies!</h3>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Geocoding service</title>
    <style>
      html, body, #map-canvas {
        width: 500px;
        height: 400px;
        margin: 0px;
        padding: 0px
      }
      #panel {
        position: absolute;
        top: 5px;
        left: 50%;
        margin-left: -180px;
        z-index: 5;
        background-color: #fff;
        padding: 5px;
        border: 1px solid #999;
      }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script>
	<!--   <script src="js/Map.js"></script>-->
  
  <script>
  	var array = [];
  </script>
  
   <!--  This code goes through the companies in the request object and insert the city and state into 
   a JavaScrip array that can be used for mapping pins later -->
	  <%  
	  ArrayList<Company> companyList = (ArrayList<Company>) request.getAttribute("companyList");
	  String gfl="";
	   
	  if(companyList!=null){
		for (Company currentCompany:companyList)
		{
		  gfl= currentCompany.getCity()+","+ currentCompany.getState(); 
		%>
		
			<script>
			array.push("<%out.print(gfl);%>")
			</script>
		
		<%
			}
	    }
	   %>

  <!--  This script segment are the function to initilize the map upon loading the page and geocoding the locatio
     of the companies using the city and state of the company -->  
  <script>
	  
	  var geocoder;
	  var map;
	  
	    function initialize() {
	    geocoder = new google.maps.Geocoder();
	    var latlng = new google.maps.LatLng(45.520, -122.682);
	    
	    var mapOptions = {
	      zoom: 8,
	      center: latlng
	    }
	    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
	    
	    
	    
	    var xyz = "<%out.print(gfl);%>";
	    
	    for (index = 0; index < array.length; ++index) {
	        codeAddress(array[index]);
	    }
	  
	  }
	
	  function codeAddress() {
	
		   var address = document.getElementById('address').value;
	  		
	  	  geocoder.geocode( { 'address': address}, function(results, status) {
	    
	  	  if (status == google.maps.GeocoderStatus.OK) {
	        map.setCenter(results[0].geometry.location);
	     
	        var marker = new google.maps.Marker({
	            map: map,
	            position: results[0].geometry.location
	        });
	      } else {
	        alert('Geocode was not successful for the following reason: ' + status);
	      }
	    });
	  
	  }
	  
	  
	  function codeAddress(address) {
	
	 		
		 	  geocoder.geocode( { 'address': address}, function(results, status) {
		   
		 	  if (status == google.maps.GeocoderStatus.OK) {
		       map.setCenter(results[0].geometry.location);
		    
		       var marker = new google.maps.Marker({
		           map: map,
		           position: results[0].geometry.location
		       		});
		     } else {
		       alert('Geocode was not successful for the following reason: ' + status);
		     }
		   });
		 
	 	}
	  
        /*Upon loading the window call initialize JavaScript fuction*/
   		google.maps.event.addDomListener(window, 'load', initialize);
  	
      </script>
  </head>
  <body>

  	<div id="map-canvas"></div>
  
 
<!-- Code below reads th list of companies from the request object and displays -->    
   <%
       	
		if(companyList!=null){
		for (Company currentCompany:companyList)
		{
		
			 ByteArrayOutputStream baos = new ByteArrayOutputStream();
			  ImageIO.write(currentCompany.getImg(), "jpg", baos );
			  baos.flush();
			  byte[] imageInByteArray = baos.toByteArray();
			  baos.close();
			  String b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
	%>
	
	
	
	    <form action="addToCompanyList" method="post">
		  <tr valign="top">
		    <td><%= currentCompany.getName()%></td>
		    <td><%= currentCompany.getAddress()%></td>
		    <td><%= currentCompany.getCity()%></td>
		    <td><%= currentCompany.getState()%></td>
		    <td><%= currentCompany.getZipcode()%></td>
		    <td><%= currentCompany.getPhonenumber()%></td>
		    <td><%= currentCompany.getEmail()%></td>
		    <td><%= currentCompany.getDescription()%></td>
		    <td><%= currentCompany.getOwner()%></td>
		    <td><img src="data:image/jpg;base64, <%=b64%>" alt="No Company Logo Added" style="max-height: 100px; max-width: 100px;"/></td>
		    <td><input type="hidden" name="name" value="<%= currentCompany.getName()%>"></td>
		  </tr>
		  </form>
		  
	<%}}
	%>
  </body>
</html>
	<a href="addToCompanyList">Login</a>
</body>
</html>