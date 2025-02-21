<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0">
	<title>Registration Details</title>
	<link rel="stylesheet" href="view_styles.css">
</head>
<body>
	
	<div class="table_div">
		<h1 id="user_details">User Details</h1>
		
		<form class="form_container" action="FormServlet" method="post">
		
			<table class="user_table">
				<tr class="table_row_header">
					<th class="table_header">Select</th>
					<th class="table_header">User ID</th>
					<th class="table_header">First Name</th>
					<th class="table_header">Last Name</th>
					<th class="table_header">Date of Birth</th>
					<th class="table_header">Gender</th>
					<th class="table_header">Aadhar Number</th>
					<th class="table_header">PAN Number</th>
					<th class="table_header">E-Mail</th>
					<th class="table_header">Phone Number</th>
					<th class="table_header">Address 1</th>
					<th class="table_header">Address 2</th>
					<th class="table_header">District</th>
					<th class="table_header">State</th>
					<th class="table_header">Country</th>
					<th class="table_header">Pin-Code</th>
					<th class="table_header">Password</th>
				</tr>
				<%
					final String JDBCURL = "jdbc:mysql://localhost:3306/form_registration_db";
					final String JDBC_USER = "vvn";
					final String JDBC_PSWD = "root";
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					Connection conn = DriverManager.getConnection(JDBCURL,JDBC_USER,JDBC_PSWD);
					Statement stmnt = conn.createStatement();
					ResultSet rst = stmnt.executeQuery("SELECT * FROM user_details");
					while(rst.next()){
				%>
				<tr class="table_row_content">
					<td class="table_cell"> <input type="checkbox" name="selected_ids" value="<%= rst.getInt("user_id") %>"></td>
	                <td class="table_cell"><%= rst.getInt("user_id") %></td>
	                <td class="table_cell"><%= rst.getString("first_name") %></td>
	                <td class="table_cell"><%= rst.getString("last_name") %></td>
	                <td class="table_cell"><%= rst.getString("date_of_birth") %></td>
	                <td class="table_cell"><%= rst.getString("gender")%></td>
	                <td class="table_cell"><%= rst.getString("aadhar_no") %></td>
	                <td class="table_cell"><%= rst.getString("pan_no") %></td>
	                <td class="table_cell"><%= rst.getString("email") %></td>
	                <td class="table_cell"><%= rst.getString("phone") %></td>
	                <td class="table_cell"><%= rst.getString("address1") %></td>
	                <td class="table_cell"><%= rst.getString("address2") %></td>
	                <td class="table_cell"><%= rst.getString("district") %></td>
	                <td class="table_cell"><%= rst.getString("state") %></td>
	                <td class="table_cell"><%= rst.getString("country") %></td>
	                <td class="table_cell"><%= rst.getString("pincode") %></td>
	                <td class="table_cell"><%= rst.getString("password") %></td>
	            </tr>	
				<% }
					conn.close();
					stmnt.close();
					rst.close();
				%>
				
			</table>
		<div class="btn">
			<button class="upd_btn" type="submit" name="action" value="update">Update</button>
			<button class="del_btn" type="submit" name="action" value="delete">Delete</button>
		</div>	
		</form>
	</div>
</body>
</html>