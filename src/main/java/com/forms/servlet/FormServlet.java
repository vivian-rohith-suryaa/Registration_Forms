package com.forms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FormServlet")

public class FormServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/form_registration_db";
	private static final String JDBC_USER = "vvn";
	private static final String JDBC_PSWD = "root";
	
	@Override
	public void init() throws ServletException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.getMessage();
			e.printStackTrace();
		}
    	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
        String dob = request.getParameter("dateofbirth");
        String gender = request.getParameter("gender");
        String aadhar = request.getParameter("aadhar");
        String pan = request.getParameter("pan");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String district = request.getParameter("district");
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        String pincode = request.getParameter("pincode");
        String password = request.getParameter("setpassword");
		
        request.setAttribute("fname", firstName);
        request.setAttribute("lname", lastName);
        request.setAttribute("dateofbirth", dob);
        request.setAttribute("gender", gender);
        request.setAttribute("aadhar", aadhar);
        request.setAttribute("pan", pan);
        request.setAttribute("email", email);
        request.setAttribute("phone", phone);
        request.setAttribute("address1", address1);
        request.setAttribute("address2", address2);
        request.setAttribute("district", district);
        request.setAttribute("state", state);
        request.setAttribute("country", country);
        request.setAttribute("pincode", pincode);
        request.setAttribute("setpassword", password);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("forms_update.jsp");
        dispatcher.forward(request, response);
        
        
        String dbFields = "INSERT INTO user_details (first_name, last_name, date_of_birth, gender, aadhar_no, pan_no, email, phone, address1, address2, district, state, country, pincode, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
       
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        
        try (
        	Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PSWD);
        	PreparedStatement stmt = conn.prepareStatement(dbFields)
        ){
        	stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, dob);
            stmt.setString(4, gender);
            stmt.setString(5, aadhar);
            stmt.setString(6, pan);
            stmt.setString(7, email);
            stmt.setString(8, phone);
            stmt.setString(9, address1);
            stmt.setString(10, address2);
            stmt.setString(11, district);
            stmt.setString(12, state);
            stmt.setString(13, country);
            stmt.setString(14, pincode);
            stmt.setString(15, password); 

            int rowsInserted = stmt.executeUpdate();
            boolean success= true;
            if(success) {
            	response.sendRedirect("forms_update.jsp?success=true");
            }
            else {
            	response.sendRedirect("forms_update.jsp?success=false");
            }
            
           
        }
        catch (SQLException e) {
        	e.getMessage();
        	e.printStackTrace();
		}
	}
}
