var gprodid = 0;
var gsubcatid = 0;
function showStock(id){
	document.getElementById('rbutton'+id).style.display = 'none';
	document.getElementById('remainstockid'+id).style.display = '';
}

function removeStock(id){
	document.getElementById('rbutton'+id).style.display = '';
	document.getElementById('remainstockid'+id).style.display = 'none';
}


var detailid;
function updateStock(id,selectedID){
	detailid = id;
	var stockValue = document.getElementById('stock'+id).value;
	
	pstock = parseInt(document.getElementById('quantity'+id).value) - parseInt(document.getElementById('stock'+id).value);
	
	document.getElementById('production'+id).value = pstock;
	
	
	var url = "updatestockProduct?stockValue="+stockValue+"&selectedID="+selectedID+"&detailid="+detailid+" ";
	
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = updateStockRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function updateStockRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
			removeStock(detailid);
    		 document.getElementById("rbutton"+detailid).innerHTML = req.responseText;
    		 
         
         }
	}
}


function updateQuantity(id,selectedID){
	
	detailid = id;
	var stockValue = document.getElementById('stock'+id).value;
	
	var quantity = document.getElementById('quantity'+id).value;
	
	pstock = parseInt(document.getElementById('quantity'+id).value) - parseInt(document.getElementById('stock'+id).value);
	
	document.getElementById('production'+id).value = pstock;

	var url = "updatequantityProduct?stockValue="+stockValue+"&selectedID="+selectedID+"&detailid="+detailid+"&quantity="+quantity+" ";
	
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = updateStockRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function updateQuantityRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			
		}
	}
}





function delievered(categoryid,subcategoryid,selectedstatus,nq,sq,bq){
	
	document.getElementById('status'+selectedstatus).innerHTML = 'Delievered';
	
	document.getElementById('n'+selectedstatus).innerHTML = '0';
	document.getElementById('s'+selectedstatus).innerHTML = '0';
	document.getElementById('b'+selectedstatus).innerHTML = '0';
	document.getElementById('total'+selectedstatus).innerHTML = '0';
	
	var url = "delieveredProduct?categoryid="+categoryid+"&subcategoryid="+subcategoryid+"&nq="+nq+"&sq="+sq+"&bq="+bq+" ";
	
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = delieveredRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function delieveredRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
    		 
         
         }
	}
}


function checkadminqty(id,val){
	
	var adminqty = document.getElementById('pqtyy'+id).innerHTML;
	if(parseInt(adminqty)<parseInt(val)){
		alert("Entered stock can not be more thamadmin stock!!");
		document.getElementById('ptxt'+id).value = '';
	}
}


function addShopStock(id,subcatid){
	gsubcatid = subcatid;
	var qty = document.getElementById('shopstock'+id).innerHTML;
	
	var str = '<input type="text" onchange="checkadminqty('+id+',this.value)" value="'+qty+'"   name="ptxt'+id+'" id="ptxt'+id+'" maxlength="3" size="3" style="text-align:center"/>'
	str = str + '<input type="button" value="Update" onclick="setShopStockAjax('+id+')"/>';
	document.getElementById('shopstock'+id).innerHTML = str;
	
	$("#ptxt"+id).focus();
}


function setShopStockAjax(id){
	gprodid = id;
	var qty = document.getElementById('ptxt'+id).value;
	var shopid =  document.getElementById('pdd'+id).value;
	
	
	var adminqty = document.getElementById('pqtyy'+id).value;
	
	
	if(shopid==0){
			alert("Please select shop!!")
	}else{
		
	var url = "shopstockProduct?shopid="+shopid+"&qty="+qty+"&prodid="+id+"&subcateoryid="+gsubcatid+" ";
	
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setShopStockAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
    
	}
}
function setShopStockAjaxRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('shopstock'+gprodid).innerHTML = req.responseText;
			
			confirmedDelete();
         }
	}
}


function confirmedDelete(){

	var r=confirm("Are you sure you want to delete this entry");
	if (r==true)
	  {
		openBarcodePopup();
	  return true;
	  }
	else
	  {
	  return false;
	  }

	}    	
	  


function showShopQty(shopid,id){
	gprodid = id;
	var url = "showshopqtyProduct?shopid="+shopid+"&prodid="+id+" ";
	
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showShopQtyRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function showShopQtyRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var qty = req.responseText;
			document.getElementById('shopstock'+gprodid).innerHTML = qty;
         
         }
	}
}