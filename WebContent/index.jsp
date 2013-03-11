<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>JavaTunes</title>

<%
	String username = "";
	Cookie[] vec = request.getCookies();
	for(int i=0; vec!=null && i<vec.length; i++)
	{
		if(vec[i].getName().equals("usercookie"))
		{
			username = vec[i].getValue();
		}	
	}
%>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
</head>
<body>

	<h1>Welcome to JavaTunes</h1>
	<br /><br />
	
	<form method="POST" action="http://localhost:8080/JavaTunes/home">
		<%	
			String error = request.getParameter("error");
			if (error == null) { error = ""; }
		%>
		<%= error %>
		<br />
		<input id="username1" type="text" name="username" value=<%=username%>>
		<input type="password" name="password_field">
		<input type="hidden" name="refer" value="login">
		<input type="submit" name="submitBtn" value="Login">
	</form>
	<h4 class="notUser"><a href="#" onclick="clearField();">not (<font style="color: black;"><%=username%></font>)</a> ?</h4>

	<script>function clearField() { $("#username1").attr("value",""); $(".notUser").remove(); }</script>
</body>
</html>