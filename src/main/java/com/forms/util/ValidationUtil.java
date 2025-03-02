package com.forms.util;

import com.forms.exception.InvalidInputException;

public class ValidationUtil {

	 public static void checkNullField(String field) throws InvalidInputException{
		 
		 if(field==null) {
	    	throw new InvalidInputException("All fields marked with * are required.");
	    }
	 } 
	 
	 public static void checkEmptyField(String field) throws InvalidInputException {
		 
		 checkNullField(field);
		 
		 if(field.trim().isEmpty()) {
			 throw new InvalidInputException("All fields marked with * are required.");
		 }
	 } 
	
	 public static void checkValidEmail(String email) throws InvalidInputException{
		 
		 checkEmptyField(email);

		 if(!(email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))) {
			 throw new InvalidInputException("Invalid email format.");
		 }
	 }

	 public static void checkValidPhone(String phone) throws InvalidInputException {
	    
		 checkEmptyField(phone);
		 if(!(phone.matches("\\d{10}"))) {
			 throw new InvalidInputException("Phone number must be 10 digits.");
		 }
	 }

	 public static void checkValidAadhar(String aadhar) throws InvalidInputException {
		 
		 checkEmptyField(aadhar);
		 if(!(aadhar.matches("\\d{12}"))) {
			 throw new InvalidInputException("Aadhar number must be 12 digits.");
		 }

	 }
	    
	 public static void checkValidPAN(String pan) throws InvalidInputException {
	    
		 checkEmptyField(pan);
		 if(!(pan.matches("^[A-Z]{5}[0-9]{4}[A-Z]{1}$"))) {
			 throw new InvalidInputException("Invalid PAN format.(e.g., ABCDE1234F)");
		 }	
	 }
	 
	 public static void checkPasswordStrength(String password) throws InvalidInputException{
		 
		 checkEmptyField(password);
		 if(!(password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,20}$"))) {
			 throw new InvalidInputException("Password must be at least 8 characters with 1 uppercase letter and 1 number.");
		 }	
	 }
	    
	 public static void matchPassword(String password, String confirmPassword) throws InvalidInputException {
		 
		 checkEmptyField(password);
		 checkEmptyField(confirmPassword);
		 
		 if(!(confirmPassword.equals(password))) {
			 throw new InvalidInputException("Passwords do not match.");
		 }
	 }
}
