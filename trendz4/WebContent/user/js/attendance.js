$(document).ready(function(){
	var validator = $("#attendancefrm").validate({
		onsubmit: true,
		rules : {
				employeeid : {
					selected : true
				}	
			
			
			
				
		},
		messages : {	
			employeeid : {
				selected : error.employeeid.selected
				}
			
			
		
			
		},
		
	});
	
	
});	