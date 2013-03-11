function randomize(arr)
{
	var z = arr.length, v, temp;
    if ( z == 0 ) return;
    while ( --z ) 
    {
        v = Math.floor( Math.random() * ( z + 1 ) );
        temp = arr[z];
        arr[z] = arr[v];
        arr[v] = temp;
    }
	return arr;
}

function updatePlayerJsp()
{
	$(".so" + playOrder[currPlayedTrack] + "").attr("selected","selected");
	$(".iFrame2").attr("src",tracksArr[playOrder[currPlayedTrack]].preLink + "?autoplay=1");
}
function back() 
{ 
	if (currPlayedTrack > 0) 
	{ 
		currPlayedTrack--;
		updatePlayerJsp();
	}
}

function next() 
{ 
	if (currPlayedTrack < (tracksArr.length - 1)) 
	{ 
		currPlayedTrack++;
		updatePlayerJsp();
	}
}
function random()
{
	playOrder = randomize(playOrder);
	currPlayedTrack = playOrder[currPlayedTrack];
	$(".random").hide();
	$(".unRandom").show();
}

function unRandom()
{
	var j=0;
	currPlayedTrack = playOrder[currPlayedTrack];
	for (j=0; j < tracksArr.length; j++) { playOrder[j] = j; }
	$(".unRandom").hide();
	$(".random").show();
}
