<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.forms.user.User" %>

<% 
	List<User> users = (List<User>) request.getAttribute("userList");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Registration Details</title>
    <link rel="stylesheet" href="view_styles.css">
    
    <script>
    	
    	function updateUser(userId){
            window.location.href = "FormServlet?action=edit&userId=" + userId;
        }
    	
    	function deleteUser(userId) {
    	    let confirmed = confirm("Are you sure you want to delete the selected user(s)?");
    	    if (!confirmed) return false;
    	   	window.location.href = "FormServlet?action=delete&userId=" + userId;
    	}
    	
    	function redirectToForms(){
    		window.location.href = "forms.jsp";
    	}
   	
    </script>
</head>
<body>

	<header>
		<div class="banner_holder">
			<div class="banner_left">
				<div class="logo_img_holder">
					<img class="logo-image" src="images/zoho_logo.jpg" alt="Logo">
				</div>
				<h1 id="title">Z-Register</h1>
			</div>
			<div class="banner_right">
				<img id="user_add_id" alt="Add User" src="images/user_add.svg" onclick="redirectToForms()" title="Add User">
			</div>
		</div>
	</header>

    <div class="table_div">
        <h1 id="user_details">User Details</h1>
        	<table class="user_table">
                <tr class="table_row_header">
                    <th class="table_header">User ID</th>
                    <th class="table_header">First Name</th>
                    <th class="table_header">Last Name</th>
                    <th class="table_header">Date of Birth</th>
                    <th class="table_header">Gender</th>
                    <th class="table_header">Aadhar Number</th>
                    <th class="table_header">PAN Number</th>
                    <th class="table_header">E-Mail</th>
                    <th class="table_header">Phone Number</th>
                    <th class="table_header" style="text-align: center;">Action</th>

                </tr>
                <%
                    if (users != null) {
                        for (User user : users) {
                %>
                <tr class="table_row_content">
                    <td class="table_cell"><%= user.getUserId() %></td>
                    <td class="table_cell"><%= user.getFirstName() %></td>
                    <td class="table_cell"><%= user.getLastName() %></td>
                    <td class="table_cell"><%= user.getDob() %></td>
                    <td class="table_cell"><%= user.getGender() %></td>
                    <td class="table_cell"><%= user.getAadhar() %></td>
                    <td class="table_cell"><%= user.getPan() %></td>
                    <td class="table_cell"><%= user.getEmail() %></td>
                    <td class="table_cell"><%= user.getPhone() %></td>
                    <td class="table_cell">
                    	<div class="btn" >
                			<img id="edit_id" alt="Update" src="images/edit.svg" onclick="updateUser(<%= user.getUserId() %>)" title="Update User">
                			<img id="bin_id" alt="Delete" src="images/bin.svg" onclick="deleteUser(<%= user.getUserId() %>)" title="Delete User">
                		</div> 
                	</td>
                </tr>
                <%  } } %>
            </table>
            
            
            <% String message = (String) request.getAttribute("message");
			   if (message != null) { %>
			   <script>
			       alert("<%= message %>");
			   </script>
			<% } %>
    </div>
</body>
</html>
