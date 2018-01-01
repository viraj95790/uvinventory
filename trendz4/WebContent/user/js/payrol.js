 
 var gindex = 0;
var gd = 0;
var cindex = 0;
function addRow(btnindex){

 var gindex = btnindex;

  document.getElementById('btn'+btnindex).innerHTML = '';

  var table = document.getElementById('payrollexpense');
  
   var rowCount = table.rows.length;
   var row = table.insertRow(rowCount);
    var counts = rowCount - 1;
  	gd = counts;
  	
  	pcell1 = row.insertCell(0);
  	pcell1.innerHTML = '<span id="v'+counts+'"><select onchange="setInvoiceAjax(this.value,'+counts+')" name="paylist['+counts+'].vendor" id="paylist['+counts+'].vendor"><option value="0">Select Vendor</option></select></span>';
  	
  	pcell2 = row.insertCell(1);
  	pcell2.innerHTML = '<span id="inv'+counts+'"><select onchange="setInvoiceNalance(this.value,'+counts+')"  name="paylist['+counts+'].invoice" id="paylist['+counts+'].invoice"><option value="0">Select Invoice</option></select></span>';
  	  
  	
  	pcell3 = row.insertCell(2);
  	pcell3.innerHTML = "<span style='margin-left: 73px;'> <input style='text-align:center' type='text'  maxlength='25' size='12'    name='paylist[" + counts + "].discription' id='discription"+counts+"'></span>";
  
  	pcell4 = row.insertCell(3);
  	pcell4.innerHTML = "<span style='margin-left: 80px;'> <input class='case' onchange='sumofexpencex()' style='text-align:center' type='text'  maxlength='8' size='10'    name = 'paylist[" +counts+ "].debitx' id='debitx"+counts+"'></span>";
  
  	pcell5 = row.insertCell(4);
  	pcell5.innerHTML = "<span id='btn"+counts+"'><input type='button' value='Add' onclick='addRow("+counts+")'></span>";
  	
  	
  	setVendorAjax();
   
}
function setVendorAjax(id,gdx){
	
	var url = "vnderInvoiceReport?index="+gd+" ";  
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setVendorAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function setVendorAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('v'+gd).innerHTML = req.responseText;
			
		 	 //cell2.innerHTML = req.responseText;
			
		}
	}
}

function setInvoiceAjax(vnderid,gdx){
	gd=gdx;
	var url = "invInvoiceReport?index="+gd+"&vnderid="+vnderid+" ";  
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setInvoiceAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function setInvoiceAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('inv'+gd).innerHTML = req.responseText;
			
		 	 //cell2.innerHTML = req.responseText;
			
		}
	}
	
}


function setInvoiceNalance(invid){
	var url = "balInvoiceReport?index="+gd+"&invid="+invid+" ";  
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setInvoiceNalanceRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function setInvoiceNalanceRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('debitx'+gd).value = req.responseText;
			
		 	 //cell2.innerHTML = req.responseText;
			sumofexpencex();
		}
	}
}

 function savePayroll(){
 var bal =  document.getElementById('balance').value;
 var debit = document.getElementById('debit').value;
  if(document.getElementById('discription'+gindex).value=='' && document.getElementById('paylist['+gindex+'].invoice').value==0){
	  alert('Please enter discription!!')
    	
    }else if(document.getElementById('debitx'+gindex).value==''){
    	alert('Please Enter payment');
    }else if(parseFloat(debit)>parseFloat(bal)){
    	alert('Payment can not be greater than balance amount!!')
    }
    else{
    
    document.getElementById('payrol_expense').submit();
    
    }
 
 }
 
 
 function sumofexpencex(){

 var total = 0;
  $('.case').each(function() { //loop through each checkbox
		total = parseFloat(total) + parseFloat(this.value);	                    this.checked = true;  //select all checkboxes with class "checkbox1"               
   });
 	
 	document.getElementById('debit').value = total;
 }