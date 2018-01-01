<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="user/js/branch.js"></script>

		<table width="100%">
			<tr>
				<td>
					<!-- <img src="logo/logo.png" style="width: 250px;"/> -->
				</td>
				<td>
					<table width="70%" align="center" style="border: none; height: auto; padding: 1em; font-size:14px; font-family: Arial,Helvetica,sans-serif; table-layout: fixed; font-weight: bold;">
						<tr>
							<td align="" style="font-size: 15px;padding-left: 130px;"><u>RETAIL INVOICE</u></td>
						</tr>
						<tr></tr>
						<tr>
							<td style="padding-left: 50px;">
								<table width="100%">
									<tr>
										<td style="padding-left: 75px;"><s:property value="companyName"/></td>
									</tr>
									<tr>
										<td style="padding-left: 65px;"><s:property value="address"/></td>
									</tr>
									<%-- <tr>
										<td style="padding-left: 40px;"> Mob : <s:property value="mobileNo"/></td>
									</tr> --%>
									<tr>
										<td>M : <s:property value="shopmob"/>,<s:property value="state"/>,<s:property value="city"/>-<s:property value="pincode"/></td>
									</tr>
								</table>
							
							
								
							</td>
						</tr>
						
					</table>
				</td>
			</tr>
		
		</table>
			
			<table width="100%" align="center" style="border: none; height: auto; padding: 1em; font-size:14px; font-family: Arial,Helvetica,sans-serif; table-layout: fixed;">
				<col width="50%"/>
				<col width="50%"/>
				<tr>
					<td style="padding-left: 100px;">INVOICE NO : <s:property value="invoiceNo"/></td>
					<td align="right" style="padding-right: 100px;">Date : <s:property value="currentDate"/></td>
				</tr>
				<tr>
					<td style="padding-left: 100px;">CUSTOMER NAME : <s:property value="customerName"/></td>
					<td align="right" style="padding-right: 100px;">MOBLE NO. : <s:property value="mobileNo"/></td>
				</tr>
				<tr>
					<td colspan="2" style="padding-left: 100px; padding-right: 100px;"><hr/>
					</td>
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
								<td colspan="7"><hr/>
					</td>
							</tr>
							<%int count = 1; %>
							<s:iterator value="saleList" status="status">
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
								<td colspan="7"><hr/>
					</td>
							</tr>
							<tr>
								<td colspan="6">
									<table width="100%">
										<col width="30%">
										<col width="30%">
										<col width="30%">
										
										<tr>
											<td><b></b></td>
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
														<span>Payment : Rs.<s:property value="payAmount"/></span> <br>
														<s:if test="balance==0">
															<span style="font-weight:bold;">Balance : Rs.<s:property value="balance"/></span>
														</s:if>
														<s:else>
															<span style="color:red;font-weight:bold;">(Balance : <s:property value="balance"/>)</span>
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
			
			<table width="100%" style="padding-left: 100px; font-size: 16px; padding-right: 100px;">
				<tr>
					<td>Terms & Conditions :</td>
				</tr>
				<tr>
					<td>1. All Goods Are Inclusive Of Tax.</td>
				</tr>
				<tr>
					<td>2. Goods Once Sold Will Not Be Taken Back Or Changed.</td>
				</tr>
				<tr>
					<td>3. Exchange Time 12PM - 2PM (Within 5 Days) </td>
				</tr>
				<tr>
					<td>4. White Product -> No Exchange</td>
				</tr>
				<tr><td></td></tr>
				<tr><td></td></tr>
				
				<tr>
					<td style="font-size: 14px;">Customer Sign.</td>
					<td align="right" style="font-size: 14px;">Authority Signotry</td>
				</tr>
			
			
			</table>
			
		
