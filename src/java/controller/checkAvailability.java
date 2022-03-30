/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dbCon;

/**
 *
 * @author User
 */
@WebServlet("/checkavailability")
public class checkAvailability extends HttpServlet{
    
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String checkIn = request.getParameter("checkin");
        String checkOut = request.getParameter("checkout");
        String roomType = request.getParameter("roomtype");
        //String adult = request.getParameter("adult");
        //String date = "2022-01-05";
        
        String query = "SELECT * FROM `booking` WHERE checkIn >='" + checkIn +"' AND checkOut <='" + checkOut + "' AND roomType ='" +roomType + "'";
        dbCon dbconn = new dbCon();
        try {
            boolean available = dbconn.queryBookings(query);
            if(available==true){
                //booking form
               
                RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/tryagain.jsp");
                RequetsDispatcherObj.forward(request, response);
                //response.getWriter().print("Room/s is not available for your date\n\n");
                //response.getWriter().print("Please try another date\n\n");

            }else{
                
                HttpSession session = request.getSession();
                session.setAttribute("checkin", checkIn);
                session.setAttribute("checkout", checkOut);
                session.setAttribute("roomtype", roomType);
                 RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/booking.jsp");
                RequetsDispatcherObj.forward(request, response);
               
            }
  
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(booking.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
