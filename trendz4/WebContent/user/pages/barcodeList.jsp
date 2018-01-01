<%@taglib uri="/struts-tags" prefix="s" %>

 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>



<%@page import="java.util.ArrayList"%>
<%@page import="com.inventorymanagement.main.eu.entity.Barcode"%>

			

<style>
			.paddniltopase{
				padding-top:35px;
			}
			p {
    margin: 0 0 0px;
    text-align: center;
}
.setimg{
    width: 100%;
    margin-left: auto;
    margin-right: auto;
        height: 40px;
     }       
		</style>
    
  
    
    		<%if(session.getAttribute("totalBarcodeList")!=null)%><% {%>
    		<%int t=0; %>
    			<%ArrayList<Barcode>totalBarcodeList = (ArrayList<Barcode>)session.getAttribute("totalBarcodeList"); %>
    			<% int count=0;String tdpadding = "padding-top:0%"; int lenght = totalBarcodeList.size()-1; %>
    			
	    				
	    			<%for(int z=0;z<lenght;z++) %><% {%>
	    				<% if(t==totalBarcodeList.size())
    						{
    							break;
    							}%>
	    				
    				
    				<div class="row">
    					<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="margin-bottom: 15px;">
    					
    					<% for(int j=1;j<=4;j++){%>
    					<% if(t==totalBarcodeList.size())
    						{
    							break;
    							}%>
    						<% Barcode b = totalBarcodeList.get(t);%>
    						
    						<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" style="font-size: 10px;">
    					
    							<img  src="subimage/<%=b.getImageName() %>" class="img-responsive setimg" ></img>
    							<p> Size : <%=b.getSize() %> , Code : <%=b.getPcode() %> </p>	
	    						<p>Rs : <%=b.getMrp() %> </p>
    						</div>
    						
							<%t++; %>			
											
    						
	            		<%} %>
	            	</div>	 
	            	 </div>            	
    			<%} %>
	          	
	            	
    			<%} %>
    	
    		
    		
    		
    	
    		
    		<!--<input type="button" name="print" value="Print" onclick="printpage();">
    
    
    --><script>
function printpage()
  {
  window.print()
  }
</script>
      	
 
		
		
	
				
				
				
				
				
				
				
       			
       			
	