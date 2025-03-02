<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="header_styles.css">
</head>
<body>
	<header>
		<div class="banner_holder">
			<div class="banner_left">
					<div class="logo_img_holder">
						<a href="User_Form.jsp">
							<img class="logo-image" src="images/zoho_logo.jpg" alt="Logo" title="Home">
							</a>
					</div>
					<h1 id="title">Z-Register</h1>
			</div>
			<div class="banner_right">
				<img id="img_id" alt=<%=request.getAttribute("altenative") %> src="images/<%=request.getAttribute("image") %>" onclick=<%= request.getAttribute("action") %> title=<%=request.getAttribute("title") %>>
				<h4 id="icon_label" onclick=<%= request.getAttribute("action") %> title=<%=request.getAttribute("title") %> 	><%=request.getAttribute("icon_name") %></h4>
				<img id="home_id" alt="Home" src ="images/home.svg" onclick="window.location.href='User_Form.jsp'" title="Home">
				<h4 id="home_label" onclick="window.location.href='User_Form.jsp'" title="Home">Home</h4>
			</div>
		</div>
	</header>

</body>
</html>