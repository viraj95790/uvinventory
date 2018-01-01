var	totalsubjectid=0;
var currentQuantity = 0;
var temPay = 0;
var globelpsizeid = 0;
$(document).ready(function(){
	


	
	
	document.getElementById('billbtn').disabled="disabled";
	document.getElementById('adsizebtn').disabled = "disabled";
	document.getElementById('rmovesizebtn').disabled = "disabled";
	document.getElementById('addbtn').disabled = "disabled";
	document.getElementById('rmovbtn').disabled = "disabled";
	
		var validator = $("#product_form").validate({
		onsubmit: true,
		rules : {
			
			/*categoryID : {
				selected : true
				},	
			productName : {
				selected : true
				},	*/
			employeeid : {
					selected : true
				}	
			
				
		 /*  description : {
		   		required : true
				} ,*/
			
			/*quantity : {
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
		
				/*categoryID : {
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
				}
				
				/*quantity : {
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
	
	
});	


var categoryD = 0;
var tempsubcategoryid = 0;
var size = "";
var click = 0;
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
	document.getElementById('barcodeData').value = "";
	document.getElementById('barcodeData').disabled = "disabled";
	size = "";
	click = 0;
	productidseries = "";
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
		
			document.getElementById('purchasetable').innerHTML = req.responseText;
			document.getElementById('adsizebtn').disabled = "";
			document.getElementById('rmovesizebtn').disabled = "";
    	 }
	}
}

var productSize = "";
var priductid = "";
var productidseries = "";
var rmovprodctsize = "";
function addSize(){

	if(document.getElementById('stockQuantity').value != 0){
		if(document.getElementById('adsize').value != 0){
				click = parseInt(click)+1;
				size = size + $("#adsize option:selected").text() + ",";
				document.getElementById('viewsize').value = size.substring(0, size.length - 1);
				document.getElementById('quantity').value = click;
				
				priductid =  document.getElementById('adsize').value;
				productidseries = productidseries + document.getElementById('adsize').value + ",";
				productSize = document.getElementById('viewsize').value;
				
				document.getElementById('addbtn').disabled = "";
				document.getElementById('rmovbtn').disabled = "";
				var selectedColor = document.getElementById('color').value;
				
				adproductSizeAjax(priductid,productSize,click,selectedColor);
		}else{
			alert("Plese Select Size!!");
			
		}
	
		
	
	}else{
		alert("There is no stock for this product!!");
	}
	
}

function adproductSizeAjax(priductid,productSize,click,selectedColor){
	var url = "addsizeSale?priductid="+priductid+"&productSize="+productSize+"&categoryD="+categoryD+"&subcategoryID="+tempsubcategoryid+"&click="+click+"&productidseries="+productidseries+"&selectedColor="+selectedColor+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = adproductSizeAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}


function adproductSizeAjaxRequest(){
 if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById('purchasetable').innerHTML = req.responseText;
			
    	 }
	}
}

function RemoveSize(){
	if(document.getElementById('viewsize').value != ""){
		rmovprodctsize = document.getElementById('viewsize').value;
		click = parseInt(click) - 1;
		var tsize = rmovprodctsize.split(",");
		var num = tsize.length - 1;
		
		var psize = "";
	
		for(i=0;i<num;i++){
			psize = psize+tsize[i] + ",";
			
		}
		
		//psize = psize.substring(0, size.length - 1);
		document.getElementById('viewsize').value = psize.substring(0, psize.length - 1);
		size = psize;
		
		
		document.getElementById('quantity').value = click;
		var selectedColor = document.getElementById('color').value;
		
		if(document.getElementById('viewsize').value == ""){
			document.getElementById('addbtn').disabled = "disabled";
		}
		
			rmovProductSizeAjax(productidseries,productSize,click,rmovprodctsize,selectedColor);
			
			
		
	}else{
		
		alert("Please Add Size!!");
	}
	
}

function rmovProductSizeAjax(productidseries,productSize,click,rmovprodctsize,selectedColor){
	var url = "rmovesizeSale?productidseries="+productidseries+"&productSize="+rmovprodctsize+"&categoryD="+categoryD+"&subcategoryID="+tempsubcategoryid+"&click="+click+"&selectedColor="+selectedColor+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = rmovProductSizeAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function rmovProductSizeAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById('purchasetable').innerHTML = req.responseText;
			productidseries = document.getElementById('tempcolorid').value;
		
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
	var colorsize = document.getElementById('color').value;
	var gender = document.getElementById('gender').value;
	
	if(document.getElementById('chb').checked == true){
		 viewsizeid = document.getElementById('viewsizeid').value;
		 var chb = document.getElementById('chb').checked;
	}
	
	var url = "setSale?categoryD="+categoryD+"&subcategoryID="+tempsubcategoryid+"&quantity="+quantity+"&price="+price+"&viewsize="+viewsize+"&productidseries="+productidseries+"&viewsizeid="+viewsizeid+"&chb="+chb+"&colorsize="+colorsize+"&gender="+gender+" ";
   
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
			document.getElementById('paymentrecieved').value = temPay;
			
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
		
		 $('.case').each(function() { //loop through each checkbox
		        // this.checked = true;  //select all checkboxes with class "checkbox1" 
		        if(this.checked==true){
		        	totalsubjectid = totalsubjectid + ',' + this.value;
		        }
		         
		     });
		
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


function addVat(vat){
	/*per = (vat*temPay)/100;
	pay = temPay-per;
	document.getElementById('pay').innerHTML = parseFloat(pay);*/
	
	document.getElementById('rupee').value = 0;
	
	var url = "vatAccessories?vat="+vat+"&rupee=0 ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = addvatAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function addvatAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById('saletable').innerHTML = req.responseText;
			var payment = document.getElementById('pay').innerHTML;
			document.getElementById('paymentrecieved').value = parseFloat(payment);
			
		}
	}
}



function addRate(rupee){

	document.getElementById('vat').value = 0;
	
	var url = "vatAccessories?rupee="+rupee+"&vat=0 ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = addRateAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function addRateAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById('saletable').innerHTML = req.responseText;
			var payment = document.getElementById('pay').innerHTML;
			document.getElementById('paymentrecieved').value = parseFloat(payment);
		}
	}
}


var barcodeData = "";

function setBarcode(){
	
	barcodeData = document.getElementById('barcodeData').value;
	var temp = barcodeData.split("~");
	var url = "containSale?articleName="+temp[0]+"&barcodesize="+temp[1]+"&barcodeprice="+temp[2]+"&barcodecolor="+temp[3]+"&globelpsizeid="+globelpsizeid+" ";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setBarcodeRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
	
}

function setBarcodeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			if(req.responseText == "false"){
				alert("You have entered '"+barcodeData+"' is not available in stock!!");
				document.getElementById('barcodeData').value = "";
			}else{
				categoryD = document.getElementById('hdncatid').value;
				addSizeAjax1(document.getElementById('hdnprodid').value);
			}
			
		}
	}
		
}





function setBarcodeAjax(){
	barcodeData = document.getElementById('barcodeData').value;
	var temp = barcodeData.split("~");
	var url = "barcodeSale?articleName="+temp[0]+"&barcodesize="+temp[1]+"&barcodeprice="+temp[2]+"&barcodecolor="+temp[3]+"&globelpsizeid="+globelpsizeid+"";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setBarcodeAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setBarcodeAjaxRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			
			document.getElementById('dropdowntable').innerHTML = req.responseText;
			//document.getElementById('categoryID').value = document.getElementById('hdncatid').value;
			//document.getElementById('subCategoryID').value = document.getElementById('hdnprodid').value;
			$("#barcodeData").focus();
			
			setBarcode();
			document.getElementById('rmovbtn').disabled = "";
			
			
    	 }
	}
}




function setEnable(){

	

var str = "<table width='100%' style='border: 1px solid #e69623; height: auto; padding: 1em; font-size: 12px; overflow: hidden; white-space: nowrap; table-layout: fixed'>";
	str = str + "<col width='5%'/>";
	str = str + "<col width='15%'/>";
	str = str + "<col width='10%'/>";
	str = str + "<col width='10%'/>";
	str = str + "<col width='10%'/>";
	str = str + "<col width='10%'/>";
	str = str + "<tr>";
	str = str + "<th></th>";
	str = str + "<th align='left'>Product Name</th>";
	str = str + "<th align='left'>Size</th>";
	str = str + "<th align='left'>Quantity</th>";
	str = str + "<th align='left'>MRP</th>";
	str = str + "<th align='left'>Total</th>";
	str = str + "</tr>";
	str = str + "</table>";
	

			 //document.getElementById('saletable').innerHTML = str;
			 document.getElementById('barcodeData').disabled = "";
			  if(document.getElementById('chb').checked == false){
    			document.getElementById('barcodeData').disabled = "disabled";
    			swapSizeAjax();
    		 }
}
			 //document.getElementById('categoryID').value = 0;
			 //document.getElementById("productajax").innerHTML = "<select name='productName'> <option value='0'>Select SubCategory</option></select>";
			 document.getElementById('stock').value = "";
    		 document.getElementById('imgview').innerHTML = "";
    		 document.getElementById('modelnumber').value = "";
    		 document.getElementById('articlenumber').value = "";
    		 document.getElementById('color').value = "";
    		 document.getElementById('price').value = "";
    		 document.getElementById('adsize').value = 0;
    		 document.getElementById('quantity').value = "";
    		 document.getElementById('viewsize').value = "";
    		 
    		

function barcodeQuantity(){
	
	str = document.getElementById('viewsize').value;
	str = str.substring(0, str.length-3);
	
	//str = str.substring(0,str.length-1);
	var temp = str.split(",");
	document.getElementById('viewsize').value = str;
	document.getElementById('quantity').value = temp.length;
	
	if(str.length > 1){
		setBarcodeAjax1();
		save();
	}
	
}

function swapSizeAjax(){

	var url = "swapSale";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = swapSizeAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}


function swapSizeAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('dropdowntable').innerHTML = req.responseText;
         
         }
	}

}

function setBarcodeAjax1(){
	//barcodeData = document.getElementById('barcodeData').value;
	var viewsize = document.getElementById('viewsize').value;
	var temp = barcodeData.split("~");
	var url = "rbarcodeSale?articleName="+temp[0]+"&barcodesize="+temp[1]+"&barcodeprice="+temp[2]+"&viewsize="+viewsize+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setBarcodeAjaxRequest1;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setBarcodeAjaxRequest1(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			
			document.getElementById('dropdowntable').innerHTML = req.responseText;
			//document.getElementById('categoryID').value = document.getElementById('hdncatid').value;
			//document.getElementById('subCategoryID').value = document.getElementById('hdnprodid').value; 
			categoryD = document.getElementById('hdncatid').value;
			setStock1(document.getElementById('hdnprodid').value);
			
			
    	 }
	}
}


function addSizeAjax1(subcategoryID){
	tempsubcategoryid = subcategoryID;
	barcodeData = document.getElementById('barcodeData').value;
	var temp = barcodeData.split("~");
	var url = "baddsizeSale?productSize="+temp[1]+"&articleName="+temp[0]+"&price="+temp[2]+"&barcodecolor="+temp[3]+"&globelpsizeid="+globelpsizeid+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = addSizeAjaxRequest1;
    req.open("GET", url, true); 
              
    req.send(null);
}


function addSizeAjaxRequest1(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('purchasetable').innerHTML = req.responseText;
			save();
			document.getElementById('barcodeData').value = "";
			//document.getElementById('viewsizeid').value = "";
         
         }
	}

}


function removeBarcodeSize(){
	
	var temp = barcodeData.split(" ");
	var url = "brmovesizeSale?productID="+tempsubcategoryid+"&categoryD="+categoryD+"&price="+temp[2]+"&barcodecolor="+temp[3]+" ";  
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = addSizeAjaxRequest1;
    req.open("GET", url, true); 
              
    req.send(null);

}

function removeBarcodeSizeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('purchasetable').innerHTML = req.responseText;
			save();
			
				
         
         }
	}
}


function addColor(){
	productSize = "";
	productidseries = "";
	document.getElementById('color').value = $("#adcolor option:selected").text();
 	var selectedColor = document.getElementById('color').value;
 	document.getElementById('viewsize').value = "";
 	size = "";
	click = 0;
 	
	var url = "addcolorSale?productID="+tempsubcategoryid+"&categoryD="+categoryD+"&selectedColor="+selectedColor+" ";  
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = addColorRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}


function addColorRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('adsizeajax').innerHTML = req.responseText;
			
			
				
         
         }
	}

}



/* Accessories Coding  */

function setAccessories(){

	if(document.getElementById('cha').checked == true){
		document.getElementById('chb').disabled = "disabled";
		document.getElementById('barcodeData').disabled = "disabled";
		setAccessoriesAjax();
		var str = "<input type='button' id='addbtn' value='Add'  onclick='saveAccessories();'/>";
		str = str + "<input type='button' id='rmovbtn' value='Remove' onclick='deletesale();'/>";
		document.getElementById('adrmovtd').innerHTML = str;
		
	}else{
		document.getElementById('chb').disabled = "";
		swapSizeAjax();
		var str = "<input type='button' id='addbtn' value='Add'  onclick='save();'/>";
		str = str + "<input type='button' id='rmovbtn' value='Remove' onclick='deletesale();'/>";
		document.getElementById('adrmovtd').innerHTML = str;
	}
}



function setAccessoriesAjax(){
	var url = "setAccessories";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setAccessoriesAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function setAccessoriesAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById('dropdowntable').innerHTML = req.responseText;
			
		}
	}
}


function stockAccessories(accessoriesID){
	
	var url = "stocksaleAccessories?accessoriesID="+accessoriesID+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = stockAccessoriesRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function stockAccessoriesRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById('purchasetable').innerHTML = req.responseText;
			document.getElementById('billbtn').disabled = "";
		}
	}
}


function setAccessoriesQuantity(quantity){
	var stock = document.getElementById('stock').value
	var result = 0;
	st = parseInt(stock);
	qt = parseInt(quantity);
	if(st >= qt){
		result = st - qt;
		document.getElementById('stock').value = result;
	}else{
		alert("You can not enter quantity grater than stock !!")
		document.getElementById('quantity').value = "";
	}
	
}


function saveAccessories(){
	if(document.getElementById('quantity').vlaue == ""){
		alert("Please Enter Quantity !!");
	}else{
		saveAccessoriesAjax();
	}
}

function saveAccessoriesAjax(){
	var accessoriesID = document.getElementById('accessories').value;
	var quantity = document.getElementById('quantity').value;
	var price = document.getElementById('price').value;
	
	var url = "saleAccessories?accessoriesID="+accessoriesID+"&quantity="+quantity+"&price="+price+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = saveAccessoriesRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function saveAccessoriesRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById('saletable').innerHTML = req.responseText;
			
		}
	}

}


function isNumberKey(evt, element) {
	  var charCode = (evt.which) ? evt.which : event.keyCode
	  if (charCode > 31 && (charCode < 48 || charCode > 57) && !(charCode == 46 || charCode == 8))
	    return false;
	  else {
	    var len = $(element).val().length;
	    var index = $(element).val().indexOf('.');
	    if (index > 0 && charCode == 46) {
	      return false;
	    }
	    if (index > 0) {
	      var CharAfterdot = (len + 1) - index;
	      if (CharAfterdot > 3) {
	        return false;
	      }
	    }

	  }
	  return true;
	}


function savepurchasedata(){
	if(document.getElementById('howpaid').value==0){
		alert('Please select payment mode!');
	}else if(document.getElementById('paymentrecieved').value==''){
		alert('Please enter payment!');
	}else if(document.getElementById('customerName').value==''){
		alert("Please select client or enter clientname!!");
	}
	else{
		document.getElementById('product_form').submit();
		
	}
}

function getClientInfo(id){
	
	var url = "infoCustomer?selectedid="+id+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getClientInfoRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getClientInfoRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data = req.responseText;
			var temp = data.split('~');
			document.getElementById('mobileNo').value = temp[0];
			document.getElementById('customerName').value = temp[1];
			
		}
	}
}


function setPsizeID(psizeid){
	globelpsizeid = psizeid;
	var url = "psizeBarcode?selectedid="+psizeid+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setPsizeIDRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function setPsizeIDRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			 document.getElementById('barcodeData').value = req.responseText;
			 setBarcodeAjax();
		}
	}
}
