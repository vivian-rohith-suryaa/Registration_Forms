package com.forms.util;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidationUtil {

	 public static boolean checkNullField(String field,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		 
		 if(field==null) {
	    	request.setAttribute("message", "All fields marked with * are required.");
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("User_Form.jsp");
	    	dispatcher.forward(request, response);
	    	return true;
	    }
		 return false;
	 } 
	 
	 public static boolean checkEmptyField(String field,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		 
		 checkNullField(field, request, response);
		 
		 if(field.trim().isEmpty()) {
			 request.setAttribute("message", "All fields marked with * are required.");
			 RequestDispatcher dispatcher = request.getRequestDispatcher("User_Form.jsp");
			 dispatcher.forward(request, response);
			 return true;
		 }
		 return false;
	 } 
	
	 public static boolean checkValidEmail(String email,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 checkEmptyField(email,request,response);

		 String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		 Pattern pattern = Pattern.compile(emailRegex);
		 Matcher matcher = pattern.matcher(email);
		 if(!(matcher.matches())) {
			 request.setAttribute("message", "Invalid email format.");
			 RequestDispatcher dispatcher = request.getRequestDispatcher("User_Form.jsp");
			 dispatcher.forward(request, response);
			 return true;
		 }
		 return false;
	 }

	 public static boolean checkValidPhone(String phone,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		 checkEmptyField(phone,request,response);
		 if(!(phone.matches("\\d{10}"))) {
			 request.setAttribute("message", "Phone number must be 10 digits.");
			 RequestDispatcher dispatcher = request.getRequestDispatcher("User_Form.jsp");
			 dispatcher.forward(request, response);
			 return true;
		 }
		 return false;
	 }

	 public static boolean checkValidAadhar(String aadhar,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 checkEmptyField(aadhar,request,response);
		 if(!(aadhar.matches("\\d{12}"))) {
			 request.setAttribute("message", "Aadhar number must be 12 digits.");
			 RequestDispatcher dispatcher = request.getRequestDispatcher("User_Form.jsp");
			 dispatcher.forward(request, response);
			 return true;
		 }
		 return false;
	 }
	    
	 public static boolean checkValidPAN(String pan,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		 checkEmptyField(pan,request,response);
		 String panRegex = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$";
		 Pattern pattern = Pattern.compile(panRegex);
		 Matcher matcher = pattern.matcher(pan);
		 if(!(matcher.matches())) {
			 request.setAttribute("message", "Invalid PAN format.(e.g., ABCDE1234F)");
			 RequestDispatcher dispatcher = request.getRequestDispatcher("User_Form.jsp");
			 dispatcher.forward(request, response);
			 return true;
		 }	
		 return false;
	 }
	 
	 public static boolean checkPasswordStrength(String password,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 checkEmptyField(password,request,response);
		 String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,20}$";
		 Pattern pattern = Pattern.compile(passwordRegex);
		 Matcher matcher = pattern.matcher(password);
		 if(!(matcher.matches())) {
			 request.setAttribute("message", "Password must be at least 8 characters with 1 uppercase letter and 1 number.");
			 RequestDispatcher dispatcher = request.getRequestDispatcher("User_Form.jsp");
			 dispatcher.forward(request, response);
			 return true;
		 }	
		 return false;
	 }
	    
	 public static boolean matchPassword(String password, String confirmPassword,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 checkEmptyField(password,request,response);
		 checkEmptyField(confirmPassword,request,response);
		 
		 if(!(confirmPassword.equals(password))) {
			 request.setAttribute("message", "Passwords do not match.");
			 RequestDispatcher dispatcher = request.getRequestDispatcher("User_Form.jsp");
			 dispatcher.forward(request, response);
			 return true;
		 }
		 return false;
	 }
}
