<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
 	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Account Registration</title>
	<link rel="stylesheet" href="update_styles.css">
</head>
<body>
	<div class="form_div">
		<form class="form_container" action="FormServlet" method="post">
		
			<h2 id="signup_form">Account Registration Details</h2>
			
			<div class="name_group">
				<label class="name-label" for="fname">First Name:</label>
				<input class="name-input" type="text" id="fname" name="fname" placeholder="First_Name" autofocus required pattern="[A-Za-z]+" title="Only alphabets allowed"> 
				<label class="name-label" for="lname">Last Name:</label>
				<input class="name-input" type="text" id="lname" name="lname" placeholder="Last_Name" pattern="[A-Za-z]+" title="Only alphabets allowed"> 
			</div>
			
			<label class="generic_label" for="dateofbirth">Date of Birth:</label>
			<input class="form_input" type="date" id="dateofbirth" name="dateofbirth" placeholder="dd/mm/yyyy" required max="2006-12-31" title="You must be atleat 18 years old"> 
			
			<label class="generic_label" for="gender">Gender:</label>
			<div class="gender_group">
				<input type="radio" id="male" name="gender" value="Male">
				<label for="male">Male</label>
				<input type="radio" id="female" name="gender" value="Female">
				<label for="female">Female</label>
				<input type="radio" id="others" name="gender" value="Others">
				<label for="others">Others</label> 
			</div>
			
			<label class="generic_label" for="aadhar">Aadhar Number:</label>
			<input class="form_input" type="text" id="aadhar" name="aadhar" placeholder="Aadhar_Number" required pattern="\d{12}" title="Aadhar must be exactly 12 digits">
			 
			<label class="generic_label" for="pan">PAN Number:</label>
			<input class="form_input" type="text" id="pan" name="pan" placeholder="PAN_Number" required pattern="[A-Z]{5}[0-9]{4}[A-Z]{1}" title="PAN format: ABCDE1234F">
			
			<label class="generic_label" for="email">E-Mail Id:</label> 
			<input class="form_input" type="email" id="email" name="email" placeholder="Email_Address" required> 
			
			<label class="generic_label" for="phone">Phone Number:</label> 
			<input class="form_input" type="tel" id="phone" name="phone" placeholder="Phone_Number" required pattern="[0-9]{10}" title="Phone number must be 10 digits"> 
			
			<label class="generic_label" for="address1">Address:</label>
            <input class="form_input" type="text" id="address1" name="address1" placeholder="Street Address" required>
            <input class="form_input" type="text" id="address2" name="address2" placeholder="Apartment, etc.">
            
	        <div class="address">
	           	<label class="ad-label" for="district">District:</label>
	           	<input class = "ad-input" type="text" id="district" name="district" placeholder="District" required>
	           	<label class="ad-label" for="state">State:</label>
	           	<input class = "ad-input" type="text" id="state" name="state" placeholder="State" required>    	
	        </div>
	            
	        <div class="address">
	           	<label class="ad-label" for="country">Country:</label>
	           	<input class = "ad-input" type="text" id="country" name="country" placeholder="Country" required>
	           	<label class="ad-label" for="pincode">Pin-Code:</label>
	         	<input class = "ad-input" type="text" id="pincode" name="pincode" placeholder="Pincode" pattern="[0-9]{6}" required>	
	        </div>

			<label class="generic_label" for="setpasswod">Password:</label> 
			<input class="form_input" type="password" id="setpassword" name="setpassword" placeholder="New_Password" required minlength="8" pattern="(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&#])[A-Za-z\d@!#$%*&?]{8,20}" title="Password must be at least 8 characters long and include a letter, a number, and a special character."> 
			
			<label class="generic_label" for="confirmpassword">Confirm Password:</label> 
			<input class="form_input" type="password" id="confirmpassword" name="confirmpassword" placeholder="Confirm_Password" required oninput="this.setCustomValidity(this.value !== document.getElementById('setpassword').value ? 'Passwords do not match!' : '')"> 
			 
			<button class="sub_btn" type="submit" name="action" value="submit">Submit</button>
			<button class="view_btn" type="submit" formaction="forms_view.jsp">View</button> 
			<button class="reset_btn" type="reset" id="reset">Reset</button> 
		</form>
		
		<script>
			const urlParams = new URLSearchParams(window.location.search);
			if(urlParams.get('success')==='true'){
				alert("Registration Successful");'
			}
		</script>
	</div>
</body>
</html>