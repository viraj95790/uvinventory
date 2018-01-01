$(document).ready(function(){
	var validator = $("#category_form").validate({
		onsubmit: true,
		rules : {
			
				
			colorName : {
				required : true,
				
				
				}
			
				
		  /* description : {
		   		required : true
				}*/
		 
		
		
		
		},
		messages : {
				
				
				colorName : {
						required : error.colorName.required,
						
					  }
					  
				
		
		      /* description : {
						required : error.description.required
					  }*/
		
		
		
		
		
		},
		
	});
	
	
});	