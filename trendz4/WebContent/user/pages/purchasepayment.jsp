<%@taglib uri="/struts-tags" prefix="s" %>

<script type="text/javascript" src="common/js/pagination.js"></script>

<script type="text/javascript" src="user/js/setbranch.js"></script>
<script type="text/javascript" src="user/js/recordpayment.js"></script>

	<link href="common/css/datePicker1.css" rel="stylesheet"/>
	<script src="common/js/date.js" type="text/javascript"></script>
	<script src="common/js/jquery/jquery.datePicker.js" type="text/javascript"></script>
	<script src="common/js/CalenderPicture.js" type="text/javascript"></script>

<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Purchase Record Payment</h2>
	
	<div id="login" class="block_div">
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		
			<s:form action="paymentPurchaseInvoice" theme="simple" id="product_form">
				<s:hidden name="invoiceid" id="invoiceid"/>
			
				
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
											<th>Quantity</th>
											<th>AMOUNT</th>
											<th>GST</th>
									
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
											<td align="center"><s:property value="quantity"/></td>
											<td align="center">Rs.<s:property value="total"/></td>
											<td align="center">Rs.<s:property value="totalgst"/>%</td>
											
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
													<td><b></b></td>
													<td><font color="red">( Discount <s:property value="vat"/> % )</font></td>
													<td style="padding-left: 30px;float: right;">Total : Rs.<s:property value="somoftotal"/> <br>
													   <span>CGST : Rs.<s:property value="totalcgst"/></span> <br>
													   <span>SGST : Rs.<s:property value="totalsgst"/></span> <br>
														<span>Payment : Rs.<s:property value="payamount"/></span> <br>
														<s:if test="balance==0">
															<span style="font-weight:bold;">Balance : <s:property value="balance"/></span>
														</s:if>
														<s:else>
															<span style="color:red;font-weight:bold;">(Balance : Rs.<s:property value="balance"/>)</span>
														</s:else>
													</td>
													<td id="recbalance" style="display: none;"><s:property value="balance"/></td>
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
				
				
				<table width="100%">
							<tr>
								<td>Payment Mode<span class="reqd-info">*</span></td>
								<td align="center">:</td>
								<td>
									<select name="howpaid" id="howpaid" class="form-control">
										<option value="0">Select</option>
										<option value="BACS">BACS</option>
										<option value="Cheque">Cheque</option>
										<option value="C/Card">C/Card</option>
										<option value="Cash">Cash</option>
										<option value="D/Card">D/Card</option>
										<option value="D/D">D/D</option>
										<option value="Other">Other</option>
										<option value="S/O">S/O</option>
										
									
									
									</select> 
								</td>
							</tr>
							
							<tr>
								<td>Payment Given<span class="reqd-info">*</span></td>
								<td align="center">:</td>
								<td>
									<s:textfield onkeypress="return isNumberKey(event,this);"  cssStyle="width:65px;text-align:center;" id="paymentrecieved" name="paymentrecieved" maxlength="10" title="Enter Payment Recieved" value="%{balance}" />
								</td>
							</tr>
							<tr>
								<td>Payment Note<span class="reqd-info">*</span></td>
								<td align="center">:</td>
								<td>
									<s:textarea rows="3" cols="40"  id="paymentnote" name="paymentnote"  title="Enter Payment Recieved" />
								</td>
							</tr>
							<tr>
								<td><input type="button" value=" Next to Record Payment " id="billbtn" onclick="savepurchasedata()"></td>
							</tr>
							
						</table>
				
			</s:form>
		
		
		
			
				
				
				
				
       			
       			

		
		
	</div>
</div>