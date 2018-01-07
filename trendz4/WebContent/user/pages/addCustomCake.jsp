<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="user/js/customcake.js"></script>

	<link href="common/css/datePicker1.css" rel="stylesheet"/>
	<script src="common/js/date.js" type="text/javascript"></script>
	<script src="common/js/jquery/jquery.datePicker.js" type="text/javascript"></script>
	<script src="common/js/CalenderPicture.js" type="text/javascript"></script>

<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Add Custom Cake</h2>
	<span id="mandatory_message" class="error">Note:Fields marked with asterisk(*) are required.</span> 
	<div id="login" class="block_div">
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		<s:form action="saveCustomCake" id="customcake_form" theme="simple">
						
				<table width="100%" style="border: 1px solid #e69623; height: auto; padding: 1em; ">
					<col width="10%"/>
					<col width="2%"/>
					<col width="30%"/>
					<tr>
						<td>Weight<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
						
						<s:textfield  name="weight" id="weight"/>
						</td>
					</tr>
					<tr>
						<td>Flavour<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
								<s:textfield id="flavour" name="flavour" title="Enter flavour" />
						</td>
					</tr>
					<tr>
						<td>Model Design Notes<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
								<s:textarea cols="40" rows="3" id="notes" name="notes" title="Enter notes" />
						</td>
						
					</tr>
					<tr>
						<td>MSG on Cake<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
								<s:textarea cols="40" rows="3" id="msgOnCake" name="msgOnCake" title="Enter msgOnCake" />
						</td>
						
					</tr>
					<tr>
						<td>Amount<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td><s:textfield id="amount" name="amount"/></td>
					</tr>
					<tr>
						<td>Delivery Date<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td><s:textfield name="deliveryDate" id="" cssClass="date-pick dp-applied"/></td>
					</tr>
					<tr>
						<td colspan="">Delivery Time<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
									<s:select id="timeID" name="time" list="timeList" headerKey="0" headerValue="Select Hour"  />
									<s:select id="minute" name="minute" list="minuteList" headerKey="0" headerValue="Select Minute"  />
									<s:select id="amorpmID" name="amorpm" list="amorpmList" headerKey="0" headerValue="Select"  />
						</td>
					
						
					</tr>
					
					
					<tr>
		 			<td>
		 				<table align="center">
		 					<tr>
		 						<td><s:submit /></td>
		 						<td><s:reset/></td>
		 					</tr>
		 				</table>
		 			</td>
		 		</tr>
				</table>
		</s:form>
		
		
	</div>
</div>