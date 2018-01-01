<%@taglib uri="/struts-tags" prefix="s" %>



<%@page import="java.util.ArrayList"%>
<%@page import="com.inventorymanagement.main.eu.entity.Barcode"%>

			

  <table   cellpadding="0" cellspacing="0">
    
  
    
    		<%if(session.getAttribute("totalBarcodeList")!=null)%><% {%>
    			<%ArrayList<Barcode>totalBarcodeList = (ArrayList<Barcode>)session.getAttribute("totalBarcodeList"); %>
    			<% int count=0;String tdpadding = "padding-top:0%"; %>
    			
	    				
	    			<%for(Barcode barcode : totalBarcodeList) %><% {%>
	    				
	    				
    				
    					<%if(count%5==0) {%>
    						<tr></tr>
    					<%} %>
    					
    					<% if(count==5){
	    						tdpadding = "padding-top:5%";
	    					}
	    					%>
    						
    					<% if(count==10){
    						tdpadding = "padding-top:8%";
    					}
    					%>
    					
    					<% if(count==40){
    						tdpadding = "padding-top:0%";
    						count = 0;
    					}
    					%>
    					
    					
    				
    				<td width="20%" style="<%=tdpadding %>;padding-right:1% ">
    					
	            		<table id="bartable"  width="40%"   style="border: none; width:0px; height: 0px;  ">
							
							
							
							<tr>
								
								<td style="padding-left: 20px; font-size: 12px;"><%=barcode.getCompanyName() %></td>
							</tr>
							
							<tr>
								
								<td>
										
									<img style="width: 190px;height: 40px;" src="subimage/<%=barcode.getImageName() %>" >
								</td>
							</tr>
							<tr>
								<td width="100%" style="padding-left: 0px;">
									<table style="font-size: 12px;padding-left: 7%">
										<tr>
											<td>Prod : <%=barcode.getArticleNumber() %></td>
											<td>Size : <%=barcode.getSize() %></td>
										</tr>
										<tr>
											<td>Color : <%=barcode.getColor() %></td>
											<td>Rs : <%=barcode.getMrp() %></td>
										</tr>
										<tr><td></td></tr>
										<tr><td></td></tr>
										<tr><td></td></tr>
										</table>
								
								</td>
							</tr>
							
						</table>
	            	</td>
	            	
	            
    						<%count++; %>
    						
    						
	            		<%} %>
	            	
    			<%} %>
	            	
	            	
    			
    	
    		
    		
    		</table>
    	
    		
    		<!--<input type="button" name="print" value="Print" onclick="printpage();">
    
    
    --><script>
function printpage()
  {
  window.print()
  }
</script>
      	
 
		
		
	
				
				
				
				
				
				
				
       			
       			
	