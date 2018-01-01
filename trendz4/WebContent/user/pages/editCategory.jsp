<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="user/js/editcategory.js"></script>
<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Edit Category</h2>
	<span id="mandatory_message" class="error">Note:Fields marked with asterisk(*) are required.</span> 
	<div id="login" class="block_div">
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		<s:form action="updateCategory" id="category_form" theme="simple">
			<div class="form_elements">				
				<table width="50%" style="border: 1px solid #e69623; height: auto; padding: 1em; ">
					<col width="10%"/>
					<col width="2%"/>
					<col width="30%"/>
					<tr>
						<td>Select Sale<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td><s:select  list="#{'0':'Select Gender','1':'Gents','2':'Ladies','3':'Accessories'}" theme="simple" name="gender" id="gender" /></td>
						
					</tr>
					<tr>
						<td>CateGory Name<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
							<s:textfield id="categoryName" name="categoryName" title="Enter category name" />
						</td>
					</tr>
					<s:hidden name = "id" id = "id" ></s:hidden>
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