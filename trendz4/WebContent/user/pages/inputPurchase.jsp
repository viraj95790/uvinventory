<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="user/js/purchase.js"></script>
<script type="text/javascript" src="user/js/gender.js"></script>

  <!--  <link rel="stylesheet" href="chosen/docsupport/style.css">
  <link rel="stylesheet" href="chosen/docsupport/prism.css">  -->
  <link rel="stylesheet" href="chosen/chosen.css">

<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Purchase</h2>
	
	<div id="login" class="block_div">
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		<s:form action="Purchase" id="product_form" theme="simple">
		
		
			
			
					
						<table width="100%" style="border: 1px solid #e69623; height: auto; padding: 1em; " id="dropdowntable">
							
							<tr>
								<td>Gender<span class="reqd-info">*</span></td>
								<td>Category<span class="reqd-info">*</span></td>
								<td>Product Name<span class="reqd-info">*</span></td>
								<td>Enter Price<span class="reqd-info">*</span></td>
								<td>Quantity<span class="reqd-info">*</span></td>
								<!--<td>Select Color<span class="reqd-info">*</span></td>
								-->
								
								<td>Enter Size<span class="reqd-info">*</span></td>
								<td>Enter Code<span class="reqd-info">*</span></td>
								<td></td>
							</tr>
							
							<tr>	
								<td><s:select onchange="setGenderAjax(this.value)"  list="#{'0':'Select Gender','1':'Gents','2':'Ladies','3':'Accessories'}" theme="simple" name="gender" id="gender" /></td>
								<td id="gendercategory">
									<select name="category">
										<option value="0">Select Category</option>
									</select>
								</td>
								<td id="productajax">
									<select name="productName" cssClass="chosen-select">
										<option value="0">Select SubCategory</option>	
									</select>
								</td>
								
								<td id="priceajax">
									<input onchange="setvchkEnable()" type="text" id="price" name="price" title="Enter Price"  size="5" style="text-align: center;"/>
								</td>
								<td>
									<input type="text" id="quantity" name="quantity"   size="5" style="text-align: center;"/>
								</td>
								<td style="display: none;">
									<s:select onchange="addColor()" name="colorName" id="adcolor" list="colorList"
									listKey="colorId" listValue="colorName"
									headerKey="0" headerValue="Select Color"/>
								</td>
								
								<td>
									<input onchange="setpurchaseqsze()" type="text" id="viewsizetxt" name="viewsizetxt"   size="5" style="text-align: center;"/>
								</td>
								
								<td>
									<input type="text" id="pcode" name="pcode"   size="10" style="text-align: center;"/>
								</td>
								
								<td id="adrmovtd">
									<input type="button" id="addbtn" value="Add"  onclick="save();"/>
									<input type="button" id="rmovbtn" value="Remove" onclick="deletesale();"/>
									
								</td>
							</tr>
						</table>
						
						<table width="100%">
							<col width="50%">
							<col width="50%">
							<tr>
								<td valign="top">
									<table width="100%" id="" style="border: 1px solid #e69623; height: auto; padding: 1em; ">
							
										
										<tbody id="purchasetable">
										<tr>
											<td>Stock<span class="reqd-info"></span></td>
											<td>Stock Color<span class="reqd-info">*</span></td>
											<td>Image<span class="reqd-info">*</span></td>
											
										</tr>
										<tr>
											
											
											<td id="stockajax">
												<s:textarea id="stock" name="stock"  readonly="true" />
											</td>
											
											
											
											<td id="stockcolorajax">
												<s:textarea id="stockcolor" name="stockcolor"  readonly="true" />
											</td>
											
											
											<td id="imgview">
												<img src="image/<s:property value="uploadedimage"/>"/>
											</td>
											
											
										</tr>
										
										
										
										<tr style="display: none;">
											<td>Model No.<span class="reqd-info">*</span></td>
											<td align="center">:</td>
											<td>
												<s:textfield id="modelnumber" name="modelNumber" title="Enter Model Number"/>
											</td>
										</tr>
										
										<tr style="display: none;">
											<td>Article No.<span class="reqd-info">*</span></td>
											<td align="center">:</td>
											<td>
												<s:textfield id="articlenumber" name="articleNumber" title="Enter Article Number"/>
											</td>
										</tr>
										<tr style="display: none;">
											<td>Add Color<span class="reqd-info">*</span></td>
											<td align="center">:</td>
											<td>
												<s:select theme="simple" id="adcolor" name="colorId" list="colorList" listKey="colorId" listValue="colorName" headerKey="0" headerValue="Select Color"  onchange="setBranch(this.value)"/>
												<input type="button" value="Add" id="adcolorbtn" onclick="addColor()"/>
												
											</td>
											
											
										</tr>
										<tr style="display: none;">
											<td>Color<span class="reqd-info">*</span></td>
											<td align="center">:</td>
											<td>
												<s:textarea id="color" name="color" title="Enter color" readonly="true"/>
											</td>
										</tr>
										
										<!--<tr>
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
										--><tr style="display: none;">
											<td>View Size<span class="reqd-info">*</span></td>
											<td align="center">:</td>
											<td>
												<s:textarea id="viewsize" name="viewsize"  readonly="true"/>
												
											</td>
										</tr>
										
										
									</tbody>
										
								</table>
								</td>
								
								<td valign="top"> 
									
									
						<table id="saletable" width="100%" style="border: 1px solid #e69623; height: auto; padding: 1em; font-size: 12px; table-layout: fixed;">
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
									<!-- <th align="left">Size</th> -->
									<th align="left">M.R.P</th>
									<th align="left">GST</th>
									<th align="left">Quantity</th>
									<th align="left">Total</th>
									
								</tr>
								<!-- <tr>
									<td><input type="checkbox" name="chksale"></td>
									<td style="padding-left: 10px;">Magi Soup</td>
									<td style="padding-left: 0px;">20.0</td>
									<td style="padding-left: 20px;">3</td>
									<td>100.00</td>
								</tr> -->
								<tr>
									<td colspan="5"><hr></hr></td>
								</tr>
								<tr>
									<td colspan="5">
										<table width="100%">
											<col width="80%"/>
											<col width="20%"/>
											<tr>
												<td>Amount to Pay</td>
												<!-- <td>1000.00</td> -->
											</tr>
										</table>
									</td>
								</tr>
								
						</table>
						
							
							
						
								</td>
								
							</tr>
							
							
						</table>
						
						
							
								
							
							
					
						
						
				<table width="100%">
					<tr>
						<td>COMPANY NAME : </td>
						<td>
							<s:textfield name="customerName" id="customerName" size="40"/>
						</td>
						<td>MOBILE NO : </td>
						<td>
							<s:textfield name="mobileNo" id="mobileNo"/>
						</td>
					</tr>
					<tr style="display: none;">
						<td>Barcode Data :</td>
						
						<td width="2%">
							<s:textarea name="barcodeData" id="barcodeData" onchange="setBarcodeAjax(this.value)" disabled="true"/>
						</td>
						<td><input type="checkbox" name="chb" id="chb" onclick="setEnable();"> 
							<a href="inputPurchase">Click here for manual entry</a>
						</td>
						<!--<td><input type="checkbox" name="cha" id="cha" onclick="setAccessories();"> 
							<a href="#">Accessories</a>
						</td>
					--></tr>
				</table> 
		
						
								
								
						<table width="100%">
							<tr>
								<td>Payment Mode<span class="reqd-info">*</span></td>
								<td align="center">:</td>
								<td>
									<select name="howpaid" id="howpaid" class="form-control">
										<option value="Cash">Cash</option>
										<option value="C/Card">C/Card</option>
										<option value="Cheque">Cheque</option>
										<option value="D/Card">D/Card</option>
										<option value="Other">Other</option>
										
										
										
									
									
									</select> 
								</td>
							</tr>
							
							<tr>
								<td>Payment Given<span class="reqd-info">*</span></td>
								<td align="center">:</td>
								<td>
									<s:textfield onkeypress="return isNumberKey(event,this);"  cssStyle="width:65px;text-align:center;" id="paymentrecieved" name="paymentrecieved" maxlength="10" title="Enter Payment Recieved" />
								</td>
							</tr>
							<tr>
								<td>Payment Note<span class="reqd-info">*</span></td>
								<td align="center">:</td>
								<td>
									<s:textarea rows="3" cols="40"  id="paymentnote" name="paymentnote"  title="Enter Payment Recieved" />
								</td>
							</tr>
							<tr>
								<td><input type="button" value=" Next to Create Bill " id="billbtn" onclick="savepurchasedata()"></td>
							</tr>
							
						</table>
					 
					
					
				
				
			<script
				src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"
				type="text/javascript"></script>
			<script src="chosen/chosen.jquery.js" type="text/javascript"></script>
			<script src="chosen/docsupport/prism.js" type="text/javascript"
				charset="utf-8"></script>
			<script type="text/javascript">
				var config = {
					'.chosen-select' : {},
					'.chosen-select-deselect' : {
						allow_single_deselect : true
					},
					'.chosen-select-no-single' : {
						disable_search_threshold : 10
					},
					'.chosen-select-no-results' : {
						no_results_text : 'Oops, nothing found!'
					},
					'.chosen-select-width' : {
						width : "95%"
					}
				}
				for ( var selector in config) {
					$(selector).chosen(config[selector]);
				}
			</script>
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
       			
       			
		
		</s:form>
		
		
	</div>
</div>