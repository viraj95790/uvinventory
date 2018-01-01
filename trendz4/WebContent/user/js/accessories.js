$(document).ready(function(){
	var validator = $("#category_form").validate({
		onsubmit: true,
		rules : {
			
				
			productName : {
				required : true,
				
				
				},
			
				
		   price : {
		   		required : true
				}
		 
		
		
		
		},
		messages : {
				
				
				productName : {
						required : error.productName.required,
						
					  },
					  
				
		
		       price : {
						required : error.price.required
					  }
		
		
		
		
		
		},
		
	});
	
	
});	