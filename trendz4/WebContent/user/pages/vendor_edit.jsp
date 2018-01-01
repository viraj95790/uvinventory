<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript" src="user/js/registeration.js"></script>

<div class="main_layout_content">

	<div id="contact_details" class="block_div">
		<div>
			<b>Edit</b>
		</div>
		<span id="mandatory_message" class="error">Note:Fields marked
			with asterisk(*) are required.</span> <span class="error"><font
			color="red"><s:actionerror id="server_side_error" /></font></span>
		<s:form action="updateCustomer" id="registerfrm" theme="simple">

			<table width="100%"
				style="border: 1px solid #e69623; height: auto; padding: 1em;">
				
				<s:hidden name="usertype" id="usertype" value="1"></s:hidden>
				<s:hidden name="id" id="id"></s:hidden>
				<tr>
					<td>Vendor ID<span class="reqd-info">*</span></td>
					<td align="center">:</td>
					<td><s:textfield id="vendorid" name="vendorid" theme="simple" /></td>
				</tr>
				
				<tr>
					<td>Vendor Name<span class="reqd-info">*</span></td>
					<td align="center">:</td>
					<td><s:textfield id="company" name="company" theme="simple" /></td>
				</tr>
				
				<tr>
					<td>Contact Name<span class="reqd-info">*</span></td>
					<td align="center">:</td>
					<td><s:textfield id="name" name="name" theme="simple" /></td>
				</tr>
				
				<tr>
					<td>Address<span class="reqd-info">*</span></td>
					<td align="center">:</td>
					<td><s:textfield id="address1" name="address1" theme="simple" /></td>
				</tr>
				
				<tr>
					<td>Contact No.<span class="reqd-info">*</span></td>
					<td align="center">:</td>
					<td><s:textfield id="mob" name="mob" theme="simple" /></td>
				</tr>
				
				<tr>
					<td>Fax<span class="reqd-info">*</span></td>
					<td align="center">:</td>
					<td><s:textfield id="fax" name="fax" theme="simple" /></td>
				</tr>
				
				<tr>
					<td>Email<span class="reqd-info">*</span></td>
					<td align="center">:</td>
					<td><s:textfield id="email" name="email" theme="simple" /></td>
				</tr>
				
				<tr>
					<td>Web Page<span class="reqd-info">*</span></td>
					<td align="center">:</td>
					<td><s:textfield id="webpage" name="webpage" theme="simple" /></td>
				</tr>
				
				<tr>
					<td>
						<table align="center">
							<tr>
								<td><s:submit theme="simple" /></td>
								<td><s:reset theme="simple" /></td>
							</tr>
						</table>
					</td>
				</tr>

			</table>




		</s:form>
	</div>



</div>
