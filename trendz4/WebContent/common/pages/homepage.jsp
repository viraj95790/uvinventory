<%@page import="com.inventorymanagement.main.web.common.helper.LoginHelper"%>
<%@page import="com.inventorymanagement.main.web.common.helper.LoginInfo"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

	<link href="common/css/datePicker1.css" rel="stylesheet"/>
	<script src="common/js/date.js" type="text/javascript"></script>
	<script src="common/js/jquery/jquery.datePicker.js" type="text/javascript"></script>
	<script src="common/js/CalenderPicture.js" type="text/javascript"></script>
	
	  <link rel="stylesheet" href="chosen/docsupport/prism.css"> 
  <link rel="stylesheet" href="chosen/chosen.css">
	
	<%LoginInfo loginInfo = LoginHelper.getLoginInfo(request); %>
	
<s:form action="Default" theme="simple">
<div class="main_layout_content">
	
 	<div id="edit_contact_details" class="block_div" >	
 		<table width="100%" style="border: 1px solid #e69623; height:0px ; padding: 1em; ">
 			<tr>
 				<td align="center"> <img src="" style="width: 250px;"/></td>
 			</tr>
 			<tr>
 				<td align="">
 						 <table width="100%">
 						 	<tr>
 						 		
 						 		<td>
									<table width="100%">
										<col width="1%"/>
										<col width="2%"/>
										<col width=5%"/>
										<col width="5%"/>
										<col width="2%"/>
										<col width="5%"/>
										<col width="5%"/>
										<col width="2%"/>
										<col width="5%"/>
										<tr>
											<td>Shop<span class="reqd-info">*</span></td>
											<td align="center">:</td>
											<td>
												<%if(loginInfo.getId()>1){ %>
												<s:select disabled="true" name="userid" id="userid" list="userList"
												listKey="userId" listValue="companyName" 
												headerKey="0" headerValue="Select Shop"
												
												/>
												<% }else{%>
													<s:select name="userid" id="userid" list="userList"
												listKey="userId" listValue="companyName" 
												headerKey="0" headerValue="Select Shop"
												
												/>
												<% }%>
											</td>
										
											<td>From Date<span class="reqd-info">*</span></td>
											<td align="center">:</td>
											<td><s:textfield cssStyle="width:98px;" name="fromDate" id="fromDate" cssClass="date-pick dp-applied"/></td>
											
											<td>To Date<span class="reqd-info">*</span></td>
											<td align="center">:</td>
											<td><s:textfield cssStyle="width:98px;" name="toDate" id="toDate" cssClass="date-pick dp-applied"/>
											<input type="submit" value="Go"/>
											</td>
										</tr>
									</table>
								</td>
								
								
 						 		
 						 	</tr>
 						 	<tr>
 						 		<td>
 						 			 <table width="100%" style="border: 1px solid gray; height:0px ; padding: 1em; ">
 						 			 	<col width="10%">
 						 			 	<col width="2%">
 						 			 	<col width="90%">
 						 			 	<%if(loginInfo.getId()==1){ %>
 						 			 	<tr>
 						 			 		<td>Purchase</td>
 						 			 		<td align="center">:</td>
 						 			 		
 						 			 		<td>
 						 			 				Total Purchase : <s:text name="%{invoiceList.size}"/>
													>> Total Quantity : <s:text name="%{totalQuantity}"/>
													>> Total Amount : Rs. <s:text name="%{totalAmount}"/> 
				
 						 			 		</td>
 						 			 		
 						 			 	</tr>
 						 			 	<%} %>
 						 			 	<tr>
 						 			 		<td>Sale</td>
 						 			 		<td align="center">:</td>
 						 			 		<td>
 						 			 				Total Sale : <s:text name="%{saleinvoiceList.size}"/>
													>> Total Quantity : <s:text name="%{saletotalQuantity}"/>
													>> Total Amount : Rs. <s:text name="%{saletotalAmount}"/>
 						 			 		</td>
 						 			 	</tr>
 						 			 	<tr>
 						 			 		<td valign="top">Attendance</td>
 						 			 		<td valign="top" align="center">:</td>
 						 			 		<td style="font-weight: bold;color: green;">
 						 			 			<s:iterator value="salesmanlist">
 						 			 				<div><u><s:property value="firstName"/> <s:property value="lastName"/> : </u></div>
 						 			 				
 						 			 				<s:if test="attandanceList.size!=0">
				<table id="results" cellpadding="0" cellspacing="0" class="my-table"  style="width:100%;">
					<tr>
						
						
						
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Date</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Status</th>
						
					</tr>
					
						<s:iterator value="attandanceList" status="rowstatus">
						<tr>	
							<s:if test="#rowstatus.even == true">
								<tr class="ac_odd">
							</s:if>
									
									
									<td><s:property value="date"/></td>
									<td><a href="#">Present</a></td>
									
							</tr>

						</s:iterator>
					
					
				
							
				</table>
			
				</s:if>
				
 						 			 				
 						 			 			</s:iterator>
 						 			 		
 						 			 		</td>
 						 			 	</tr>
 						 			 	
 						 			 	
	 						 			 	<tr>
	 						 			 		<td>Product Stock</td>
	 						 			 		<td align="center">:</td>
	 						 			 		<td>
	 						 			 				<s:select name="pname" id="pname" list="shopStockList"
	 						 			 				listKey="id" listValue="productName" cssClass="chosen-select"
	 						 			 				headerKey="0" headerValue="View Stock" cssStyle="width:151px;"
	 						 			 				/>
	 						 			 		</td>
	 						 			 	</tr>
 						 			 	
 						 			 	
 						 			 </table>
 						 		</td>
 						 	</tr>
 						 </table>		
 				</td>
 			</tr>
 		</table>
		
	</div>
</div>

</s:form>



			<script src="chosen/chosen.jquery.js" type="text/javascript"></script>
			<script src="chosen/docsupport/prism.js" type="text/javascript"
				charset="utf-8"></script>
			<script type="text/javascript">
				var config = {
					'.chosen-select' : {},
					'.chosen-select-deselect' : {
						allow_single_deselect : true
					},
					'.chosen-select-no-single' : {
						disable_search_threshold : 10
					},
					'.chosen-select-no-results' : {
						no_results_text : 'Oops, nothing found!'
					},
					'.chosen-select-width' : {
						width : "95%"
					}
				}
				for ( var selector in config) {
					$(selector).chosen(config[selector]);
				}
			</script>

