<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="user/js/adminsubcategory.js"></script>
<script type="text/javascript" src="user/js/gender.js"></script>
<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Edit Subcategory</h2>
	<span id="mandatory_message" class="error">Note:Fields marked with asterisk(*) are required.</span> 
	<div id="login" class="block_div">
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		<s:form action="updateAdminSubcategory" id="product_form" theme="simple">
						
				<table width="100%" style="border: 1px solid #e69623; height: auto; padding: 1em; ">
					<col width="10%"/>
					<col width="2%"/>
					<col width="30%"/>
					<tr>
						<td>Select Gender<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td><s:select onchange="setGenderAjax(this.value)"  list="#{'0':'Select Gender','1':'Gents','2':'Ladies','3':'Accessories'}" theme="simple" name="gender" id="gender" /></td>
					</tr>
					<tr>
						<td>Category<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td id="gendercategory">
							<s:select name="categoryID" list="categoryList" listKey="id" listValue="categoryName" headerKey="0" headerValue="Select Category" label="Select Category" onchange="setCategoryID(this.value);" />
						
							<!--<select name="category">
								<option value="0">Select Category</option>
							</select>
						--></td>
					</tr>
					<tr>
					<tr>
						<td>Product Name<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
								<s:textfield maxlength="15" placeholder="Valid only 15 charecter"  id="productName" name="productName" title="Enter productName" />
						</td>
					</tr>
					<tr>
						<td>Price<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
								<s:textfield id="price" name="price" title="Enter price" />
						</td>
						
					</tr>
					<tr>
						<td>Profit Margin<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
								<s:textfield id="profitmargin" name="profitmargin" title="Enter profitmargin%" />
						</td>
						
					</tr>
					<tr>
						<td>CGST<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
								<s:textfield id="cgst" name="cgst" title="Enter CGST %" />
						</td>
						
					</tr>
					<tr>
						<td>SGST<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
								<s:textfield id="sgst" name="sgst" title="Enter SGST %" />
						</td>
						
					</tr>
					
					<s:hidden name = "id" id = "id" ></s:hidden>
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