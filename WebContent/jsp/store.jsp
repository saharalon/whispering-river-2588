<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="il.ac.shenkar.JavaTunes.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>JTunes</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
</head>

<body>	
	
	<div class="storeContainer">

		<jsp:useBean id="beanUser" class="il.ac.shenkar.JavaTunes.User" scope="session" />
		<jsp:setProperty name="beanUser" property="username" />
		
		<script>
			var usr = "${beanUser.username}";
			var message = "${message}";	
		</script>
		
		<%
			String refer = request.getParameter("refer");
			session.setAttribute("tracks", request.getAttribute("sTracks"));
		 	session.setAttribute("purchased", request.getAttribute("sPurchasedTracks"));
		 	session.setAttribute("playlist", request.getAttribute("sPlaylist"));
		 	session.setAttribute("cart", request.getAttribute("sCart"));
		 	User tmp = (User)request.getAttribute("beanUser");
		 	int numOfCartItems = 0;
		 	numOfCartItems = ((tmp.getCart().split(":").length) - 1);
		 %>
		
		<div class="headerBar">
			Hello&nbsp;<jsp:getProperty name="beanUser" property="username" />,&nbsp;
			<a class="shoppingCart" href="#" style="color: blue;"><img class="cartTxtImg" src="/JavaTunes/imgs/addToCart.png"></img>TunesCart</a>(<%=numOfCartItems %>)
		</div>
		
		<img class="logo" src="/JavaTunes/imgs/logo.png" />
		
		<div class="hiddenSidebar">
			<div class="sidebar">
				<div class="menu">
					<INPUT TYPE="BUTTON" class="catalogBtn" onclick="playSound('sounds/Click.mp3');">
					<INPUT TYPE="BUTTON" class="purchasedBtn" onclick="playSound('sounds/Click.mp3');">
					<INPUT TYPE="BUTTON" class="playlistBtn" onclick="playSound('sounds/Click.mp3');">
					<INPUT TYPE="BUTTON" class="playerBtn" onclick="playSound('sounds/Click.mp3');">
					<br /><br /><br /><br /><br />
					<font style="color: blue;"><a href="/JavaTunes/home?refer=logout">Log Out</a></font>
				</div>
			</div>
		</div>
		
		<span id="dummy"></span>
		
		<div class="jspContainer">
		<p>Welcome, <jsp:getProperty name="beanUser" property="username" /> !</p>
		</div>

	</div>
	
	<script>
		window.onbeforeunload = function() { $.get('/JavaTunes/home?refer=logout', function() {}); };
		var refer2 = "${refer}";
		console.log("refer2:" + refer2);
		if (refer2 != "") { $(".jspContainer").load("/JavaTunes/" + refer2); }
	</script>
	
	<script>
	function playSound(soundfile)
	{
		document.getElementById("dummy").innerHTML="<embed src=\""+soundfile+"\" hidden=\"true\" autostart=\"true\" loop=\"false\" />";
	}
	</script>
	
	<script src="js/menu.js"></script>
	<script src="js/player.js"></script>
</body>

</html>