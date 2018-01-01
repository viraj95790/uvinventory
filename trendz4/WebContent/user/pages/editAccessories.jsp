<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="user/js/accessories.js"></script>
<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Edit Accessories</h2>
	<span id="mandatory_message" class="error">Note:Fields marked with asterisk(*) are required.</span> 
	<div id="login" class="block_div">
	
	<s:if test="hasActionMessages()"> 
			<div id="common_message" class="message">
				<s:actionmessage id="common_message_text" theme="simple"/>
			</div>
		</s:if> 
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		<s:form action="editsaveAccessories" id="category_form" theme="simple" enctype="multipart/form-data">
		<s:hidden name="selectedid" value="%{id}"/>
			<div class="form_elements">				
				<table width="50%" style="border: 1px solid #e69623; height: auto; padding: 1em; ">
					<col width="10%"/>
					<col width="2%"/>
					<col width="30%"/>
					
					<tr>
						<td>ProductName<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
							<s:textfield id="productName" name="productName" title="Enter productName" />
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
						<td>Price<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
							<s:textfield id="price" name="price" title="Enter Price" />
						</td>
					</tr>
					<s:if test="uploadedimage!=null">
						<tr>
							<td>Uploaded Image<span class="reqd-info">*</span></td>
							<td align="center">:</td>
							<td>
								<img style="width: 150px;height: 100px;" src="acimage/<s:property value="uploadedimage"/>"/>
							</td>
							<td id="msgtd"></td>
						</tr>
					</s:if>
					<tr>
						<td>Upload Image<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
							<s:file name="userAccImage"/>
						</td>
						<td id="msgtd"></td>
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