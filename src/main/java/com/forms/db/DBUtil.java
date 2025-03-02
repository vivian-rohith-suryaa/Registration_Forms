package com.forms.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import com.forms.user.User;

public class DBUtil {
	
	private Connection getConnection() {
		Connection conn = null;	
		try {
			 Context ctx = new InitialContext();
			 DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDB");
			 conn = ds.getConnection();
	        }
		catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public boolean updateUser(User user){
//		int userId = Integer.parseInt(userIdParam);
		String updateQuery = "UPDATE user_details SET first_name=?, last_name=?, date_of_birth=?, gender=?, aadhar_no=?, pan_no=?, email=?, phone=?, address1=?, address2=?, district=?, state=?, country=?, pincode=?, password=? WHERE user_id=?";
		
		try (Connection conn = getConnection();
			 PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
			
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getDob());
			stmt.setString(4, user.getGender());
			stmt.setString(5, user.getAadhar());
			stmt.setString(6, user.getPan());
			stmt.setString(7, user.getEmail());
			stmt.setString(8, user.getPhone());
			stmt.setString(9, user.getAddress1());
			stmt.setString(10, user.getAddress2());
			stmt.setString(11, user.getDistrict());
			stmt.setString(12, user.getState());
			stmt.setString(13, user.getCountry());
			stmt.setString(14, user.getPincode());
			stmt.setString(15, user.getPassword());
			stmt.setInt(16, user.getUserId());
			
			int rowsUpdated = stmt.executeUpdate();
			return (rowsUpdated > 0); 	
		}
		catch (SQLException e) {		
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean submitUser(User user){
		
		String insertQuery = "INSERT INTO user_details (first_name, last_name, date_of_birth, gender, aadhar_no, pan_no, email, phone, address1, address2, district, state, country, pincode, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	
		try (Connection conn = getConnection();
    		 PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
    	
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getDob());
			stmt.setString(4, user.getGender());
			stmt.setString(5, user.getAadhar());
			stmt.setString(6, user.getPan());
			stmt.setString(7, user.getEmail());
			stmt.setString(8, user.getPhone());
			stmt.setString(9, user.getAddress1());
			stmt.setString(10, user.getAddress2());
			stmt.setString(11, user.getDistrict());
			stmt.setString(12, user.getState());
			stmt.setString(13, user.getCountry());
			stmt.setString(14, user.getPincode());
			stmt.setString(15, user.getPassword());
			
    		int rowsInserted = stmt.executeUpdate();
    		return(rowsInserted > 0);
    	} 
    	catch (SQLException e) {
    		e.printStackTrace();
    		return false;
    		 
		}
	
	}
	
	public List<User> displayUserTable(){
		
		List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user_details";

        try (Connection conn = getConnection();
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
            return users;
        }
        catch (SQLException e) {
        	e.printStackTrace();
        }
        return null;
	}

	public boolean deleteUser(int userId ) {
        String deleteQuery = "DELETE FROM user_details WHERE user_id = ?";

        try (Connection conn = getConnection();
        	 PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {
        	
        	stmt.setInt(1, userId);
                
        	int rowsDeleted = stmt.executeUpdate();
        	return (rowsDeleted > 0);
            }
        catch (SQLException e) {
        	e.printStackTrace();
        	return false;
		}
	}

	public User setUserInfo(int userId ) {
        String query = "SELECT * FROM user_details WHERE user_id = ?";

        try (Connection conn = getConnection(); 
        	 PreparedStatement stmt = conn.prepareStatement(query)) {
        	
            stmt.setInt(1, userId);
            
            try(ResultSet rst = stmt.executeQuery()){

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
	                return user;
	            }
            }
        } 
        catch (SQLException e) {
        	e.printStackTrace();
        }
        return null;
	}
}
