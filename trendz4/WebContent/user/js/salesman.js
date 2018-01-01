$(document).ready(function(){
	var validator = $("#salesmanfrm").validate({
		onsubmit: true,
		rules : {
			userId : {
				required : true,
				//minlength : constraint.userId.minlength,
				nowhitespace : true,
				remote: 'CheckEmployeeID'
			},	
			
			firstName : {
				required : true,
				regex :/^[a-zA-Z ]+$/
			},
			lastName : {
				required : true,
				regex :/^[a-zA-Z ]+$/
			},
			
			
			mobileNo : {
				required : true,
				mobilenumber : true
			},
			
			address : {
				required : true
			}
			
				
		},
		messages : {	
			userId : {
				required : error.userId.required,
				minlength : jQuery.format(error.userId.minLength),	// format function replaces {0} with actual minimum length
				nowhitespace : error.userId.nowhitespace,
				remote: error.userId.alreadyExist
			},	
			
			firstName : {
				required : error.firstName.required,
				regex : error.firstName.alphabets
			},
			lastName : {
				required : error.lastName.required,
				regex : error.lastName.alphabets
			},
			
			mobileNo : {
				required : error.mobileNo.required,
				mobilenumber : error.mobileNo.mobilenumber
			},
			
			address : {
				required : error.address.required
			}
			
		
			
		},
		
	});
	
	
});	

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
