<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="user/js/login.js"></script>
<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Login</h2>
	<span id="mandatory_message" class="error">Note:Fields marked with asterisk(*) are required.</span> 
	<div id="login" class="block_div">
		<h4>Login to your nagpur inventory management account</h4>
		<span class="error"><s:actionerror id="server_side_error"/></span>
		<s:form action="Login" id="login_form" theme="simple">
			<div class="form_elements">				
				<table width="100%" style="border: 1px solid #e69623; height: auto; padding: 1em; ">
					<col width="10%"/>
					<col width="2%"/>
					<col width="30%"/>
					<tr>
						<td>UserID<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
							<s:textfield id="userId" name="userId" title="Enter user id" />
						</td>
					</tr>
					<tr>
						<td>Password<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
							<s:password id="password" name="password" title="Enter Password" />
						</td>
					</tr>
					<!--<tr>
						<td>Enter Code</td>
						<td align="center">:</td>
						<td>
							<s:password id="code" name="code" title="Enter code" />
						</td>
					</tr>
					--><tr>
		 			<td>
		 				<table align="center">
		 					<tr>
		 						<td><s:submit/></td>
		 						<td><s:reset/></td>
		 					</tr>
		 				</table>
		 			</td>
		 		</tr>
				</table>
				
				
				
				
				
				
				
       			
       			
		
		</s:form>
		
		<!--<div id="login_other">
       		<h5><s:a href="inputForgotUserIdPassword">Forgot Password/User ID?</s:a></h5>
       		<h5>Not a member yet: <s:a href="inputRegistration">Join UnlockJobs.com</s:a></h5>
       	</div>
	--></div>
</div>