

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.register;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;


/**
 *
 * @author lenovo
 */
@MultipartConfig
public class Register extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
         
            //getting all incoming details for the request
            
            String name=request.getParameter("user_name");
                        String email=request.getParameter("user_email");
                                    String password=request.getParameter("user_password");
                                 Part part = request.getPart("image");
                                 String filename = part.getSubmittedFileName();
                               //  out.println(filename);


try
{
    Thread.sleep(4000);
    
 Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registerpage","root","#Lenovo1326");
    String sql = "INSERT INTO register_user (name, email, password,imagename) VALUES (?, ?, ?, ?)";
    PreparedStatement pstmt = con.prepareStatement(sql);

    pstmt.setString(1, name);
    pstmt.setString(2, email);
    pstmt.setString(3, password);
     pstmt.setString(4, filename);
     pstmt.executeUpdate();
   
//upload..

//is is just name
InputStream is=part.getInputStream();
byte[]data=new byte[is.available()];

is.read(data);
String path=request.getRealPath("/")+"img"+File.separator+filename;
// out.println(path);

 FileOutputStream fos=new FileOutputStream(path);
 //all data of fos in write
 fos.write(data);
 fos.close();
 

// Close resources (PreparedStatement and Connection) when done
   out.println("done");
   
  }
catch (Exception e)
        {
            e.printStackTrace();
            
            out.println("<h2> error</h2>");
        }


            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
