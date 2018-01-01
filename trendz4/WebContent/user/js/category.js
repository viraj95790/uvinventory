$(document).ready(function(){
	var validator = $("#category_form").validate({
		onsubmit: true,
		rules : {
			
				
			categoryName : {
				required : true,
				remote: 'CheckCategoryName'
				
				},
				gender : {
				selected : true
				},	
				
		  /* description : {
		   		required : true
				}*/
		 
		
		
		
		},
		messages : {
				
				
				categoryName : {
						required : error.categoryName.required,
						remote : error.categoryName.alreadyExist
					  },
					  
				gender : {
				 selected : error.gender.selected
				},
		
		      /*  description : {
						required : error.description.required
					  }*/
		
		
		
		
		
		},
		
	});
	
	
});	