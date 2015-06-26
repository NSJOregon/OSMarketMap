<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import ="business.*,java.util.ArrayList"%>
<html>

<head>
    <title>Welcome to MarketMaps</title>

</head>

<body>
<h1>Welcome to MarketMaps</h1>
<%
ArrayList<Company> companyList = (ArrayList<Company>) request.getAttribute("companyList");


//ArrayList<Company> company2List = CompanyDB.selectCompany();
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
    <td><input type="hidden" name="name" value="<%= currentCompany.getName()%>"></td>
    <td><input type="submit" name="removeButton" value="Remove"></td>
  </tr>
  </form>



<%}}
%>
</table>
<a href="/MarketMaps/add_company.jsp">Add a company</a>

</body>

</html>
