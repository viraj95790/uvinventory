<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="user/js/salereturn.js"></script>
<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Sale Return List</h2>
	<span id="mandatory_message" class="error">Note:Fields marked with asterisk(*) are required.</span> 
	<div id="login" class="block_div">
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		<s:if test="hasActionMessages()"> 
			<div id="common_message" class="message">
				<s:actionmessage id="common_message_text" theme="simple"/>
			</div>
		</s:if> 
		<div>
			<a href="inputSaleReturn">Add Sale Return</a>
		</div>
		<s:form action="moveSaleReturn" id="category_form" theme="simple">
		<s:hidden name="selectedItem" id="selectedItem"/>
			<div class="form_elements">				
				<s:if test="invoiceList.size() > 0">
				
					<%int count = 1; %>
					<table id="saletable" width="100%" align="center" style="border: 1px solid #e69623; height: auto; padding: 1em; font-size:14px; font-family: Arial,Helvetica,sans-serif; table-layout: fixed;">
						
						<tr>
							<td colspan="2" style="padding-left: 100px; padding-right: 100px;" >
								<table width="100%" style="border: none; height: auto; padding: 1em; font-size: 12px; table-layout: fixed;">
									<col width="5%"/>
									<col width="5%"/>
									<col width="30%"/>
									<col width="25%"/>
									<col width="10%"/>
									<col width="10%"/>
									<col width="10%"/>
									
									<tr>
											<th>Select</th>
											<th>SN</th>
											<th>PRODUCT NAME</th>
											<th>Size</th>
											<th>M.R.P</th>
											<th>Quantity</th>
											<th>AMOUNT</th>
									
									</tr>
									<tr>
										<td colspan="7">
							---------------------------------------------------------------------------------------------------------------------------------------------------------------------</td>
									</tr>
									
									<s:iterator value="invoiceList" status="status">
										<tr>
											<td><input type="checkbox" name="chb" id="chb" value="<s:property value="id"/>"></td>
											<td align="center"><%=count %></td>
											<td align="center"><s:property value="productName"/></td>
											<td align="center"><s:property value="size"/></td>
											<td align="center">Rs.<s:property value="mrp"/></td>
											<td align="center"><s:property value="quantity"/></td>
											<td align="center">Rs.<s:property value="total"/></td>
											
										</tr>
										<%count++; %>
									</s:iterator>
									
								</table>
							</td>
						</tr>
					</table>
				
					
				
				</s:if>
				
				
				<s:submit action="moveSaleReturn" value="Move"/>
				
				<s:submit action="deleteSaleReturn" value="Delete"/>
				
				
       			
       			
		
		</s:form>
		
		
	</div>
</div>