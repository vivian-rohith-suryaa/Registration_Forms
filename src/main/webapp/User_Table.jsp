<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.forms.user.User" %>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
%>

<% 
	List<User> users = (List<User>) request.getAttribute("userList");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Registration Details</title>
    <link rel="stylesheet" href="table_styles.css">
    
    <script>
    	
    	function updateUser(userId){
            window.location.href = "FormServlet?action=edit&userId=" + userId;
        }
    	
    	function deleteUser(userId) {
    	    let confirmed = confirm("Are you sure you want to delete the selected user?");
    	    if (!confirmed) return false;
    	   	window.location.href = "FormServlet?action=delete&userId=" + userId;
    	}
    	
    </script>
</head>
<body>

	<% request.setAttribute("alternative","Add User"); %>
	<% request.setAttribute("image","user_add.svg"); %>
	<% request.setAttribute("action","window.location.href='User_Form.jsp'"); %>
	<% request.setAttribute("title", "Add-User"); %>
	<% request.setAttribute("icon_name", "Add User"); %>
	
	<%@ include file="Form_Header.jsp"%>

    <div class="table_div">
        <h1 id="user_details">User Details</h1>
        	<table class="user_table">
                <tr class="table_row_header">
                	<th class="table_header">S.NO</th>
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
                	int serialNo = 1;
                    if (users != null) {
                        for (User user : users) {
                %>
                <tr class="table_row_content">
                	<td class="table_cell"><%=serialNo++ %></td>
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
    </div>
</body>
</html>
