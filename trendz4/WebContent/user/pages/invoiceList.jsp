<%@taglib uri="/struts-tags" prefix="s" %>

<%@page import="com.inventorymanagement.main.web.common.helper.LoginInfo"%>
<%@page import="com.inventorymanagement.main.web.common.helper.LoginHelper"%>
<%@page import="com.inventorymanagement.main.common.constants.Constants"%>
<script type="text/javascript" src="common/js/pagination.js"></script>

<script type="text/javascript" src="user/js/saleInvoice.js"></script>



	<link href="common/css/datePicker1.css" rel="stylesheet"/>
	<script src="common/js/date.js" type="text/javascript"></script>
	<script src="common/js/jquery/jquery.datePicker.js" type="text/javascript"></script>
	<script src="common/js/CalenderPicture.js" type="text/javascript"></script>

<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Sale Invoice List</h2>
	
	<div id="login" class="block_div">
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		
			<s:form action="InvoiceReport" theme="simple">
			<s:hidden name="selectedbillType" id="selectedbillType"/>
				<!--<div>Search: <s:textfield theme="simple" name="searchText" placeholder="Search by invoice number" />
				<input type="submit" value="Go"/>
				</div>
				
				-->
				
					<div>BILL TYPE :
					
						<SELECT name="billType" id="billType">
							<option value="0">Select BillType</option>
							<option value="1">Primary Bill</option>
							<option value="2">Secondary Bill</option>
							<option value="3">Both</option>
						</SELECT>
					</div>
				
					
					<div>
						<b>( Total Sale : <s:text name="%{invoiceList.size}"/>
						>> Total Quantity : <s:text name="%{totalQuantity}"/>
						>> Total Amount : Rs. <s:text name="%{totalAmount}"/> )
						</b>
					</div>
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
				
				
				<s:if test="invoiceList.size() > 0">
				<s:iterator value="invoiceList" status="rowstatus">
					<%int count = 1; %>
					<table width="100%" align="center" style="border: none; height: auto; padding: 1em; font-size:14px; font-family: Arial,Helvetica,sans-serif; table-layout: fixed;">
						<col width="50%"/>
						<col width="50%"/>
						<tr>
							<td style="padding-left: 100px;">INVOICE NO : <s:property value="invoiceNumber"/></td>
							<td align="right" style="padding-right: 100px;">Date : <s:property value="lastUpdated"/></td>
						</tr>
						<tr>
							<td style="padding-left: 100px;">CUSTOMER NAME : <s:property value="customerName"/></td>
							<td align="right" style="padding-right: 100px;">MOBLE NO. : <s:property value="mobileNumber"/></td>
						</tr>
						<tr>
							<td colspan="2" style="padding-left: 100px; padding-right: 100px;"><hr/></td>
						</tr>
						<tr>
							<td colspan="2" style="padding-left: 100px; padding-right: 100px;" >
								<table width="100%" style="border: none; height: auto; padding: 1em; font-size: 12px; table-layout: fixed;">
									<col width="5%"/>
									<col width="30%"/>
									<col width="25%"/>
									<col width="10%"/>
									<col width="10%"/>
									<col width="10%"/>
									
									<tr>
											<th>SN</th>
											<th>PRODUCT NAME</th>
											<th>Size</th>
											<th>M.R.P</th>
											<th>GST%</th>
											<th>Quantity</th>
											<th>AMOUNT</th>
									
									</tr>
									<tr>
										<td colspan="7"><hr/></td>
									</tr>
									
									<s:iterator value="purchaseList" status="status">
										<tr>
											<td align="center"><%=count %></td>
											<td align="center"><s:property value="productName"/></td>
											<td align="center"><s:property value="size"/></td>
											<td align="center">Rs.<s:property value="mrp"/></td>
											<td align="center"><s:property value="totalgst"/></td>
											<td align="center"><s:property value="quantity"/></td>
											<td align="center">Rs.<s:property value="total"/></td>
											
										</tr>
										<%count++; %>
									</s:iterator>
									<tr>
										<td colspan="7"><hr/></td>
									</tr>
									<tr>
										<td colspan="6">
											<table width="100%">
												<col width="30%">
												<col width="30%">
												<col width="30%">
												
												<tr>
													<td><b>Prepared By : <s:property value="userid"/> </b></td>
													<s:if test="vat !=0">
														<td><font color="red">( Discount <s:property value="vat"/> % )</font></td>
													</s:if>
													<s:elseif test="rupee !=0">
														<td><font color="red">(Discount <s:property value="rupee"/> Rs. )</font></td>
													</s:elseif>
													<s:else>
														<td><font color="red">No Discount</font></td>
													</s:else>
													
													<td style="padding-left: 30px;float: right;">
														 <span>+CGST AMT : Rs.<s:property value="totalcgst"/></span> <br>
														 <span>+SGST AMT : Rs.<s:property value="totalsgst"/></span> <br>
													     <span>Total : Rs.<s:property value="gtotal"/></span> <br>
														<span>Payment : Rs.<s:property value="payamount"/></span> <br>
														<s:if test="balance==0">
															<span style="font-weight:bold;">Balance : Rs.<s:property value="balance"/></span>
														</s:if>
														<s:else>
															<span style="color:red;font-weight:bold;">(Balance : Rs.<s:property value="balance"/>)</span>
														</s:else>
													</td>
												</tr>
												
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</s:iterator>
					
				
				</s:if>
				
			</s:form>
		
		
		
			
				
				
				
				
       			
       			

		
		
	</div>
</div>