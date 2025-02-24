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
    	
    	function validateCheckBox(){
    		let checkboxes = document.querySelectorAll("input[name='selected_ids']:checked");
    		
    		if(checkboxes.length === 0){
                alert("Please select a record to update.");
                return false;
            }
            if (checkboxes.length > 1) {
                alert("You can only select one record to update.");
                return false;
            }
            let userId = checkboxes[0].value;
            window.location.href = "FormServlet?action=edit&userId=" + userId;
        }
    	
    	function deleteSelectedUsers() {
    	    let checkboxes = document.querySelectorAll("input[name='selected_ids']:checked");
    	    
    	    if (checkboxes.length === 0) {
    	        alert("Please select at least one record to delete.");
    	        return false;
    	    }
    	    
    	    let confirmed = confirm("Are you sure you want to delete the selected user(s)?");
    	    if (!confirmed) return false;
    	    
    	    let selectedIds = Array.from(checkboxes)
    	    					   .map(cb => cb.value)
    	    					   .join(",");
    	   	window.location.href = "FormServlet?action=delete&userIds=" + selectedIds;
    	}
   	
    </script>
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
                    if (users != null) {
                        for (User user : users) {
                %>
                <tr class="table_row_content">
                    <td class="table_cell"><input type="checkbox" name="selected_ids" value="<%= user.getUserId() %>"></td>
                    <td class="table_cell"><%= user.getUserId() %></td>
                    <td class="table_cell"><%= user.getFirstName() %></td>
                    <td class="table_cell"><%= user.getLastName() %></td>
                    <td class="table_cell"><%= user.getDob() %></td>
                    <td class="table_cell"><%= user.getGender() %></td>
                    <td class="table_cell"><%= user.getAadhar() %></td>
                    <td class="table_cell"><%= user.getPan() %></td>
                    <td class="table_cell"><%= user.getEmail() %></td>
                    <td class="table_cell"><%= user.getPhone() %></td>
                    <td class="table_cell"><%= user.getAddress1() %></td>
                    <td class="table_cell"><%= user.getAddress2() %></td>
                    <td class="table_cell"><%= user.getDistrict() %></td>
                    <td class="table_cell"><%= user.getState() %></td>
                    <td class="table_cell"><%= user.getCountry() %></td>
                    <td class="table_cell"><%= user.getPincode() %></td>
                    <td class="table_cell"><%= user.getPassword() %></td>
                </tr>
                <%  } } %>
            </table>
            
            <div class="btn">
                <button class="upd_btn" type="button" onclick="validateCheckBox()">Update</button>
                <button class="del_btn" type="button" onclick="deleteSelectedUsers()">Delete</button>

            </div>
            
            <% String message = (String) request.getAttribute("message");
			   if (message != null) { %>
			   <script>
			       alert("<%= message %>");
			   </script>
			<% } %>
        </form>
    </div>
</body>
</html>
