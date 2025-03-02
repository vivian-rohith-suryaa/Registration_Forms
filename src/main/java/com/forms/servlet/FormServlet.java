package com.forms.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.forms.db.DBUtil;
import com.forms.exception.InvalidInputException;
import com.forms.util.ValidationUtil;
import com.forms.user.User;

@WebServlet("/FormServlet")
public class FormServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    DBUtil dbWorker = new DBUtil();
    boolean result = false;
    User user;
    
    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } 
        catch (ClassNotFoundException e) {
        	throw new ServletException("Driver Class not found!!!!!");
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	    	
    	String action = request.getParameter("action");
    	
    	String userIdParam = request.getParameter("user_id");
    	int userId = 0;

    	if (userIdParam != null && !userIdParam.isEmpty()) {
    	    userId = Integer.parseInt(userIdParam);
    	}

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
    	String confirmPassword = request.getParameter("confirmpassword");
	    	
    	try {
	    	ValidationUtil.checkEmptyField(firstName);
		    	
	    	ValidationUtil.checkEmptyField(lastName);
		    	
	    	ValidationUtil.checkEmptyField(dob);
		    	
	    	ValidationUtil.checkEmptyField(gender);
		    	
	    	ValidationUtil.checkEmptyField(aadhar);
		    	
	    	ValidationUtil.checkEmptyField(pan);
		    	
	    	ValidationUtil.checkEmptyField(email);
		    	
	    	ValidationUtil.checkEmptyField(phone);
		
	    	ValidationUtil.checkEmptyField(password);
	    	
	    	ValidationUtil.checkValidAadhar(aadhar);
	    	
	    	ValidationUtil.checkValidPAN(pan);
		    	
	    	ValidationUtil.checkValidEmail(email);
		    	
	    	ValidationUtil.checkValidPhone(phone);
		    	
	    	ValidationUtil.checkPasswordStrength(password);
	    	
	    	ValidationUtil.matchPassword(password, confirmPassword);
    	}
    	catch(InvalidInputException e) {
    		request.setAttribute("message", e.getMessage());
    	    FormServlet.forwardDispatcher(request, response, "User_Form.jsp");
    	}
	    	
        user = new User(userId, firstName, lastName, dob, gender, aadhar, pan, email, phone, address1,address2, district, state, country, pincode, password);
    	
    	if ("update".equals(action)) {
    		result = dbWorker.updateUser(user);
    		if(result) {
    			request.setAttribute("message", "Update Successful");
    		}
    		else {
    			request.setAttribute("message", "Update Failed");
    		}
    		FormServlet.forwardDispatcher(request, response,"User_Form.jsp");
    	}
    	else if ("submit".equals(action)) {
    		result = dbWorker.submitUser(user);
    		if(result) {
    			request.setAttribute("message", "Registration Successful");
    		}
    		else {
    			request.setAttribute("message", "Registation Failed");
    		}
    		FormServlet.forwardDispatcher(request, response,"User_Form.jsp");
    	}
			
    	else { 
    		request.setAttribute("message","Invalid Action!!!!");
    		FormServlet.forwardDispatcher(request, response,"User_Form.jsp");
    	}
    }
 

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        
        if ("edit".equals(action)) {
        	int userId = Integer.parseInt(request.getParameter("userId"));
        	user = dbWorker.setUserInfo(userId);
        	request.setAttribute("selectedUser", user);
        	FormServlet.forwardDispatcher(request, response,"User_Form.jsp");

        } 
        else if("delete".equals(action)) {
        	int userId = Integer.parseInt(request.getParameter("userId"));
        	result = dbWorker.deleteUser(userId);
        	if(result) {
    			request.setAttribute("message", "Users Deleted Successfully!");
    		}
    		else {
    			request.setAttribute("message", "Deletion Failed");
    		}
    		FormServlet.forwardDispatcher(request, response,"FormServlet?action=view");
        }

        else if("view".equals(action)){
        	List<User> users = dbWorker.displayUserTable();
        	request.setAttribute("userList", users);
        	FormServlet.forwardDispatcher(request, response, "User_Table.jsp");
        }
    }
    
    public static void forwardDispatcher(HttpServletRequest request, HttpServletResponse response, String JSPName) {
    	RequestDispatcher dispatcher = request.getRequestDispatcher(JSPName);
    	try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
    }

}

