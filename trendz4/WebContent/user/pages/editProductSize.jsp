<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="user/js/editcategory.js"></script>
<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Edit Stock</h2>
	<span id="mandatory_message" class="error">Note:Fields marked with asterisk(*) are required.</span> 
	<div id="login" class="block_div">
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		<s:form action="savesizeProduct" id="category_form" theme="simple">
			<div class="form_elements">				
				<table width="50%" style="border: 1px solid #e69623; height: auto; padding: 1em; ">
					<col width="10%"/>
					<col width="2%"/>
					<col width="30%"/>
					
					<tr>
						<td>Size<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
							<s:textfield id="sizeValue" name="sizeValue" title="Enter Size" />
						</td>
					</tr>
					<s:hidden name = "id" value="%{id}" ></s:hidden>
					<tr>
						<td>Color<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
							<s:textfield id="colorName" name="colorName" title="Enter Color" />
						</td>
					</tr>
					<tr>
		 			<td>
		 				<table align="center">
		 					<tr>
		 						<td><s:submit/></td>
		 						<td><s:reset/></td>
		 						<td><s:a href="sizeProduct?selectedid=%{selectedID}"><input type="button" value=" Back "></s:a></td>
		 					</tr>
		 				</table>
		 			</td>
		 		</tr>
				</table>
				
				
				
				
				
				
				
       			
       			
		
		</s:form>
		
		
	</div>
</div>