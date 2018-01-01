<%@taglib uri="/struts-tags" prefix="s" %>

<script type="text/javascript" src="common/js/pagination.js"></script>

<script type="text/javascript" src="user/js/setbranch.js"></script>



	<link href="common/css/datePicker1.css" rel="stylesheet"/>
	<script src="common/js/date.js" type="text/javascript"></script>
	<script src="common/js/jquery/jquery.datePicker.js" type="text/javascript"></script>
	<script src="common/js/CalenderPicture.js" type="text/javascript"></script>

<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Purchase Account</h2>
	
	<div id="login" class="block_div">
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		
			<s:form action="accountPurchaseInvoice" theme="simple">
				<!--<div>Search: <s:textfield theme="simple" name="searchText" placeholder="Search by invoice number" />
				<input type="submit" value="Go"/>
				</div>
				
				-->
				
				<%-- <div>
					<b>( Total Purchase : <s:text name="%{invoiceList.size}"/>
						>> Total Quantity : <s:text name="%{totalQuantity}"/>
						>> Total Amount : Rs. <s:text name="%{totalAmount}"/> )
					</b>
				</div> --%>
				
				
							<table width="100%"  style="padding-left: 100px;">
					<col width="45%"/>
					<col width="45%"/>
					<col width="10%"/>
					<tr>
						<td>
							<table width="100%">
								<col width="5%"/>
								<col width="2%"/>
								<col width="8%"/>
								<tr>
									<td>From Date<span class="reqd-info">*</span></td>
									<td align="center">:</td>
									<td><s:textfield name="fromDate" id="fromDate" cssClass="date-pick dp-applied"/></td>
								</tr>
							</table>
						</td>
						<td>
							<table width="100%">
								<col width="3%"/>
								<col width="2%"/>
								<col width="8%"/>
								<tr>
									<td>To Date<span class="reqd-info">*</span></td>
									<td align="center">:</td>
									<td><s:textfield name="toDate" id="toDate" cssClass="date-pick dp-applied"/>
									<input type="submit" value="Go"/>
									</td>
								</tr>
							</table>
						</td>
						
					</tr>
				
				
					
				</table>
				
				
				<table id="results" cellpadding="0" cellspacing="0" class="my-table"  style="width:100%;">
					<tr>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Date</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Invoice</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Debit</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Discount %</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Credit</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Balance</th>
					</tr>
					
					<s:iterator value="purchaseaccList">
						<tr>
							<td><s:property value="date"/></td>
							<s:if test="balance>0">
								<td><a href="recordPurchaseInvoice?invoiceid=<s:property value="id"/>"><s:property value="id"/> (Record Payment)</a></td>
							</s:if>
							<s:else>
								<td><s:property value="id"/></td>
							</s:else>
							
							<td>Rs.<s:property value="dabit"/></td>
							<td><s:property value="vat"/></td>
							<td>Rs<s:property value="credit"/></td>
							<td>Rs<s:property value="balance"/></td>
							
						</tr>
						
						
					
					</s:iterator>
					
					<tr>
							<td style="font-weight: bold;">Total</td>
							<td></td>
							<td style="font-weight: bold;">Rs.<s:property value="debitx"/></td>
							<td></td>
					        <td style="font-weight: bold;">Rs<s:property value="creditx"/></td>
							<td style="font-weight: bold;">Rs<s:property value="balancex"/></td>
					    </tr>
					
				</table>
				
				
				
			</s:form>
		
		
		
			
				
				
				
				
       			
       			

		
		
	</div>
</div>