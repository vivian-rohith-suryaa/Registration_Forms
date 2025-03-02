<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.forms.user.User"%>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
%>

<%
User user = (User) request.getAttribute("selectedUser");
if (user == null) {
	user = new User();
}
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Account Registration</title>
	<link rel="stylesheet" href="form_styles.css">
</head>
<body>
	
	<% request.setAttribute("alternative","View Users"); %>
	<% request.setAttribute("image","view.svg"); %>
	<% request.setAttribute("action","window.location.href='FormServlet?action=view'"); %>
	<% request.setAttribute("title", "View-Users"); %>
	<% request.setAttribute("icon_name", "View Users"); %>
	
	<%@ include file="Form_Header.jsp"%>

	<main>
		<div class="container">

			<div class="background">
				<img src="images/bg_image.jpg" alt="Registration Image">
			</div>

			<div class="form_div">
				<form class="form_container" action="FormServlet" method="post">

					<h2 id="signup_form">Account Registration Details</h2>

					<input type="hidden" name="user_id" value="<%=user.getUserId() > 0 ? user.getUserId() : ""%>">
					
					<%
					String message = (String) request.getAttribute("message");
					if (message != null) {
					%>
						<div class="error_container"><%= message %></div>
						<% request.removeAttribute(message); %>
					<%
					}
					
					%>

					<div class="form-row">
						<div class="form-group">
							<label class="generic_label" for="fname">First Name:<span style="color: red; font-weight: bold; margin-left: 5px;">*</span></label>
							<input class="form_input" type="text" id="fname" name="fname" value="<%=user.getFirstName() != null ? user.getFirstName() : ""%>" placeholder="First_Name" autofocus required pattern="[A-Za-z.\s]+" title="Only alphabets allowed">
						</div>

						<div class="form-group">
							<label class="generic_label" for="lname">Last Name:</label>
							<input class="form_input" type="text" id="lname" name="lname" value="<%=user.getLastName() != null ? user.getLastName() : ""%>" placeholder="Last_Name" pattern="[A-Za-z.\s]+" title="Only alphabets allowed">
						</div>
						
					</div>

					<div class="form-row">
						<div class="form-group">
							<label class="generic_label" for="dateofbirth">Date of Birth:<span style="color: red; font-weight: bold; margin-left: 5px;">*</span></label>
							<input class="form_input" type="date" id="dateofbirth" name="dateofbirth" value="<%=user.getDob() != null ? user.getDob() : ""%>" placeholder="dd/mm/yyyy" required max="2006-12-31" title="You must be atleat 18 years old">
						</div>

						<div class="form-group">
							<label class="generic_label" for="gender">Gender:<span style="color: red; font-weight: bold; margin-left: 5px;">*</span></label>
							<div class="gender_group">
								<input type="radio" id="male" name="gender" value="Male" <%="Male".equals(user.getGender()) ? "checked" : ""%>>
								<label for="male">Male</label>
								<input type="radio" id="female" name="gender" value="Female" <%="Female".equals(user.getGender()) ? "checked" : ""%>>
								<label for="female">Female</label>
								<input type="radio" id="others" name="gender" value="Others" <%="Others".equals(user.getGender()) ? "checked" : ""%>>
								<label for="others">Others</label>
							</div>
						</div>
					</div>
<!-- pattern="\d{12}"
 -->					<div class="form-row">
						<div class="form-group">
							<label class="generic_label" for="aadhar">Aadhar Number:<span style="color: red; font-weight: bold; margin-left: 5px;">*</span></label>
							<input class="form_input" type="text" id="aadhar" name="aadhar" value="<%=user.getAadhar() != null ? user.getAadhar() : ""%>" placeholder="Aadhar_Number" required  title="Aadhar must be exactly 12 digits">
						</div>

						<div class="form-group">
							<label class="generic_label" for="pan">PAN Number:<span style="color: red; font-weight: bold; margin-left: 5px;">*</span></label>
							<input class="form_input" type="text" id="pan" name="pan" value="<%=user.getPan() != null ? user.getPan() : ""%>" placeholder="PAN_Number" pattern="[A-Z]{5}[0-9]{4}[A-Z]{1}" required  title="PAN format: ABCDE1234F">
						</div>
					</div>
					
					<div class="form-row">
						<div class="form-group">
							<label class="generic_label" for="email">E-Mail Id:<span style="color: red; font-weight: bold; margin-left: 5px;">*</span></label>
							<input class="form_input" type="email" id="email" name="email" title="E-Mail Format: abcd@xyz.com" value="<%=user.getEmail() != null ? user.getEmail() : ""%>" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}" placeholder="Email_Address" required>
						</div>
									
						<div class="form-group">
							<label class="generic_label" for="phone">Phone Number:<span style="color: red; font-weight: bold; margin-left: 5px;">*</span></label>
							<input class="form_input" type="tel" id="phone" name="phone" value="<%=user.getPhone() != null ? user.getPhone() : ""%>" placeholder="Phone_Number" required pattern="[0-9]{10}" title="Phone number must be 10 digits">
						</div>
					</div>

					<div class="form-row">
						<div class="form-group">
							<label class="generic_label" for="address1">Address:<span style="color: red; font-weight: bold; margin-left: 5px;">*</span></label>
							<input class="form_input address_input" type="text" id="address1" name="address1" value="<%=user.getAddress1() != null ? user.getAddress1() : ""%>" placeholder="Street Address" required>
						</div>

						<div class="form-group">
							<input class="form_input address_input" type="text" id="address2" name="address2" value="<%=user.getAddress2() != null ? user.getAddress2() : ""%>" placeholder="Apartment, etc.">
						</div>

					</div>

					<div class="form-row">
						<div class="form-group">
							<label class="generic_label" for="district">District:<span style="color: red; font-weight: bold; margin-left: 5px;">*</span></label>
							<input class="form_input" type="text" id="district" name="district" value="<%=user.getDistrict() != null ? user.getDistrict() : ""%>" placeholder="District" required>
						</div>

						<div class="form-group">
							<label class="generic_label" for="state">State:<span style="color: red; font-weight: bold; margin-left: 5px;">*</span></label>
							<input class="form_input" type="text" id="state" name="state"	value="<%=user.getState() != null ? user.getState() : ""%>" placeholder="State" required>
						</div>
						
					</div>

					<div class="form-row">
						<div class="form-group">
							<label class="generic_label" for="country">Country:<span style="color: red; font-weight: bold; margin-left: 5px;">*</span></label>
							<input class="form_input" type="text" id="country" name="country" value="<%=user.getCountry() != null ? user.getCountry() : ""%>" placeholder="Country" required>
						</div>
							
						<div class="form-group">
							<label class="generic_label" for="pincode">Pin-Code:<span style="color: red; font-weight: bold; margin-left: 5px;">*</span></label>
							<input class="form_input" type="text" id="pincode" name="pincode" value="<%=user.getPincode() != null ? user.getPincode() : ""%>" placeholder="Pincode" pattern="[0-9]{6}" title="Pin-Code should be exactly 6 digits " required>
						</div>
						
					</div>

					<div class="form-row">
						<div class="form-group">
							<label class="generic_label" for="setpasswod">Password:<span style="color: red; font-weight: bold; margin-left: 5px;">*</span></label>
							<input class="form_input" type="password" id="setpassword" name="setpassword" value="<%=user.getPassword() != null ? user.getPassword() : ""%>" placeholder="New_Password" required minlength="8" pattern="(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&#])[A-Za-z\d@!#$%*&?]{8,20}" itle="Password must be between 8 and 20 characters long and include a letter, a number, and a special character.">
						</div>

						<div class="form-group">
							<label class="generic_label" for="confirmpassword">Confirm Password:<span style="color: red; font-weight: bold; margin-left: 5px;">*</span> </label>
							<input class="form_input" type="password" id="confirmpassword" name="confirmpassword" value="<%=user.getPassword() != null ? user.getPassword() : ""%>" placeholder="Confirm_Password" required oninput="this.setCustomValidity(this.value !== document.getElementById('setpassword').value ? 'Passwords do not match!' : '')">
						</div>
					</div>

					<button class="sub_btn" type="submit" name="action" value="<%=user.getUserId() > 0 ? "update" : "submit"%>">Submit</button>
					<button class="reset_btn" type="reset" id="reset">Reset</button>
				</form>
			</div>

		</div>

	</main>
</body>
</html>