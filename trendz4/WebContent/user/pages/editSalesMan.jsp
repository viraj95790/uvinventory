<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="user/js/branch.js"></script>
<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Edit SalesMan</h2>
	<span id="mandatory_message" class="error">Note:Fields marked with asterisk(*) are required.</span> 
	<div id="login" class="block_div">
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		<s:form action='editsaveSalesMan' method='POST' id="branchfrm" theme="simple">
			<s:hidden name="id" value="%{id}"/>
			<div class="form_elements">				
				<table width="100%" style="border: 1px solid #e69623; height: auto; padding: 1em; ">
					<col width="10%"/>
		 			<col width="2%"/>
		 			<col width="18%"/>
					<tr>
						<td>Employee ID<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td><s:textfield name ="userId" theme="simple" id= "userId"></s:textfield></td>
					</tr>

					<tr>
						<td>First Name<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td><s:textfield name ='firstName' theme="simple" id= "firstName"></s:textfield></td>
					</tr>


					<tr>
						<td>Last Name<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td><s:textfield name ='lastName' theme="simple" id= "lastName"></s:textfield></td>
					</tr>
					
					<tr>
						<td>Mobile No.<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td><s:textfield name ='mobileNo' theme="simple" id= "mobileNo"></s:textfield></td>
					</tr>
					
					<tr>
						<td>Address<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td><s:textarea name ='address' theme="simple" id= "address"></s:textarea></td>
					</tr>
					
					<tr>
						<td>Salary/Day<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td><s:textfield name ='salaryperday' theme="simple" id= "salaryperday"></s:textfield></td>
					</tr>
					
                   <tr>
						<td>Commission %<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td><s:textfield maxlength="2" onkeypress="return isNumberKey(event,this);" name ='commission' theme="simple" id= "commission"></s:textfield></td>
					</tr>
					
					
					<tr>
		 			<td>
		 				<table align="center">
		 					<tr>
		 						<td><s:submit theme="simple"/></td>
		 						<td><s:reset theme="simple"/></td>
		 					</tr>
		 				</table>
		 			</td>
		 		</tr>
				</table>
				
				
				
				
				
				
				
       			
       			
		
		</s:form>
		
		
	</div>
</div>