<%@taglib uri="/struts-tags" prefix="s" %>

<script type="text/javascript" src="common/js/pagination.js"></script>

<script type="text/javascript" src="user/js/remainstock.js"></script>

<script type="text/javascript" src="user/js/setbranch.js"></script>

<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Bill</h2>
	
	<div id="login" class="block_div">
		

	<s:form action="AdminBill" theme="simple">
	<s:hidden name="actionType" value="go"/>
				<table>
					<tr>
						<td>Select Branch : </td>
						<td>
							<s:select theme="simple" name="branchID" list="branchList" listKey="id" listValue="branchName" headerKey="0" headerValue="Select Branch" label="Select a Branch"/>
							
						</td>
						<td>
							<input type="submit" value="G0">
						</td>
					</tr>
				</table>
		</s:form>
	
		
				
     <% int count =1;%>  			
   <s:if test="categoryWiseProductList.size() > 0">
      <table id="results" width="100%" cellpadding="0" cellspacing="0" >
        <s:iterator value="categoryWiseProductList" status="currentRecord">
        	<s:if test="productList.size() > 0">
        	
        		  <s:if test="(#currentRecord.index % 2) == 0">
            <!-- Record is First of row: 1st, 5th, 9th etc -->
            <s:if test="#currentRecord.index > 0">                  
             	<!-- It's not the first, close previous row -->
            </s:if>                 
               <tr><!-- Open row -->
            </s:if>
            		<td valign="top">
                        <table  width="100%" cellpadding="0" cellspacing="0" style="padding:6px; border: 1px solid #DFD8D4;">
                        	<col width="15%"/>
                        	<col width="10%"/>
                        	<col width="10%"/>
												
												<tr>
													<th style="background:transparent url('common/images/table_header.gif"><s:property  value="categoryName" /></th>
													<th style="background:transparent url('common/images/table_header.gif">Quantity</th>
													<th style="background:transparent url('common/images/table_header.gif">Amount</th>
												</tr>
												<s:iterator value="productList" status="rowstatus">
													<tr>	
													<s:if test="#rowstatus.even == true">
													<tr class="ac_odd">
													</s:if>
														<td align="center" style="padding:6px 6px 6px 12px;"><s:property  value="productName" /></td>
														<td align="center" style="padding:6px 6px 6px 12px;"><s:property  value="remaioningStock" /></td>
														<td align="center" style="padding:6px 6px 6px 12px;"><s:property  value="totalAmount" /></td>
														
													</tr>
													<%count++;%>
												</s:iterator>
												
											</table>    
										 </td>           
        		
        	
        	</s:if>
        
    	</s:iterator>                       
                   <!-- close last open row -->
    </table>
</s:if>
	</div>
	

</div>