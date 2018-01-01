$(document).ready(function(){
	var validator = $("#product_form").validate({
		onsubmit: true,
		rules : {
			
			categoryID : {
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
				} ,
			
			quantity : {
		   		required : true
				} ,*/
				
			price : {
		   		required : true
				} 
		 
		
		
		
		},
		messages : {
		
				categoryID : {
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
					  },
				
				quantity : {
						required : error.quantity.required
					  },*/
					  
				price : {
						required : error.price.required
					  }
		
		
		
		
		
		},
		
	});
	
	
});	


var categoryD = 0;
var tempsubcategoryID = 0;

function setCategoryID(id){
	
	categoryD = id;
	
	var url = "subcategoryAjaxProduct?id="+id+"";
   
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


function setPrice(subcategoryID){
	
	tempsubcategoryID = subcategoryID;
	var url = "priceAdminSubcategory?categoryD="+categoryD+"&subcategoryID="+subcategoryID+"";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setPriceRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function setPriceRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById("priceajax").innerHTML = req.responseText;
         
         }
	}
}


