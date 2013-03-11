$(document).ready(function() {
	
	$(".shoppingCart").click(function(){
		$(".jspContainer").load("jsp/cart.jsp",{username:usr},function() {  } );
	});
			
	$(".catalogBtn").click(function(){
		$(".jspContainer").load("jsp/catalog.jsp",{username:usr, message: message},function() {  } );
	});
	
	$(".purchasedBtn").click(function(){
		$(".jspContainer").load("jsp/purchased.jsp",{username:usr, message: message},function() {  } );
	});
	
	$(".playlistBtn").click(function(){
		$(".jspContainer").load("jsp/playlist.jsp",{username:usr},function() {  } );
	});
	
	$(".playerBtn").click(function(){
		$(".jspContainer").load("jsp/player.jsp",{username:usr},function() {  } );
	});
	
});