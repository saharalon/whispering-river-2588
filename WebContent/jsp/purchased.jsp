<%@ page  language="java" errorPage="" %>
<%@ page import="il.ac.shenkar.JavaTunes.*,java.util.*" %>
<%@ taglib uri="/WEB-INF/tlds/mp3ISO.tld" prefix="mp3ISO" %>
<jsp:useBean id="beanUser" class="il.ac.shenkar.JavaTunes.User" scope="session" />
<jsp:setProperty name="beanUser" property="username" />
<h1>here should be all of the songs the user purchased</h1>
<%
	List userList =  (ArrayList)session.getAttribute("purchased");
	Iterator itr = userList.iterator();
	while(itr.hasNext())
	{
		Track currentTrack = (Track)itr.next();
		String id = currentTrack.getId();
		String artist = currentTrack.getArtist();
		String title = currentTrack.getTitle();
		String downLink = currentTrack.getDownLink();
%>
<%= String.valueOf(id) %> - <mp3ISO:mp3ISO artist="<%= String.valueOf(artist) %>" title="<%= String.valueOf(title) %>"/>
<a href="/JavaTunes/home?username=<jsp:getProperty name="beanUser" property="username" />&refer=purchased&action=addToPlaylist&id=<%= String.valueOf(id) %>">
	<img class="addToPlaylist" title="add to playlist" alt="add to playlist" src="/JavaTunes/imgs/addToPlaylist.png" />
</a>
<a href="<%= String.valueOf(downLink) %>" target="_blank">
	<img class="download" title="download" alt="download" src="/JavaTunes/imgs/download1.png">
</a>
<a href="/JavaTunes/home?username=<jsp:getProperty name="beanUser" property="username" />&refer=purchased&action=eraseTune&id=<%= String.valueOf(id) %>">
	<img class="erasePurTune" title="erase tune" alt="erase tune" src="/JavaTunes/imgs/DeleteRed2.png">
</a>
<br />
<%	}	%>

<script>
	if ((message != "") && (message != null)) { alert(message); message = ""; }
</script>