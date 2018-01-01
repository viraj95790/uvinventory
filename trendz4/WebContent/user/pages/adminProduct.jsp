<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="user/js/adminsubcategory.js"></script>
<script type="text/javascript" src="user/js/gender.js"></script>
<link rel="stylesheet" href="chosen/chosen.css">

<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Add Product</h2>
	<span id="mandatory_message" class="error">Note:Fields marked with asterisk(*) are required.</span> 
	<div id="login" class="block_div">
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		<s:form action="saveSubcategoryProduct" id="product_form" theme="simple">
			<div class="form_elements">				
				<table id="productlisttable" width="100%" style="border: 1px solid #e69623; height: auto; padding: 1em; ">
					<col width="13%"/>
					<col width="13%"/>
					<col width="13%"/>
					<col width="13%"/>
					<col width="12%"/>
					<col width="12%"/>
					<col width="12%"/>
					<col width="10%"/>
					
					
					<tr>
						<td>Select Gender<span class="reqd-info">*</span></td>
						<td>Category<span class="reqd-info">*</span></td>
						<td>Product Name<span class="reqd-info">*</span></td>
						<td>PurchasePrice<span class="reqd-info">*</span></td>
						<td>Profit Margin<span class="reqd-info">*</span></td>
						<td>SalePrice<span class="reqd-info">*</span></td>
						<td>CGST<span class="reqd-info">*</span></td>
						<td>SGST<span class="reqd-info">*</span></td>
					</tr>
					<tbody id="">
					<tr id="trid1">
						<td><s:select onchange="setGenderProductAjax1(this.value,0)"  list="#{'0':'Select Gender','1':'Gents','2':'Ladies','3':'Accessories'}" theme="simple" name="rowProductList[0].gender" id="gender" /></td>
						<td id="gendercategory">
							<select name="category">
								<option value="0">Select Category</option>
							</select>
						</td>
						<td>
								<s:textfield size="15" maxlength="15" placeholder="Valid only 15 charecter" id="productName" name="rowProductList[0].productName" title="Enter productName"  onchange="productAjax(this.value)"/>
						</td>
						<td>
								<s:textfield size="15" placeholder="Enter Price" id="price0" name="rowProductList[0].price" title="Enter price" />
						</td>
						<td>
								<s:textfield size="15" placeholder="Profit %" id="profitmargin0" name="rowProductList[0].profitmargin" title="Enter profit" onchange="calsaleamount(this.value,0)"  />
						</td>
						<td>
								<s:textfield size="15" placeholder="Sale Price" id="saleprice0" name="rowProductList[0].saleprice" title="" onchange="calprofit(this.value,0)"  />
						</td>
						<td>
								<s:textfield size="15"  placeholder="CGST %" id="cgst" name="rowProductList[0].cgst" title="Enter cgst" />
						</td>
						<td>
								<s:textfield size="15" placeholder="SGST %" id="sgst" name="rowProductList[0].sgst" title="Enter sgst" />
						</td>
						
						 <td><span id="chkgst0"><s:checkbox  cssClass="case" id="iegst0"  name="rowProductList[0].iegst" /></span></td> 
						
						<td><span id="btn0"><input type="button" value="Add" onclick="addRow(0)"></span></td>
						
						
					</tr>
					
					
					
					</tbody>
					
					<%-- <tr>
						<td>Select Gender<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td><s:select onchange="setGenderAjax(this.value)"  list="#{'0':'Select Gender','1':'Gents','2':'Ladies','3':'Accessories'}" theme="simple" name="gender" id="gender" /></td>
					</tr>
					<tr>
						<td>Category<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td id="gendercategory">
							<!--<s:select name="categoryID" list="categoryList" listKey="id" listValue="categoryName" headerKey="0" headerValue="Select Category" label="Select Category" onchange="setCategoryID(this.value);" />
						-->
							<select name="category">
								<option value="0">Select Category</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>Product Name<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
								<s:textfield  maxlength="15" placeholder="Valid only 15 charecter" id="productName" name="productName" title="Enter productName"  onchange="productAjax(this.value)"/>
						</td>
						<td id="msgtd"></td>
					</tr>
					
					<tr>
					<tr>
						<td>Price<span class="reqd-info">*</span></td>
						<td align="center">:</td>
						<td>
								<s:textfield id="price" name="price" title="Enter price" />
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
		 		</tr> --%>
				</table>
				
				
				<table>
					<tr id="trid2">
						<td><s:submit/></td>
					</tr>
				</table>
				
				
				
				
       			
       			
		
		</s:form>
		
		
	</div>
</div>