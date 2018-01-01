function setBranch(id){

	var url = "setCustomCake?id="+id+" ";
	
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setBranchRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setBranchRequest(){

if (req.readyState == 4) {
		if (req.status == 200) {
			
         
         }
	}
}


$(document).ready(function(){
	var validator = $("#customcake_form").validate({
		onsubmit: true,
		rules : {
			
				
			weight : {
				required : true,
				
			},
				
		   flavour : {
		   		required : true
			},
			
			notes : {
				required : true,
				
			},
				
		   msgOnCake : {
		   		required : true
			},
		 	
		 	amount : {
				required : true,
				
			},
				
		   deliveryDate : {
		   		required : true
			},
			time : {
				selected : true
			},
			minute : {
				selected : true
			},
			amorpm : {
				selected : true
			}	
		
		
		
		},
		messages : {
				
				
				weight : {
						required : error.weight.required,
						
					  },
		
		        flavour : {
						required : error.flavour.required
					  },
					  
				notes : {
						required : error.notes.required,
						
					  },
		
		        msgOnCake : {
						required : error.msgOnCake.required
					  },
				
				amount : {
						required : error.amount.required,
						
					  },
		
		        deliveryDate : {
						required : error.deliveryDate.required
					  },
				time : {
					selected : error.time.selected
				},
				minute : {
					selected : error.minute.selected
				},
				amorpm : {
					selected : error.amorpm.selected
				}
		},
		
	});
	
	
});	
