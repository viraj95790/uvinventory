var	totalsubjectid=0;
var currentQuantity = 0;
var temPay = 0;
var bcodecplorsize = '';

$(document).ready(function(){
	
	document.getElementById('billbtn').disabled="disabled";
	document.getElementById('adsizebtn').disabled = "disabled";
	document.getElementById('rmovesizebtn').disabled = "disabled";
	document.getElementById('addbtn').disabled = "disabled";
	document.getElementById('rmovbtn').disabled = "disabled";
	
		var validator = $("#product_form").validate({
		onsubmit: true,
		rules : {
			
			categoryID : {
				selected : true
				},	
			productName : {
				selected : true
				},	
			employeeid : {
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
				}
			/*customerName: {
					required : true
				},
			mobileNo : {
				required : true,
				mobilenumber : true
			}*/
		 
				
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
				employeeid : {
					selected : error.employeeid.selected
				},
				
				quantity : {
						required : error.quantity.required
					  },
					  
				price : {
						required : error.price.required
					  }
				/*customerName : {
						required : error.name.required
					 	
					 	},	
					 	
				mobileNo : {
					required : error.mobileNo.required,
					mobilenumber : error.mobileNo.mobilenumber
				}*/
			},
		
	});
	
	$("#saletable").click(function() {
		
		totalsubjectid=0;
		
		  var chk=$("#saletable :checked").size();
		 
		   if(chk > 1){
               
	  		  $("#saletable :checked").each(function() {
				 //alert("value = " + $(this).val());
				totalsubjectid= totalsubjectid + "," +	 $(this).val();
				});
				
			}else{
				$("#saletable :checked").each(function() {
					totalsubjectid = $(this).val();
				});
			}
		 
	});
	
		
		
		//document.getElementById('subCategoryID').value = 9;
	
	
});	


var categoryD = 0;
var tempsubcategoryid = 0;
var size = "";
var click = 0;


function setBarcodeCategoryID(id){
	size = "";
	click = 0;
	categoryD = id;
	productidseries = "";
	
	var url = "subcategoryAjaxSale?id="+id+"";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setBarcodeCategoryIDRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}


function setBarcodeCategoryIDRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		
			
			
    		 document.getElementById("productajax").innerHTML = req.responseText;
			
			 document.getElementById('subCategoryID').value = gbatcodeproduct;
			 
			
			 
			 
			 
			 setStock(gbatcodeproduct);
			
    		/* document.getElementById('stock').value = "";
    		 document.getElementById('imgview').innerHTML = "";
    		 document.getElementById('modelnumber').value = "";
    		 document.getElementById('articlenumber').value = "";
    		 document.getElementById('color').value = "";
    		 document.getElementById('price').value = "";
    		 document.getElementById('viewsize').value = "";*/
    		 
    		 
    		 
    		 
         
         }
	}
}



function setCategoryID(id){
	size = "";
	click = 0;
	categoryD = id;
	productidseries = "";
	
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
    		 document.getElementById('stock').value = "";
    		 document.getElementById('imgview').innerHTML = "";
    		 document.getElementById('modelnumber').value = "";
    		 document.getElementById('articlenumber').value = "";
    		 document.getElementById('color').value = "";
    		 document.getElementById('price').value = "";
    		 document.getElementById('viewsize').value = "";
         
         }
	}
}

function setStock(subcategoryID){
	
	size = "";
	click = 0;
	productidseries = "";
	tempsubcategoryid = subcategoryID;
	
	var fromdate = document.getElementById('fromDate').value;
	var todate =  document.getElementById('toDate').value;
	
	var url = "stockBarcode?categoryD="+categoryD+"&subcategoryID="+subcategoryID+"&fromdate="+fromdate+"&todate="+todate+" ";
   
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
		
			document.getElementById('purchasetable').innerHTML = req.responseText;
			document.getElementById('addbtn').disabled = "";
			document.getElementById('rmovbtn').disabled = "";
    	 }
	}
}




function save(){
addColor();
	document.getElementById('billbtn').disabled="";
	
	//alert(tempsubcategoryid);
	if(document.getElementById("categoryID").selectedIndex == 0){
		alert("Please Select Category !!");
	}else if(document.getElementById("subCategoryID").selectedIndex == 0){
		alert("Please Select Product Name !!");
	}
	else if(document.getElementById("color").value == ''){
		alert("Please Select color !!");
	}else{
		//updateStock();
		
		
		purchaseAjax();
		//document.getElementById('viewsize').value = "";
	}

}


var viewsizeid = "";
function purchaseAjax(){
	
	var quantity = document.getElementById('quantity').value;
	var price = document.getElementById('price').value;
	var viewsize = document.getElementById('viewsize').value;
	//var colorsize = document.getElementById('color').value;
	var gender = document.getElementById('gender').value;
	
	var fromdate = document.getElementById('fromDate').value;
	var todate =  document.getElementById('toDate').value;
	
	
	
	var url = "setBarcode?categoryD="+categoryD+"&subcategoryID="+tempsubcategoryid+"&quantity="+quantity+"&price="+price+"&viewsize="+viewsize+"&productidseries="+productidseries+"&viewsizeid="+viewsizeid+"&gender="+gender+"&bcodecplorsize="+bcodecplorsize+"&fromdate="+fromdate+"&todate="+todate+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = purchaseAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function purchaseAjaxRequest(){
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
		 	document.getElementById('stock').value = "";
    		 document.getElementById('imgview').innerHTML = "";
    		 document.getElementById('modelnumber').value = "";
    		 document.getElementById('articlenumber').value = "";
    		 document.getElementById('color').value = "";
    		 document.getElementById('price').value = "";
    		 document.getElementById('viewsize').value = "";
    		 document.getElementById('stockQuantity').value = "";
    		 document.getElementById('categoryID').selectedIndex = 0;
    		 document.getElementById('productajax').innerHTML = "<select name='productName'><option value='0'>Select SubCategory</option></select>";
		
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


function setQuantity(){
	var viewsize = document.getElementById('viewsize').value;
	var prodid =  document.getElementById('subCategoryID').value;
	var url = "quantityBarcode?viewsize="+viewsize+"&bcodecplorsize="+bcodecplorsize+"&prodid="+prodid+"";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setQuantityRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}


function setQuantityRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('quantity').value = req.responseText;
			
		}
	}

}





function addColor(){
	
	//bcodecplorsize = bcodecplorsize +  $("#adcolor option:selected").text() + ",";
	bcodecplorsize = "Red,";
	document.getElementById('color').value = bcodecplorsize;
	
	setQuantity();
}


function setdateforbarcode(){
	var subCategoryID = document.getElementById('subCategoryID').value;
	setStock(subCategoryID);
}
