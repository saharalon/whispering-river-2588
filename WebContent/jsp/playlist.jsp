<%@ page  language="java" errorPage="" %>
<%@ page import="il.ac.shenkar.JavaTunes.*,java.util.*" %>
<%@ taglib uri="/WEB-INF/tlds/mp3ISO.tld" prefix="mp3ISO" %>
<jsp:useBean id="beanUser" class="il.ac.shenkar.JavaTunes.User" scope="session" />
<jsp:setProperty name="beanUser" property="username" />
<h1>here should be the playlist</h1>
<br />
<%
	List userList =  (ArrayList)session.getAttribute("playlist");
	Iterator itr = userList.iterator();
	while(itr.hasNext())
	{
		Track currentTrack = (Track)itr.next();
		String id = currentTrack.getId();
		String artist = currentTrack.getArtist();
		String title = currentTrack.getTitle();
%>
<%= String.valueOf(id) %> - <mp3ISO:mp3ISO artist="<%= String.valueOf(artist) %>" title="<%= String.valueOf(title) %>"/>
<a href="/JavaTunes/home?username=<jsp:getProperty name="beanUser" property="username" />&refer=playlist&id=<%= String.valueOf(id) %>">
	<img class="removePlTune" title="remove from playlist" alt="remove from playlist" src="/JavaTunes/imgs/removeFromPlaylist.png">
</a>
<br />
<%	}	%>