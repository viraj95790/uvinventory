<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="user/js/product.js"></script>
<script type="text/javascript" src="user/js/gender.js"></script>

<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Add Product</h2>
	<span id="mandatory_message" class="error">Note:Fields marked with asterisk(*) are required.</span> 
	<div id="login" class="block_div">
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		<s:form action="saveProduct" id="product_form" theme="simple" method="post" enctype="multipart/form-data">
		
		
		
			<div class="form_elements">				
				<table width="100%" style="border: 1px solid #e69623; height: auto; padding: 1em; ">
					<col width="10%"/>
					<col width="2%"/>
					<col width="30%"/>
					
					<tr>
						<td>Select Sale<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td><s:select onchange="setGenderAjax(this.value)"  list="#{'0':'Select Sale','1':'Retail','2':'Whole Sale'}" theme="simple" name="gender" id="gender" /></td>
					</tr>
					<tr>
						<td>Category<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td id="gendercategory">
						
							<select name="category">
								<option value="0">Select Category</option>
							</select>
						</td>
					</tr><!--
					
					<tr>
						<td>Category<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
							<s:select name="categoryID" list="categoryList" listKey="id" listValue="categoryName" headerKey="0" headerValue="Select Category" label="Select Category" onchange="setSubCategory(this.value)" />
						</td>
					</tr>
					--><tr>
						<td>Product Name<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td id="productajax">
							<!--<s:select id="subCategoryID" name="productName" list="subcategoryList" listKey="id" listValue="productName" headerKey="0" headerValue="Select Product" label="Select Product" onchange="setPrice(this.value)"/>
						-->
							<select name="productName">
								<option value="0">Select Product</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>Description</td>
						<td align="center">:</td>
						<td>
							<s:textarea id="description" name="description" title="Enter description" />
						</td>
					</tr>
					<tr>
						<td>Model Number<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
							<s:textfield id="modelnumber" name="modelNumber" title="Enter Model Number"/>
						</td>
					</tr>
					
					<tr>
						<td>Article Number<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
							<s:textfield id="articlenumber" name="articleNumber" title="Enter Article Number"/>
						</td>
					</tr>
					<tr>
						<td>Color<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
							<s:textfield id="color" name="color" title="Enter color"/>
						</td>
					</tr>
					<tr>
						<td>Size</td>
						<td align="center">:</td>
						<td>
							<s:textarea id="size" name="size"  readonly="true"/>
						</td>
					</tr>
					
					<tr>
						<td>Quantity<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
							<s:textfield id="quantity" name="quantity" title="Enter Quantity" readonly="true"/>
						</td>
					</tr>
					
					<tr>
						<td>Price<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td id="priceajax">
							<s:textfield id="price" name="price" title="Enter Price" readonly="true" />
						</td>
						<td id="msgtd"></td>
					</tr>
					<tr>
						<td>Upload Image<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
							<s:file name="userImage"/>
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