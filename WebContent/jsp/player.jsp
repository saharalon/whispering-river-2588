<%@ page  language="java" errorPage="" %>
<%@ page import="il.ac.shenkar.JavaTunes.*,java.util.*" %>
<jsp:useBean id="beanUser" class="il.ac.shenkar.JavaTunes.User" scope="session" />
<jsp:setProperty name="beanUser" property="username" />
<h1>here should be the mp3 player</h1>
<br />
<img class="back" alt="back" src="/JavaTunes/imgs/previous.png" onclick="back();" />
<img class="random" title="turn shuffle off" alt="random" src="/JavaTunes/imgs/random.png" onclick="random();" />
<img class="unRandom" title="turn shuffle on" alt="unRandom" src="/JavaTunes/imgs/unRandom.png" onclick="unRandom();" />
<img class="next" alt="next" src="/JavaTunes/imgs/next.png" onclick="next();" />
<br />
<select class="list"></select>
<div class="previewWrapper2">
	<div class="preview2">
		<iframe class="iFrame2" align="middle" src=""></iframe>
	</div>
</div>

<script>
	$(".random").hide();
	var tracksArr = [];
	var playOrder = [];
	var counter = 0;
	
	<%
		List userList =  (ArrayList)session.getAttribute("playlist");
		Iterator itr = userList.iterator();
		while(itr.hasNext())
		{
			Track currentTrack = (Track)itr.next();
			String id = currentTrack.getId();
			String artist = currentTrack.getArtist();
			String title = currentTrack.getTitle();
			String preLink = currentTrack.getPreLink();
	%>
	var obj = {};
	obj.id = "<%= String.valueOf(id) %>";
	obj.artist = "<%= String.valueOf(artist) %>";
	obj.title = "<%= String.valueOf(title) %>";
	obj.preLink = "<%= String.valueOf(preLink) %>";
	tracksArr.push(obj);
	$(".list").append("<option class='so" + counter + "'>" + obj.artist + " - " + obj.title + "</option>");
	playOrder.push(counter);
	counter++;
	<%	}	%>
	
	$(".list").attr("size", tracksArr.length);
	console.log(tracksArr);
	var currPlayedTrack = 0;
	console.log("currPlayedTrack = " + currPlayedTrack);
	$(".iFrame2").attr("src",tracksArr[currPlayedTrack].preLink);
</script>