<%@ page  language="java" errorPage="" %>
<%@ page import="il.ac.shenkar.JavaTunes.*,java.util.*" %>
<%@ taglib uri="/WEB-INF/tlds/mp3ISO.tld" prefix="mp3ISO" %>
<jsp:useBean id="beanUser" class="il.ac.shenkar.JavaTunes.User" scope="session" />
<jsp:setProperty name="beanUser" property="username" />
<h1>here should be the cart</h1><br />
<br />

<%
	List userList =  (ArrayList)session.getAttribute("cart");
	Iterator itr = userList.iterator();
	float totalPrice = 0;
	String ids = "";
	while(itr.hasNext())
	{
		Track currentTrack = (Track)itr.next();
		String id = currentTrack.getId();
		String artist = currentTrack.getArtist();
		String title = currentTrack.getTitle();
		float price = currentTrack.getPrice();
		totalPrice += price;
		ids += ":" + id;
%>
<%= String.valueOf(id) %> - <mp3ISO:mp3ISO artist="<%= String.valueOf(artist) %>" title="<%= String.valueOf(title) %>"/> - <%= price %>$
&nbsp;&nbsp;<a class="removeCartTune" href="/JavaTunes/home?username=<jsp:getProperty name="beanUser" property="username" />&refer=cart&action=removeTune&id=<%= String.valueOf(id) %>">remove</a>
<br />
<%	}	%>
Total Price: <%= totalPrice %>
<br />
<a class="Proceed" href="/JavaTunes/home?username=<jsp:getProperty name="beanUser" property="username" />&refer=cart&action=proceed&id=<%= String.valueOf(ids) %>">Proceed</a>