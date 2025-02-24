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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    	
    	checkNullField(firstName, request, response);
    	checkEmptyField(firstName, request, response);
    	
    	checkNullField(lastName, request, response);
    	checkEmptyField(lastName, request, response);
    	
    	checkNullField(dob, request, response);
    	checkEmptyField(dob, request, response);
    	
    	checkNullField(gender, request, response);
    	checkEmptyField(gender, request, response);
    	
    	checkNullField(aadhar, request, response);
    	checkEmptyField(aadhar, request, response);
    	
    	checkNullField(pan, request, response);
    	checkEmptyField(pan, request, response);
    	
    	checkNullField(email, request, response);
    	checkEmptyField(email, request, response);
    	
    	checkNullField(phone, request, response);
    	checkEmptyField(phone, request, response);

    	checkNullField(address1, request, response);
    	checkEmptyField(address1, request, response);

    	checkNullField(district, request, response);
    	checkEmptyField(district, request, response);

    	checkNullField(state, request, response);
    	checkEmptyField(state, request, response);

    	checkNullField(country, request, response);
    	checkEmptyField(country, request, response);

    	checkNullField(pincode, request, response);
    	checkEmptyField(pincode, request, response);

    	checkNullField(password, request, response);
    	checkEmptyField(password, request, response);
    	
    	checkValidAadhar(aadhar,request,response);
    	
    	checkValidPAN(pan,request,response);
    	
    	checkValidEmail(email,request,response);
    	
    	checkValidPhone(phone,request,response);
    	
    	checkPasswordStrength(password,request,response);
    	
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

    public void checkNullField(String field,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(field==null) {
    		request.setAttribute("message", "All fields marked with * are required.");
    	    RequestDispatcher dispatcher = request.getRequestDispatcher("forms.jsp");
    	    dispatcher.forward(request, response);
    	    return;
    	}
    }
    
    public void checkEmptyField(String field,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(field.trim().isEmpty()) {
    		request.setAttribute("message", "All fields marked with * are required.");
    	    RequestDispatcher dispatcher = request.getRequestDispatcher("forms.jsp");
    	    dispatcher.forward(request, response);
    	    return;
    	}
    }

    public void checkValidEmail(String email,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    	Pattern pattern = Pattern.compile(emailRegex);
    	Matcher matcher = pattern.matcher(email);
    	if(!(matcher.matches())) {
    		request.setAttribute("message", "Invalid email format.");
    		RequestDispatcher dispatcher = request.getRequestDispatcher("forms.jsp");
    		dispatcher.forward(request, response);
    		return;
    	}
    }

    public void checkValidPhone(String phone,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	if(!(phone.matches("\\d{10}"))) {
    		request.setAttribute("message", "Phone number must be 10 digits.");
    	    RequestDispatcher dispatcher = request.getRequestDispatcher("forms.jsp");
    	    dispatcher.forward(request, response);
    	    return;
    	}
    }

    public void checkValidAadhar(String aadhar,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	if(!(aadhar.matches("\\d{12}"))) {
    		request.setAttribute("message", "Aadhar number must be 12 digits.");
    	    RequestDispatcher dispatcher = request.getRequestDispatcher("forms.jsp");
    	    dispatcher.forward(request, response);
    	    return;
    	}
    }
    
    public void checkValidPAN(String pan,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String panRegex = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$";
    	Pattern pattern = Pattern.compile(panRegex);
    	Matcher matcher = pattern.matcher(pan);
    	if(!(matcher.matches())) {
    		request.setAttribute("message", "Invalid PAN format.(e.g., ABCDE1234F)");
    		RequestDispatcher dispatcher = request.getRequestDispatcher("forms.jsp");
    		dispatcher.forward(request, response);
    		return;
    	}
    }
 
    public void checkPasswordStrength(String password,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,20}$";
    	Pattern pattern = Pattern.compile(passwordRegex);
    	Matcher matcher = pattern.matcher(password);
    	if(!(matcher.matches())) {
    		request.setAttribute("message", "Password must be at least 8 characters with 1 uppercase letter and 1 number.");
    		RequestDispatcher dispatcher = request.getRequestDispatcher("forms.jsp");
    		dispatcher.forward(request, response);
    		return;
    	}
    }
}
