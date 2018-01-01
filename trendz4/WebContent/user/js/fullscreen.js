var openpopupwidth = 1000;
var openpopupheight = 600;

function openClientLogPopup(cid){
	var oldwindow = window.open("ClientLog?id="+cid+"", "Client Container", "status = 1, height = "+openpopupheight+", width = "+openpopupwidth+", resizable = 0,scrollbars=yes" );
	oldwindow.focus();
}

function openDisplayPopup(URL) { 
	var oldwindow = window.open(URL, "Apmt Display", "status = 1, height = "+openpopupheight+", width = "+openpopupwidth+", resizable = 0,scrollbars=yes" ); 
	oldwindow.focus();
	}





function openBarcodePopup(){
	
	
	var t = 'formtarget';

	
	/* document.getElementById('getPatientRecord').submit(); */

	var left = (screen.width / 2) - (700 / 2);
	var top = (screen.height / 2) - (550 / 2);
	
	var oldwindow = window.open("", t,
			"status = 1,height = "+openpopupheight +",width = "+openpopupwidth +",resizable = 1,scrollbars=yes,left=" + 0
					+ ",top=" + 50);
	
	oldwindow.focus();

	document.getElementById('barcodefrm').submit();
	
	
}