<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import ="business.*,java.util.ArrayList"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.*"%>
<html>
<head>
    <title>Welcome to MarketMaps</title>

</head>

	<%
	response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
	%>

<body>
	<h1>Welcome to MarketMaps</h1>
	<%
	ArrayList<Company> companyList = (ArrayList<Company>) request.getAttribute("companyList");
	%>

<table border="1" cellpadding="5">
<tr>
  <th>Name</th>
  <th>Address</th>
  <th>City</th>
  <th>State</th>
  <th>Zipcode</th>
  <th>Phonenumber</th>
  <th>Email</th>
  <th>Description</th>
  <th>Owner</th>
  <th></th>
</tr>

	<%
	if(companyList!=null){
	for (Company currentCompany:companyList)
	{
	%>
		  <%
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
		    <td><input type="submit" name="removeCompany" value="Remove"></td>
		  </tr>
		  </form>
	<%}}
	%>

</table>
<a href="/MarketMaps/add_company.jsp">Add a company</a>
<a href="/MarketMaps/logout.jsp">Logout</a>
</body>

</html>
