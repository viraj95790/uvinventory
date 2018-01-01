$(document).ready(function(){
	var validator = $("#editbranchfrm").validate({
		onsubmit: true,
		rules : {
			
				
		   branchName : {
		   		required : true
				}
		 
		},
		messages : {
				
		        branchName : {
						required : error.branchName.required
					  }
		
		
		
		
		
		},
		
	});
	
	
});	