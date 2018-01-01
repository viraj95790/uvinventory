<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="user/js/editbranch.js"></script>
<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Category</h2>
	<span id="mandatory_message" class="error">Note:Fields marked with asterisk(*) are required.</span> 
	<div id="login" class="block_div">
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		<form action='updateBranch' method='POST' id="editbranchfrm">
			<div class="form_elements">				
				<table width="50%" style="border: 1px solid #e69623; height: auto; padding: 1em; ">
					<col width="10%"/>
					<col width="2%"/>
					<col width="30%"/>
					<tr>
<td>User ID:<span class="reqd-info">*</span></td>
	<td><s:textfield name ='userId' theme="simple" id= "userId" readonly="true"></s:textfield></td>
</tr>



<tr>
	<td>Branch Name:<span class="reqd-info">*</span></td>
	<td><s:textfield name='branchName' theme="simple" id= "branchName"></s:textfield></td>
</tr>
					<tr>
		 			<td>
		 				<table align="center">
		 					<tr>
		 						<td><s:submit theme="simple" value="Update"/></td>
		 						<td><s:reset theme="simple"/></td>
		 					</tr>
		 				</table>
		 			</td>
		 		</tr>
				</table>
				
				
				
				
				
				
				
       			
       			
		
		</form>
		
		
	</div>
</div>