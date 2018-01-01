var	totalsubjectid=0;
var currentQuantity = 0;
var temPay = 0;
$(document).ready(function(){
	
	document.getElementById('billbtn').disabled="disabled";
	document.getElementById('addbtn').disabled = "disabled";
	document.getElementById('rmovbtn').disabled = "disabled";	
	
	
		var validator = $("#product_form").validate({
		onsubmit: true,
		rules : {
			
			/*categoryID : {
				selected : true
				},	
			gender : {
				selected : true
				},	
			productName : {
				selected : true
				},	
			
				
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
				gender : {
				 selected : error.gender.selected
				},
				
				productName : {
				selected : error.productName.selected
				},
				
			 /* description : {
						required : error.description.required
					  },*/
				
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
var colorData = "";
function setCategoryID(id){
	size = "";
	click = 0;
	colorData = "";
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
    		 document.getElementById('stock').value = "";
    		 document.getElementById('imgview').innerHTML = "";
    		 document.getElementById('modelnumber').value = "";
    		 document.getElementById('articlenumber').value = "";
    		 document.getElementById('color').value = "";
    		 document.getElementById('price').value = "";
    		 
    		 
    		
    	}
	}
}

function setStock(subcategoryID){
	document.getElementById('barcodeData').value = "";
	document.getElementById('barcodeData').disabled = "disabled";
	size = "";
	click = 0;
 	colorData = "";
	tempsubcategoryid = subcategoryID;
	
	var url = "stockPurchase?categoryD="+categoryD+"&subcategoryID="+subcategoryID+"";

   
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
			//document.getElementById('adcolor').value = 0;
			
			
			
			
			
		}
	}
}


function addSize(){
	if(document.getElementById('price').value != ""){
			document.getElementById('addbtn').disabled = "";
			document.getElementById('rmovbtn').disabled = "";
			
		var viewsize = document.getElementById('viewsize').value;
		var tempviewsize = viewsize.split(",");
		document.getElementById('quantity').value = tempviewsize.length;
		
	}else{
		
		alert("Please Enter Price!!");
	}
	

	
	
}

function RemoveSize(){
	document.getElementById('rmovecolorbtn').disabled = "";
	document.getElementById('rmovesizebtn').disabled = "disabled";
	
	
	if(document.getElementById('viewsize').value != ""){
		click = parseInt(click) - 1;
		size = size.substring(0, size.length - 3);
		document.getElementById('viewsize').value = size.substring(0, size.length - 1);
		document.getElementById('quantity').value = click;
		
		if(document.getElementById('viewsize').value == ""){
			document.getElementById('addbtn').disabled = "disabled";
			
		}
		
	}else{
		alert("Please Add Size!!");
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
	}else{
		//updateStock();
		if(document.getElementById('color').value ==""){
			alert("Please enter color");
		}else if(document.getElementById('price').value == ""){
			alert("Please enter price");
		}else if(document.getElementById('quantity').value == ""){
			alert("Please enter quantity");
		}else{
			purchaseAjax();
			 $("#gender").focus();
		}
		
	}

}

function purchaseAjax(){
	
	var quantity = document.getElementById('quantity').value;
	var price = document.getElementById('price').value;
	var viewsize = document.getElementById('viewsize').value;
	var colorsize = document.getElementById('color').value;
	var gender = document.getElementById('gender').value;
	var pcode = document.getElementById('pcode').value;
	
	var url = "setPurchase?categoryD="+categoryD+"&subcategoryID="+tempsubcategoryid+"&quantity="+quantity+"&price="+price+"&viewsize="+viewsize+"&colorsize="+colorsize+"&gender="+gender+"&pcode="+pcode+" ";
   
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
		deleteAjax();
		
	}
	

}

function deleteAjax(){

	var url = "deletePurchase?selectedItem="+totalsubjectid+" ";
   
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
    
	var dtype = document.getElementById('dtype').value;
	
	if(dtype=='1'){
		pay = temPay - vat;
		document.getElementById('pay').innerHTML = parseFloat(pay);
		document.getElementById('paymentrecieved').value = parseFloat(pay);
		
	}else{
		
		per = (vat*temPay)/100;
		pay = temPay - per;
		document.getElementById('pay').innerHTML = parseFloat(pay);
		document.getElementById('paymentrecieved').value = parseFloat(pay);
	}
	
	
}

var barcodeData = "";
function setBarcodeAjax(){
	barcodeData = document.getElementById('barcodeData').value;
	var temp = barcodeData.split(" ");
	var url = "barcodePurchase?articleName="+temp[0]+"&barcodesize="+temp[1]+"&barcodeprice="+temp[2]+"&barcodecolor="+temp[3]+"";
   
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
			categoryD = document.getElementById('hdncatid').value;
			setStock1(document.getElementById('hdnprodid').value,document.getElementById('gender').value);
			
			if( document.getElementById('gender').value == 1){
				document.getElementById('gendertext').value = "Gents";
			}else{
				document.getElementById('gender').value = "Ladies";
			}
			
			
    	 }
	}
}


function setStock1(subcategoryID,gender){
	
	size = "";
	click = 0;
	tempsubcategoryid = subcategoryID;
	
	var url = "bstockPurchase?categoryD="+categoryD+"&subcategoryID="+subcategoryID+"&gender="+gender+" ";
   
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
			
			if(document.getElementById('barcodeData').value!=""){
			
				var temp = barcodeData.split(" ");
				barcodeViewSize = barcodeViewSize + temp[1] + ",";
				//document.getElementById('viewsize').value = barcodeViewSize.substring(0,barcodeViewSize.length-1);
				document.getElementById('price').value = temp[2];
				//bquantity = parseInt(bquantity)+1;
				//document.getElementById('quantity').value = bquantity;				
				
				document.getElementById('addbtn').disabled = "";
				document.getElementById('rmovbtn').disabled = "";
				
				
				
				
				save();
				
				//document.getElementById('viewsize').value = "";
				barcodeViewSize = "";
				document.getElementById('barcodeData').value = "";
			}
			
			
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
}

function barcodeQuantity(){

	document.getElementById('rmovecolorbtn').disabled = "";
	document.getElementById('rmovesizebtn').disabled = "disabled";
	
	
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

function setBarcodeAjax1(){
	//barcodeData = document.getElementById('barcodeData').value;
	var viewsize = document.getElementById('viewsize').value;
	var barcodecolorsize = document.getElementById('color').value;
	var temp = barcodeData.split(" ");
	var url = "rbarcodePurchase?articleName="+temp[0]+"&barcodesize="+temp[1]+"&barcodeprice="+temp[2]+"&viewsize="+viewsize+"&barcodecolor="+temp[3]+"&barcodecolorsize="+barcodecolorsize+" ";
   
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
			setStock1(document.getElementById('hdnprodid').value,document.getElementById('gender').value);
			
			
    	 }
	}
}




function addColor(){
	
	document.getElementById('color').value = $("#adcolor option:selected").text();
}





function setvchkEnable(){
	document.getElementById('addbtn').disabled = "";
		document.getElementById('rmovbtn').disabled = "";
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
	
	var url = "stockAccessories?accessoriesID="+accessoriesID+" ";
   
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


function saveAccessories(){
	var accessoriesID = document.getElementById('accessories').value;
	var quantity = document.getElementById('quantity').value;
	var price = document.getElementById('price').value;
	
	var url = "purchaseAccessories?accessoriesID="+accessoriesID+"&quantity="+quantity+"&price="+price+" ";
   
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
	}else{
		document.getElementById('product_form').submit();
		
	}
	}
	
	function setpurchase(value){
	
		if(document.getElementById('price').value==''){
			alert('Please enter price!!');
			document.getElementById('quantity').value = '';
		}else if(document.getElementById('viewsizetxt').value==''){
			alert('Please enter sjze!!');
			document.getElementById('quantity').value = '';
		}else{
			var sze = document.getElementById('viewsizetxt').value;
			var str = "";
			for(i=1; i<=value; i++ ){
			    str = str+sze+",";
			}
			document.getElementById('viewsize').value = str;
		}

	
	
}
	
	
	function setpurchaseqsze(){
		var q = document.getElementById('quantity').value;
		setpurchase(q);
	}	
