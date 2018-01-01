var	totalsubjectid=0;

$(document).ready(function(){

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
			 document.getElementById('selectedItem').value = totalsubjectid;
		});
	
	
});	

