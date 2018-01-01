<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="user/js/color.js"></script>
<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Add Color</h2>
	<span id="mandatory_message" class="error">Note:Fields marked with asterisk(*) are required.</span> 
	<div id="login" class="block_div">
	
	<s:if test="hasActionMessages()"> 
			<div id="common_message" class="message">
				<s:actionmessage id="common_message_text" theme="simple"/>
			</div>
		</s:if> 
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		<s:form action="editsaveColor" id="category_form" theme="simple">
		<s:hidden name="id" value="%{id}"/>
			<div class="form_elements">				
				<table width="50%" style="border: 1px solid #e69623; height: auto; padding: 1em; ">
					<col width="10%"/>
					<col width="2%"/>
					<col width="30%"/>
					
					<tr>
						<td>Color Name<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
							<s:textfield id="colorName" name="colorName" title="Enter colorName name" />
						</td>
					</tr>
					<tr>
						<td>Description<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
							<s:textarea id="description" name="description" title="Enter description" />
						</td>
					</tr>
					<tr>
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
		
		
	</div>
</div>