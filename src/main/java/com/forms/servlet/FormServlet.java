package com.forms.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.forms.user.User;

@WebServlet("/FormServlet")
public class FormServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/form_registration_db";
    private static final String JDBC_USER = "vvn";
    private static final String JDBC_PSWD = "root";

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } 
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	    	
    	String action = request.getParameter("action");
    	String userIdParam = request.getParameter("user_id");

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

    	try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PSWD)) {
    		if (userIdParam != null && !userIdParam.isEmpty()) {
   	            int userId = Integer.parseInt(userIdParam);
   	            String updateQuery = "UPDATE user_details SET first_name=?, last_name=?, date_of_birth=?, gender=?, aadhar_no=?, pan_no=?, email=?, phone=?, address1=?, address2=?, district=?, state=?, country=?, pincode=?, password=? WHERE user_id=?";
    	            
   	            try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
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
    	            stmt.setInt(16, userId);
    	            
    	            int rowsUpdated = stmt.executeUpdate();
    	            if (rowsUpdated > 0) {
    	                request.setAttribute("message", "Update Successful!");
    	            } 
    	            else {
    	                request.setAttribute("message", "Update Failed!");
    	            }
    	            RequestDispatcher dispatcher = request.getRequestDispatcher("forms.jsp");
    	            dispatcher.forward(request, response);

   	            }

    	    }
            else if ("submit".equals(action)) {
            	String insertQuery = "INSERT INTO user_details (first_name, last_name, date_of_birth, gender, aadhar_no, pan_no, email, phone, address1, address2, district, state, country, pincode, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            	try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
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
            		if (rowsInserted > 0) {
            		    request.setAttribute("message", "Registration Successful!");
            		} 
            		else {
            		    request.setAttribute("message", "Registration Failed!");
            		}
            		RequestDispatcher dispatcher = request.getRequestDispatcher("forms.jsp");
            		dispatcher.forward(request, response);
            	}
            }
			
            else { 
            	response.sendRedirect("forms_view.jsp?error=invalid_action");
            }
    	}
 
    	catch (SQLException e) {
    		e.printStackTrace();
    		response.sendRedirect("forms_view.jsp?error=db_error");
    	}
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        String userIdsParam = request.getParameter("userIds");
        
        
        if ("edit".equals(action)) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String query = "SELECT * FROM user_details WHERE user_id = ?";

            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PSWD);
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, userId);
                ResultSet rst = stmt.executeQuery();

                if (rst.next()) {
                    User user = new User(
                        rst.getInt("user_id"),
                        rst.getString("first_name"),
                        rst.getString("last_name"),
                        rst.getString("date_of_birth"),
                        rst.getString("gender"),
                        rst.getString("aadhar_no"),
                        rst.getString("pan_no"),
                        rst.getString("email"),
                        rst.getString("phone"),
                        rst.getString("address1"),
                        rst.getString("address2"),
                        rst.getString("district"),
                        rst.getString("state"),
                        rst.getString("country"),
                        rst.getString("pincode"),
                        rst.getString("password")
                    );
                    request.setAttribute("selectedUser", user);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("forms.jsp");
                    dispatcher.forward(request, response);
                }
                else {
                    response.sendRedirect("forms_view.jsp?error=user_not_found");
                }
            } 
            catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("forms_view.jsp?error=db_error");
            }

        } 
        else if("delete".equals(action)) {

            if (userIdsParam != null && !userIdsParam.isEmpty()) {
                String[] userIds = userIdsParam.split(",");
                String placeholders = String.join(",", Collections.nCopies(userIds.length, "?"));

                String deleteQuery = "DELETE FROM user_details WHERE user_id IN (" + placeholders + ")";

                try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PSWD);
                		PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {

                    for (int i = 0; i < userIds.length; i++) {
                        stmt.setInt(i + 1, Integer.parseInt(userIds[i]));
                    }
                    int rowsDeleted = stmt.executeUpdate();
                    if (rowsDeleted > 0) {
                        request.setAttribute("message", "Users Deleted Successfully!");
                    } else {
                        request.setAttribute("message", "Deletion Failed!");
                    }

                    RequestDispatcher dispatcher = request.getRequestDispatcher("FormServlet?action=view");
                    dispatcher.forward(request, response);

                } catch (SQLException e) {
					e.printStackTrace();
					response.sendRedirect("forms_view.jsp?error=db_error");
				}
            }
            else {
                request.setAttribute("message", "No Users Selected!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("forms_view.jsp");
                dispatcher.forward(request, response);
            }
        }

        else if("view".equals(action)){
            List<User> users = new ArrayList<>();
            String query = "SELECT * FROM user_details";

            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PSWD);
                 PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rst = stmt.executeQuery()) {

                while (rst.next()) {
                    User user = new User(
                        rst.getInt("user_id"),
                        rst.getString("first_name"),
                        rst.getString("last_name"),
                        rst.getString("date_of_birth"),
                        rst.getString("gender"),
                        rst.getString("aadhar_no"),
                        rst.getString("pan_no"),
                        rst.getString("email"),
                        rst.getString("phone"),
                        rst.getString("address1"),
                        rst.getString("address2"),
                        rst.getString("district"),
                        rst.getString("state"),
                        rst.getString("country"),
                        rst.getString("pincode"),
                        rst.getString("password")
                    );
                    users.add(user);
                }
                request.setAttribute("userList", users);
                RequestDispatcher dispatcher = request.getRequestDispatcher("forms_view.jsp");
                dispatcher.forward(request, response);
            }
            catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("forms_view.jsp?error=db_error");
            }
        }
    }
}
