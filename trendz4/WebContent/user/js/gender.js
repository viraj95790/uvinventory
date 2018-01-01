//set barcode dropdown value

function setBarocdeGenderAjax(gender){
	
	var url = "genderCategory?gender="+gender+" ";  
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setBarocdeGenderAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function setBarocdeGenderAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('gendercategory').innerHTML = req.responseText;
			document.getElementById('categoryID').value = gbarcodecategory;
			setBarcodeCategoryID(gbarcodecategory);
			
		}
	}
}


/*barcode dropdown value end*/


function setGenderAjax(gender){
	
	var url = "genderCategory?gender="+gender+" ";  
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setGenderAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function setGenderAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('gendercategory').innerHTML = req.responseText;
			disableElement();
		}
	}
}


function disableElement(){
	var str = "<select name=categoryID><option value='0'>Select Product</option> </select>"
	
	document.getElementById('productajax').innerHTML = str;

}

//ajax for sale return 

function setGenderAjaxSaleReturn(gender){
	
	var url = "gendereturnCategory?gender="+gender+" ";  
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setGenderAjaxSaleReturnRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function setGenderAjaxSaleReturnRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('gendercategory').innerHTML = req.responseText;
			disableElement();
		}
	}
}


function setCategoryIDSalaReturn(id){
	size = "";
	click = 0;
	colorData = "";
	categoryD = id;
	
	var url = "subcategoryAjaxreturnSale?id="+id+"";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setCategoryIDSalaReturnRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}


function setCategoryIDSalaReturnRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		
			
    		 document.getElementById("productajax").innerHTML = req.responseText;
    		 document.getElementById('stock').value = "";
    		 document.getElementById('imgview').innerHTML = "";
    		 document.getElementById('modelnumber').value = "";
    		 document.getElementById('articlenumber').value = "";
    		 document.getElementById('color').value = "";
    		 document.getElementById('price').value = "";
    		 
    		 
    		
    	}
	}
}

function setStockSaleReturn(subcategoryID){
	document.getElementById('barcodeData').value = "";
	document.getElementById('barcodeData').disabled = "disabled";
	size = "";
	click = 0;
 	colorData = "";
	tempsubcategoryid = subcategoryID;
	
	var url = "sreturnPurchase?categoryD="+categoryD+"&subcategoryID="+subcategoryID+"";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setStockSaleReturnRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	

}

function setStockSaleReturnRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			document.getElementById('purchasetable').innerHTML = req.responseText;
			
			
		}
	}
}

