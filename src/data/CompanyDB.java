/* CompanyDB.java
 * Company DB Class adds, removes, changes, and checks whether companies are present
 * From the connection pool gets a connection and changes the SQL table
 * 
 */

package data;

import java.sql.*;
import java.util.ArrayList;

import business.Company;

public class CompanyDB
{
    public static int insert(Company company)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = 
                "INSERT INTO Company (Name, Latitude, Longitude, Address, City, State, Zipcode, Phonenumber, Email, Description, Owner) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try
        {        
            ps = connection.prepareStatement(query);
                      
            ps.setString(1, company.getName());
            ps.setString(2, company.getLatitude());
            ps.setString(3, company.getLongitude());
            ps.setString(4, company.getAddress());
            ps.setString(5, company.getCity());
            ps.setString(6, company.getState());
            ps.setString(7, company.getZipcode());
            ps.setString(8, company.getPhonenumber());
            ps.setString(9, company.getEmail());
            ps.setString(10, company.getDescription());
            ps.setString(11, company.getOwner());
               
            return ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
        finally
        {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int update(Company company)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE Company SET " +
                "Latitude = ?, " +
                "Longitude = ? " +
                "Address = ?, " +
                "City = ?, " +
                "State = ?, " +
                "Zipcode = ?, " +
                "Phonenumber = ?, " +
                "Email = ?, " +
                "Description = ?, " +
                "Owner = ?, " +
                "WHERE Name = ?";
        
              
        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, company.getName());
            ps.setString(2, company.getCity());
            ps.setString(3, company.getState());

            return ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
        finally
        {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int delete(String companyName)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "DELETE FROM company " +
                "WHERE Name = ?";
        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, companyName);

            return ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
        finally
        {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    /*
    public static boolean emailExists(String emailAddress)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT EmailAddress FROM User " +
                "WHERE EmailAddress = ?";
        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, emailAddress);
            rs = ps.executeQuery();
            return rs.next();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    */
    public static ArrayList<Company> selectCompany()
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM Company";
        try
        {
            ps = connection.prepareStatement(query);
        //    ps.setString(1, emailAddress);
            rs = ps.executeQuery();
            
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
     
            
            ArrayList<Company> companyList = new ArrayList<Company>(7);
            
            Company company = null;
            while (rs.next())
            {
            	company = new Company();
            	company.setName(rs.getString("Name"));
                System.out.println(rs.getString("Name"));
            	company.setLatitude(rs.getString("Latitude"));
            	company.setLongitude(rs.getString("Longitude"));
            	company.setAddress(rs.getString("Address"));
            	company.setCity(rs.getString("City"));
            	company.setState(rs.getString("State"));
              	company.setZipcode(rs.getString("Zipcode"));
              	company.setPhonenumber(rs.getString("Phonenumber"));
              	company.setEmail(rs.getString("Email"));
              	company.setDescription(rs.getString("Description"));
              	company.setOwner(rs.getString("Owner"));
         
              	companyList.add(company);
            }
            return companyList;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }        
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }


    public static Company selectUser(String name)
    {
    	   ConnectionPool pool = ConnectionPool.getInstance();
           Connection connection = pool.getConnection();
           PreparedStatement ps = null;
           ResultSet rs = null;
           
           String query = "SELECT * FROM company " +
                          "WHERE Name = ?";
           try
           {
               ps = connection.prepareStatement(query);
               ps.setString(1, name);
               rs = ps.executeQuery();
               Company company = null;
               if (rs.next())
               {
                    company = new Company();
                    company.setName(rs.getString("Name"));
                	company.setCity(rs.getString("Latitude"));
                	company.setState(rs.getString("Longitude"));
                	company.setName(rs.getString("Address"));
                	company.setCity(rs.getString("City"));
                	company.setState(rs.getString("State"));
                 	company.setState(rs.getString("Zipcode"));
                 	company.setState(rs.getString("Phonenumber"));
                 	company.setState(rs.getString("Email"));
                 	company.setState(rs.getString("Description"));
                 	company.setState(rs.getString("Owner"));               }
        
                    return company;
           }
           catch (SQLException e){
               e.printStackTrace();
               return null;
           }        
           finally
           {
               DBUtil.closeResultSet(rs);
               DBUtil.closePreparedStatement(ps);
               pool.freeConnection(connection);
           }
   }
    
    
}