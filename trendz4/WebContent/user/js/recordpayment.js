

$(document).ready(function(){
	document.getElementById('paymentrecieved').value = document.getElementById('recbalance').innerHTML;
	
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


function savepurchasedata(){
	if(document.getElementById('howpaid').value==0){
		alert('Please select payment mode!');
	}else if(document.getElementById('paymentrecieved').value==''){
		alert('Please enter payment!');
	}else{
		document.getElementById('product_form').submit();
		
	}
}

