<%@page import="com.inventorymanagement.main.eu.entity.Product"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="user/js/barcode.js"></script>
<script type="text/javascript" src="user/js/gender.js"></script>

	<link href="common/css/datePicker1.css" rel="stylesheet"/>
	<script src="common/js/date.js" type="text/javascript"></script>
	<script src="common/js/jquery/jquery.datePicker.js" type="text/javascript"></script>
	<script src="common/js/CalenderPicture.js" type="text/javascript"></script>
	
	
<script>
var gbarcodegender = '';
var gbarcodecategory = '';
var gbatcodeproduct = '';
var glastupdate = '';
$(document).ready(function(){
	<%if(session.getAttribute("barcodesessionproduct")!=null){
		Product product = (Product)session.getAttribute("barcodesessionproduct");
		
	%>
	gbarcodegender = <%=product.getGender()%>
	gbarcodecategory = <%=product.getCategoryID()%>
	gbatcodeproduct = <%=product.getProductName()%>
	
	
	 document.getElementById('fromDate').value = '<%=product.getBarcodedate()%>';
	
	document.getElementById('gender').value = gbarcodegender;
	setBarocdeGenderAjax(gbarcodegender);
	
	<%}%>
	
	
});

</script>


<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Generate Barcode</h2>
	<span id="mandatory_message" class="error">Note:Fields marked with asterisk(*) are required.</span> 
	<div id="login" class="block_div">
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		<s:form action="Barcode" id="product_form" theme="simple">
		
		<table width="100%">
			<col width="5%"/>
			<col width="2%"/>
			<col width="8%"/>
			
			<col width="5%"/>
			<col width="2%"/>
			<col width="8%"/>
			<tr>
				<td>From Date<span class="reqd-info">*</span></td>
				<td align="center">:</td>
				<td><s:textfield name="fromDate" id="fromDate" cssClass="date-pick dp-applied"/></td>
				<td>To Date<span class="reqd-info">*</span></td>
									
				<td align="center">:</td>
				<td><s:textfield name="toDate" id="toDate" cssClass="date-pick dp-applied"/>
				<input type="button" value="Go" onclick="setdateforbarcode()">
			</tr>
		</table>
		
			<table width="100%" style="font-size: 12px;">
				<tr>
					<td width="40%">
					
						<table width="100%" style="border: 1px solid #e69623; height: auto; padding: 1em; " id="dropdowntable">
							<col width="10%"/>
							<col width="2%"/>
							<col width="20%"/>
							<tr>
								<td>Select Gender<span class="reqd-info">*</span></td>
								<td align="center">:</td>
								<td><s:select onchange="setGenderAjax(this.value)"  list="#{'0':'Select Gender','1':'Gents','2':'Ladies','3':'Accessories'}" theme="simple" name="gender" id="gender" /></td>
							</tr>
							<tr>
								<td>Category<span class="reqd-info">*</span></td>
								<td align="center">:</td>
								<td id="gendercategory">
									<!--<s:select name="categoryID" id="categoryID" list="categoryList" listKey="id" listValue="categoryName" headerKey="0" headerValue="Select Category" label="Select Category" onchange="setSubCategory(this.value)" />
								-->
									<select name="category">
										<option value="0">Select Category</option>
									</select>
								</td>
						
								
							</tr>
							<tr>
								<td>Product Name<span class="reqd-info">*</span></td>
								<td align="center">:</td>
								<td id="productajax">
									<select name="productName">
										<option value="0">Select SubCategory</option>
									</select>
								</td>
							</tr>
							
						</table>
						<table width="100%" id="purchasetable" style="border: 1px solid #e69623; height: auto; padding: 1em; ">
							
										<col width="20%"/>
										<col width="3%"/>
										<col width="20%"/>
										<tr>
											<td>Stock<span class="reqd-info">*</span></td>
											<td align="center">:</td>
											<td id="stockajax">
												<s:textarea id="stock" name="stock"  readonly="true" />
											</td>
										</tr>
										
										<tr>
											<td>Stock Color<span class="reqd-info">*</span></td>
											<td align="center">:</td>
											<td id="stockcolorajax">
												<s:textarea id="stockcolor" name="stockcolor"  readonly="true" />
											</td>
										</tr>
										
										<tr>
											<td>Stock Quantity<span class="reqd-info">*</span></td>
											<td align="center">:</td>
											<td>
												<s:textfield id="stockQuantity" name="stockQuantity"  readonly="true" size="5"/>
											</td>
										</tr>
										
										<tr>
											<td>Model No.<span class="reqd-info">*</span></td>
											<td align="center">:</td>
											<td>
												<s:textfield id="modelnumber" name="modelNumber" title="Enter Model Number"/>
											</td>
										</tr>
										
										<tr>
											<td>Article No.<span class="reqd-info">*</span></td>
											<td align="center">:</td>
											<td>
												<s:textfield id="articlenumber" name="articleNumber" title="Enter Article Number"/>
											</td>
										</tr>
										<tr>
											<td>Add Color<span class="reqd-info">*</span></td>
											<td align="center">:</td>
											<td>
												<s:select theme="simple" id="adcolor" name="colorId" list="colorList" listKey="colorId" listValue="colorName" headerKey="0" headerValue="Select Color"  onchange="setBranch(this.value)"/>
												<input type="button" value="Add" id="adcolorbtn" onclick="addColor()"/>
												<input type="button" value="Remove" id="rmovecolorbtn" onclick="removeColor()"/>
											</td>
											
											
										</tr>
										<tr>
											<td>Color<span class="reqd-info">*</span></td>
											<td align="center">:</td>
											<td>
												<s:textarea id="color" name="color" title="Enter color" readonly="true"/>
											</td>
										</tr>
										<tr>
											<td>Price<span class="reqd-info">*</span></td>
											<td align="center">:</td>
											<td id="priceajax">
												<input type="text" id="price" name="price" title="Enter Price"  size="5" style="text-align: center;"/>
											</td>
										</tr>
										<tr>
											<td>Add Size<span class="reqd-info">*</span></td>
											<td align="center">:</td>
											<td>
												<select name="adsize" id="adsize">
													<option value="0">Select</option>
													<option>05</option>
													<option>06</option>
													<option>07</option>
													<option>08</option>
													<option>09</option>
													<option>10</option>
													<option>11</option>
													<option>12</option>
												</select>
												
												<input type="button" id="adsizebtn" value="Add" onclick="addSize();"/>
												<input type="button" id="rmovesizebtn" value="Remove" onclick="RemoveSize();"/>
											</td>
										</tr>
										<tr>
											<td>View Size<span class="reqd-info">*</span></td>
											<td align="center">:</td>
											<td>
												<s:textarea id="viewsize" name="viewsize"  readonly="true"/>
												
											</td>
										</tr>
										
										<tr>
											<td>Quantity<span class="reqd-info">*</span></td>
											<td align="center">:</td>
											<td>
												<input type="text" id="quantity" name="quantity"  readonly="readonly" size="5" style="text-align: center;"/>
											</td>
										</tr>
										
										<tr>
											<td>Image<span class="reqd-info">*</span></td>
											<td align="center">:</td>
											<td>
												<!--<img src="/inventorymanagement/image/<s:property value="uploadedimage"/>"/>
											--></td>
											
										</tr>
										
										
								
							
						</table>
						<table width="100%">
							<tr>
								<td><input type="submit" value=" Next to Generate Barcode " id="billbtn"></td>
								
							</tr>
						</table>
					</td>
					<td align="center" width="10%">
						<input type="button" id="addbtn" value="Add"  onclick="save();"/>
						<input type="button" id="rmovbtn" value="Remove" onclick="deletesale();"/>
						
					</td>
					<td width="50%" valign="top" id="saletable">
						<table width="100%" style="border: 1px solid #e69623; height: auto; padding: 1em; font-size: 12px; table-layout: fixed;">
						<input type="hidden" name="itemsize" id="itemsize" value="0">
							<col width="5%"/>
							<col width="15%"/>
							<col width="10%"/>
							<col width="10%"/>
							<col width="10%"/>
							<col width="10%"/>
								<tr>
									<th></th>
									<th align="left">Product Name</th>
									<th align="left">Size</th>
									<th align="left">M.R.P</th>
									<th align="left">Quantity</th>
									<th align="left">Total</th>
									
								</tr>
								<!--<tr>
									<td><input type="checkbox" name="chksale"></td>
									<td style="padding-left: 10px;">Magi Soup</td>
									<td style="padding-left: 0px;">20.0</td>
									<td style="padding-left: 20px;">3</td>
									<td>100.00</td>
								</tr>
								<tr>
									<td colspan="5"><hr></hr></td>
								</tr>
								<tr>
									<td colspan="5">
										<table width="100%">
											<col width="80%"/>
											<col width="20%"/>
											<tr>
												<td>Anount to Pay</td>
												<td>1000.00</td>
											</tr>
										</table>
									</td>
								</tr>
								
						--></table>
					</td>
				</tr>
				
			</table>			
				

				
				
				
				
				
				
       			
       			
		
		</s:form>
		
		
	</div>
</div>