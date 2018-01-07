<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript" src="user/js/registeration.js"></script>

<div class="main_layout_content">

  <link href="common/css/datePicker1.css" rel="stylesheet"/>
	<script src="common/js/date.js" type="text/javascript"></script>
	<script src="common/js/jquery/jquery.datePicker.js" type="text/javascript"></script>
	<script src="common/js/CalenderPicture.js" type="text/javascript"></script>

	<div id="contact_details" class="block_div">
		<div>
			<b>Registration</b>
		</div>
		<span id="mandatory_message" class="error">Note:Fields marked
			with asterisk(*) are required.</span> <span class="error"><font
			color="red"><s:actionerror id="server_side_error" /></font></span>
		<s:form action="registerdataCustomer" id="registerfrm" theme="simple">

			<table width="100%"
				style="border: 1px solid #e69623; height: auto; padding: 1em;">
				<s:hidden name="usertype" id="usertype" value="0"></s:hidden>

				<tr>
					<td>CustomerType<span class="reqd-info"></span></td>
					<td align="center">:</td>
					<td><s:select id="customer_type" name="customer_type" list="customertypeList" headerKey="0" headerValue="Select CustomerType" /></td>
				</tr>
				
				<tr>
					<td>Title<span class="reqd-info">*</span></td>
					<td align="center">:</td>
					<td><s:select id="title" name="title" list="titleList" headerKey="0" headerValue="Select Title" /></td>
				</tr>

				<tr>
					<td>Name<span class="reqd-info">*</span></td>
					<td align="center">:</td>
					<td><s:textfield id="name" name="name" theme="simple" /></td>
				</tr>

				<tr>
					<td>MiddleName<span class="reqd-info"></span></td>
					<td align="center">:</td>
					<td><s:textfield id="middlename" name="middlename"
							theme="simple" /></td>
				</tr>

				<tr>
					<td>Surname<span class="reqd-info">*</span></td>
					<td align="center">:</td>
					<td><s:textfield id="Surname" name="Surname" theme="simple" /></td>
				</tr>

				<tr>
					<td>Company<span class="reqd-info"></span></td>
					<td align="center">:</td>
					<td><s:textfield id="company" name="company" theme="simple" /></td>
				</tr>

				<tr>
					<td>Address1<span class="reqd-info">*</span></td>
					<td align="center">:</td>
					<td><s:textfield id="address1" name="address1" theme="simple" /></td>
				</tr>

				<tr>
					<td>Address2<span class="reqd-info"></span></td>
					<td align="center">:</td>
					<td><s:textfield id="address2" name="address2" theme="simple" /></td>
				</tr>

				<tr>
					<td>City<span class="reqd-info">*</span></td>
					<td align="center">:</td>
					<td><s:select id="city" name="city" list="cityList"
							headerKey="0" headerValue="Select City" /></td>
				</tr>

				<tr>
					<td>State<span class="reqd-info">*</span></td>
					<td align="center">:</td>
					<td><s:select id="state" name="state" list="stateList"
							headerKey="0" headerValue="Select State" /></td>
				</tr>
				
				<tr>
					<td>Zip Code<span class="reqd-info">*</span></td>
					<td align="center">:</td>
					<td><s:textfield id="zipcode" name="zipcode" theme="simple" /></td>
				</tr>

				<tr>
					<td>Home Phone<span class="reqd-info">*</span></td>
					<td align="center">:</td>
					<td><s:textfield id="homephone" name="homephone"
							theme="simple" /></td>
				</tr>

				<tr>
					<td>Mobile no<span class="reqd-info">*</span></td>
					<td align="center">:</td>
					<td><s:textfield id="mob" name="mob" theme="simple" /></td>
				</tr>

				<tr>
					<td>Work Phone<span class="reqd-info">*</span></td>
					<td align="center">:</td>
					<td><s:textfield id="office" name="office"
							theme="simple" /></td>
				</tr>

				<tr>
					<td>Fax<span class="reqd-info"></span></td>
					<td align="center">:</td>
					<td><s:textfield id="fax" name="fax" theme="simple" /></td>
				</tr>

				<tr>
					<td>Email<span class="reqd-info">*</span></td>
					<td align="center">:</td>
					<td><s:textfield id="email" name="email" theme="simple" />
					</td>
				</tr>
				
				<tr>
					<td>WebPage<span class="reqd-info">*</span></td>
					<td align="center">:</td>
					<td><s:textfield id="webpage" name="webpage" theme="simple" />
					</td>
				</tr>

				<tr>
					<td>Birth Day<span class="reqd-info"></span></td>
					<td align="center">:</td>
					<td><s:textfield id="birthday" name="birthday" cssClass="date-pick dp-applied" theme="simple" />
					</td>
				</tr>

				<tr>
					<td>How Do You know Us?<span class="reqd-info">*</span></td>
					<td align="center">:</td>
					<td>
						<div style="height: 96px; overflow: scroll;">
							
							<s:checkbox id="passby" name="passby"></s:checkbox>Pass By
							<br> 
							<s:checkbox id="newspaper" name="newspaper"></s:checkbox>NewsPaper
							<br> 
							<s:checkbox id="internet" name="internet"></s:checkbox>Internet
							<br> 
							<s:checkbox id="flyer" name="flyer"></s:checkbox>Flyer
							<br> 
							<s:checkbox id="friends_reference" name="friends_reference"></s:checkbox>Friends Reference
							<br> 
							<s:checkbox id="posters" name="posters"></s:checkbox>Posters
							<br> 
							<s:checkbox id="yellow_page" name="yellow_page"></s:checkbox>Yellow Page
							<br> 
							<s:checkbox id="other" name="other"></s:checkbox>Other
							

						</div>
					</td>
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