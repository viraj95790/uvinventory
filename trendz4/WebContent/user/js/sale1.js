var	totalsubjectid=0;
var currentQuantity = 0;
var temPay = 0;
$(document).ready(function(){
	
	document.getElementById('billbtn').disabled="disabled";
	
		var validator = $("#product_form").validate({
		onsubmit: true,
		rules : {
			
			categoryID : {
				selected : true
				},	
			productName : {
				selected : true
				},	
			
				
		 /*  description : {
		   		required : true
				} ,*/
			
			quantity : {
		   		required : true
				} ,
				
			price : {
		   		required : true
				} ,
				
				customerName: {
					required : true
				},
				
				
				
			mobileNo : {
				required : true,
				mobilenumber : true
			}
		 
		
		
		
		},
		messages : {
		
				categoryID : {
				selected : error.categoryID.selected
				},
				
				productName : {
				selected : error.productName.selected
				},
				
				
				
		
		       /* description : {
						required : error.description.required
					  },*/
				
				quantity : {
						required : error.quantity.required
					  },
					  
				price : {
						required : error.price.required
					  },
					  
				customerName : {
						required : error.name.required
					 	
					 	},	
					 	
				mobileNo : {
					required : error.mobileNo.required,
					mobilenumber : error.mobileNo.mobilenumber
				}
						
		
		
		
		
		},
		
	});
	
	
	$("#saletable").click(function() {
		
		totalsubjectid=0;
		
		
		
		  var chk=$("#saletable :checked").size();
		  
		   if(chk > 1){
               
	  		  $("#saletable :checked").each(function() {
				// alert("value = " + $(this).val());
				totalsubjectid= totalsubjectid + "," +	 $(this).val();
				});
				
			}else{
				$("#saletable :checked").each(function() {
					totalsubjectid = $(this).val();
				});
			}
		 
		 
	});
	
	
});	


var categoryD = 0;
var tempsubcategoryid = 0;

function setSubCategory(id){
	
	categoryD = id;
	
	var url = "subcategoryAjaxSale?id="+id+"";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setSubCategoryRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}


function setSubCategoryRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		
			
    		 document.getElementById("productajax").innerHTML = req.responseText;
         
         }
	}
}




function setStock(subcategoryID){
	
	tempsubcategoryid = subcategoryID;
	
	var url = "stockSale?categoryD="+categoryD+"&subcategoryID="+subcategoryID+"";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setStockRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	

}

function setStockRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			 document.getElementById("stockajax").innerHTML = req.responseText;
    		 document.getElementById("quantity").value = "1";
    		 
    		 document.getElementById('addbtn').disabled="";
    		 document.getElementById('rmovbtn').disabled="";
    		 
    		 currentQuantity = document.getElementById('stock').value;
    		
    		 
    		 if(document.getElementById('stock').value==0){
    		 	 document.getElementById('addbtn').disabled="disabled";
    		 	 document.getElementById('rmovbtn').disabled="disabled";
    		 	 document.getElementById("quantity").value = "0";
    		 	 
    		 	 alert("There is no stock for selected product!!");
    		 }
    		 if(document.getElementById('stock').value<=10){
    		 	alert("Please increase your product stock!!");
    		 }
    		 
         
         }
	}
}


function save(){
	document.getElementById('billbtn').disabled="";
	//alert(tempsubcategoryid);
	if(document.getElementById("categoryID").selectedIndex == 0){
		alert("Please Select Category !!");
	}else if(document.getElementById("subCategoryID").selectedIndex == 0){
		alert("Please Select Product Name !!");
	}else if(parseInt(document.getElementById('stock').value) <  parseInt(document.getElementById('quantity').value)){
		alert("Product quantity should be less than stock!!");
	}else{
		updateStock();
		saleAjax();
		
	}

}

function saleAjax(){
	
	var quantity = document.getElementById('quantity').value;
	
	var url = "setSale?categoryD="+categoryD+"&subcategoryID="+tempsubcategoryid+"&quantity="+quantity+"";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = saleAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function saleAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('saletable').innerHTML = req.responseText;
			temPay = parseFloat(document.getElementById('hiddenpay').value);
			
			if(document.getElementById('itemsize').value > 0){
				document.getElementById('billbtn').disabled="";
			}else{
				document.getElementById('billbtn').disabled="disabled";
			}
			
    	}
	}
}


function deletesale(){

var chk=$("#saletable :checked").size();
	if(chk == 0){
		alert("Please select product to remove!!")
	}else{
		deleteAjax();
		
	}
	

}

function deleteAjax(){
	var url = "deleteSale?selectedItem="+totalsubjectid+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = deleteAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function deleteAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('saletable').innerHTML = req.responseText;
			temPay = parseFloat(document.getElementById('hiddenpay').value);
			
			if(document.getElementById('itemsize').value > 0){
				document.getElementById('billbtn').disabled="";
			}else{
				document.getElementById('billbtn').disabled="disabled";
			}
    	}
	}
}


function updateStock(){

	stock = parseInt(currentQuantity);
	quantity = parseInt(document.getElementById('quantity').value);
	result = stock - quantity;
	document.getElementById('stock').value = result;
}


function addVat(vat){
	per = (vat*temPay)/100;
	pay = temPay+per;
	document.getElementById('pay').innerHTML = parseFloat(pay);
	
}


function addable(){
	document.getElementById('billbtn').disabled="disabled";
}




-------------------------------------------------------

function setStock1(subcategoryID){
	size = "";
	click = 0;
	tempsubcategoryid = subcategoryID;
	
	var url = "bstockSale?categoryD="+categoryD+"&subcategoryID="+subcategoryID+"";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setStockRequest1;
    req.open("GET", url, true); 
              
    req.send(null);
	

}
var barcodeViewSize = "";
var bquantity = 0;
function setStockRequest1(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			document.getElementById('purchasetable').innerHTML = req.responseText;
			document.getElementById('adsizebtn').disabled = "disabled";
			//document.getElementById('rmovesizebtn').disabled = "disabled";
			if(document.getElementById('barcodeData').value!=""){
			
				var temp = barcodeData.split(" ");
				barcodeViewSize = barcodeViewSize + temp[1] + ",";
				//document.getElementById('viewsize').value = barcodeViewSize.substring(0,barcodeViewSize.length-1);
				document.getElementById('price').value = temp[2];
				//bquantity = parseInt(bquantity)+1;
				//document.getElementById('quantity').value = bquantity;				
				
				document.getElementById('addbtn').disabled = "";
				document.getElementById('rmovbtn').disabled = "";
				
				var temp = barcodeData.split(" ");
	
	
	var options= document.getElementById('adsize').options;
		for(i=0;i<options.length;i++){
			
				if(options[i].text == temp[1]){
					//alert(options[i].text)
					document.getElementById('adsize').value = options[i].value;
					
					//addSizeAjax1(options[i].value , options[i].text , temp[0], temp[2]);
					
					//save();
					break;
				}
			}
			
				
				
				
				//save();
				
				//document.getElementById('viewsize').value = "";
				barcodeViewSize = "";
				document.getElementById('barcodeData').value = "";
			}
			
			
    	 }
	}
}













