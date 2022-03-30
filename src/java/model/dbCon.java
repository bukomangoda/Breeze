/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import static java.lang.System.out;
import java.sql.*;


/**
 *
 * @author bhasi
 */
public class dbCon {
    
    public Connection createConnection() throws ClassNotFoundException, SQLException
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing","root","");
                return con;
                
            }
    
    public Boolean queryBookings(String query) throws ClassNotFoundException, SQLException{
        Connection con = createConnection();
        PreparedStatement ps;
        String result = "query successfully!";
        boolean available = false;
        
        try{
        ps = con.prepareStatement(query);
        ResultSet resultSet = ps.executeQuery();
        
        while(resultSet.next()) {
            available = true;
    out.println("<tr>");
    out.println("<td>"+resultSet.getString(1) + "</td>");
    out.println("<td>"+resultSet.getString(2) + "</td>");
    out.println("<td>"+resultSet.getString(3) + "</td>");
    out.println("</tr>");

   }
        }
        catch(SQLException e){
            result = "query not success  " + e;
            out.println(e);
        }
        
        return available;
    }
    
    public String insertBooking(Booking booking) throws ClassNotFoundException, SQLException{
        Connection con = createConnection();
        String result = "Data entered successfully!";
        
        String sql = "insert into booking values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps;
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, booking.getFname());
            ps.setString(2, booking.getLname());
            ps.setString(3, booking.getCity());
            ps.setString(4, booking.getPhone());
            ps.setString(5, booking.getEmail());
            ps.setString(6, booking.getCheckIn());
            ps.setString(7, booking.getCheckOut());
            ps.setString(8, booking.getRoomType());
            ps.setString(9, booking.getAdult());
            ps.executeUpdate();
        }
        catch(SQLException e){
            result = "Data not entered  " + e;
        }
        
        return result;
    }
    
    public  boolean feedback(String name, String email, String subject, String message) throws ClassNotFoundException, SQLException 
                {
                    PreparedStatement ps = createConnection().prepareStatement("insert into feedback values(?,?,?,?)");
                    ps.setString(1, name);
                    ps.setString(2, email);
                    ps.setString(3, subject);
                    ps.setString(4, message);
                    int i = ps.executeUpdate();

                    if(i > 0) 
                        return true;
                      else
                        return false;
                    
                }
    
    public  boolean delete(String email, String checkIn, String checkOut) throws ClassNotFoundException, SQLException 
                {
                    
                    PreparedStatement ps = createConnection().prepareStatement("delete from booking where email=? AND checkIn >=? AND checkOut <=?");
                    ps.setString(1, email);
                    ps.setString(2, checkIn);
                    ps.setString(3, checkOut); 
                    int i = ps.executeUpdate();

                    if(i > 0) 
                        return true;
                      else
                        return false;
                    
                }
}
