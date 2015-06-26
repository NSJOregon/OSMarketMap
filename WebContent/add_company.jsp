<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

<head>
    <title>MarketMaps add a Company</title>
</head>

<body>

	<form action="addToCompanyList" method="post">
		<table cellspacing="5" border="0">
		    <tr>
		        <td align="right">Company Name:</td>
		        <td><input type="text" name="name" 
		             value="${user.name}">
		        </td>
		    </tr>
		    <tr>
		        <td align="right">City:</td>
		        <td><input type="text" name="city"
		             value="${user.city}">
		        </td>
		    </tr>
		    <tr>
		        <td align="right">State:</td>
		        <td><input type="text" name="state"
		             value="${user.state}">
		        </td>
		     </tr>
		    <tr>
		        <td>
		        <input type="hidden" name="addCompany" value="doAdd">
		        </td>
		        <td><br><input type="submit" value="Submit"></td>
		    </tr>
		</table>
	</form>
</body>

</html>