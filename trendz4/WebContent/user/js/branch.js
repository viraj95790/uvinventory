$(document).ready(function(){
	var validator = $("#branchfrm").validate({
		onsubmit: true,
		rules : {
			userId : {
				required : true,
				remote: 'CheckUserId'
				},
				
			password : {
				required : true
				},
				
		   branchName : {
		   		required : true
				}
		 
		
		
		
		
		
		
		},
		messages : {
				userId : {
						required : error.userId.required,
						remote: error.userId.alreadyExist
					  },
				
				password : {
						required : error.password.required
					  },
		
		        branchName : {
						required : error.branchName.required
					  }
		
		
		
		
		
		},
		
	});
	
	
});	