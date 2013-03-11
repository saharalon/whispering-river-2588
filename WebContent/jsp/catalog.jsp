<%@ page  language="java" errorPage="" %>
<%@ page import="il.ac.shenkar.JavaTunes.*,java.util.*" %>
<%@ taglib uri="/WEB-INF/tlds/mp3ISO.tld" prefix="mp3ISO" %>
<jsp:useBean id="beanUser" class="il.ac.shenkar.JavaTunes.User" scope="session" />
<jsp:setProperty name="beanUser" property="username" />
<h1>here should be the songs list<br />and the options to purchase or preview them</h1>
<br />

<div class="previewWrapper1">
	<div class="preview1">
		<iframe class="iFrame1" width="420" height="345" align="middle" src=""></iframe>
	</div>
	<button class="closePre" onclick="closePre();">close</button>
</div>

<%
	List userList =  (ArrayList)session.getAttribute("tracks");
	Iterator itr = userList.iterator();
	while(itr.hasNext())
	{
		Track currentTrack = (Track)itr.next();
		String id = currentTrack.getId();
		String artist = currentTrack.getArtist();
		String title = currentTrack.getTitle();
		String preLink = currentTrack.getPreLink();
		float price = currentTrack.getPrice();
%>
<%= String.valueOf(id) %> - <mp3ISO:mp3ISO artist="<%= String.valueOf(artist) %>" title="<%= String.valueOf(title) %>"/> - <%= price %>$
<a href="/JavaTunes/home?username=<jsp:getProperty name="beanUser" property="username" />&refer=catalog&id=<%= String.valueOf(id) %>">
	<img class="addToCart" title="add to cart" alt="add to cart" src="/JavaTunes/imgs/addToCart.png"></img>
</a>
<a href="#">
	<img class="preview" title="preview" alt="preview" src="/JavaTunes/imgs/preview.png" onclick="playPreview('<%= String.valueOf(preLink) %>');"></img>
</a>
<br />
<%	}	%>

<script>
	if ((message != "") && (message != null)) { alert(message); message = ""; }
</script>
<script>
	function playPreview(input) { $(".iFrame1").attr("src", input + "?autoplay=1"); $(".previewWrapper1").show(); }
	function closePre() { $(".iFrame1").attr("src", ""); $(".previewWrapper1").hide(); }
</script>
