<%@taglib uri="/struts-tags" prefix="s" %>

<script type="text/javascript" src="common/js/pagination.js"></script>

<script type="text/javascript" src="user/js/remainstock.js"></script>

<script type="text/javascript" src="user/js/setbranch.js"></script>

<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Product</h2>
	
	<div id="login" class="block_div">
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		<!--<div><a href="inputProduct">Add</a>	
		
		-->
	
			<div><!-- <a href="inputProduct">Add</a>	 -->
			<s:form action="Product" theme="simple">
				<div align="center">Search: <s:textfield theme="simple" placeholder = "Search by Product" name="searchText" size="40"/>
					<s:textfield theme="simple" placeholder = "Search by Article Number" name="articleNumber" size="40"/>
					<input type="submit" value="Go"/>
				</div>
			</s:form>
			</div>
		<div>
			<s:if test="#session.LOGIN_INFO.userType==1">
					<s:form action="Product" theme="simple">
					<table>
						<tr>
							<td>Select Branch : </td>
							<td>
								<s:select theme="simple" name="branchID" list="branchList" listKey="id" listValue="branchName" headerKey="0" headerValue="Select Branch" label="Select a Branch" onchange="setBranch(this.value)"/>
								
							</td>
							<td>
								<input type="submit" value="G0">
							</td>
						</tr>
					</table>
					</s:form>
				</s:if>
			</div>
			
			<div class="form_elements">	
						
				<table id="results" cellpadding="0" cellspacing="0" class="my-table"  style="width:100%;">
					<tr>
						
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Category</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;" class="sortable <s:if test="#attr.pagination.sortColumn.equals('productname')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
						<a href="#" onclick="fnPagination(6,'productname');">Product</a></th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Quantity</th>
						
					<!--<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Shop List</th>
						
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Shop Stock</th>
						
						
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Last Updated</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Remain Stock</th>						
						-->
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Barcode</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Edit</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Delete</th>
					</tr>
					<s:if test="productList.size!=0">
						<% int count =1;%>
						<s:iterator value="productList" status="rowstatus">
						<tr>	
							<s:if test="#rowstatus.even == true">
								<tr class="ac_odd">
							</s:if>
									
									<td><s:property  value="categoryName" /></td>
									<td><s:property  value="productName" /></td>
									
									<td id="pqtyy<s:property value="id"/>"><s:property  value="quantity" /></td>
									
									<%-- <td>
										<s:select onchange="showShopQty(this.value,%{id})" theme="simple" name="userid" id="pdd%{id}" list="userList" 
											listKey="id" listValue="companyName" 
											headerKey="0" headerValue="Select Shop"
											
											/>
									</td>
									
									<td style="text-align:center;cursor:pointer" id="shopstock<s:property value="id"/>" ondblclick="addShopStock(<s:property value="id"/>,<s:property value="subCategoryID"/>)">0</td> --%>
									
									<td><a href="#" onclick="openDisplayPopup('moveBarcode?selectedid=<s:property value="id"/>')">Barcode</a></td>
									
									<!--<td><s:property value="lastUpdated" /></td>
									<td style="text-align: center;" id="rbutton"><s:property  value="remaioningStock" /> <a href="javascript:void(0)" onclick="showStock(<%=count %>)">Edit</a></td>
									<td id="remainstockid" style="display: none;"><input type="text"  style="text-align: center;" size="5" id="stock<%=count %>" value="<s:property  value="remaioningStock" />" name="stock"> <a href="#" onclick="updateStock(<%=count %>,<s:property  value="id" />)">Submit</a> | <a href="#" onclick="removeStock(<%=count %>)">Cancel</a></td>
									
									--><s:hidden value="%{id}" name="id"></s:hidden>
									<s:url action="editProduct" id="edit">
								    <s:param name="selectedid" value="%{id}"></s:param>
									</s:url>
								     <td><s:a href="%{edit}"><img src="common/images/edit.jpg"></img></s:a></td>
								    <s:url action="deleteProduct" id="delete">
									<s:param name="selectedid" value="%{id}"></s:param>
									</s:url>
									<td><s:a href="%{delete}"><img src="common/images/delete.gif"></img></s:a></td>	
									<%count++; %>
							</tr>


						</s:iterator>
					</s:if>
					<s:else>
						There is no category found!!
					</s:else>
							
				</table>
					
				<s:form action="Product.action" name="paginationForm" id="paginationForm" theme="simple">
				<table align="center">
					<tr>
						<td align="left">Showing all <b>(<s:property value="pagerecords"/> of <s:property value="totalRecords"/> Products)</b></td>
					<td align="right"><%@ include file="/common/pages/pagination.jsp" %></td>
					
					</tr>
					
				</table>
			</s:form><!--
					
		<table>
			<tr>
				<td>
					
					<input type="button" value="Print" onclick="printResult1();">
				</td>
				
			</tr>
			
		</table>		
				
				
			 --><script type="text/javascript">
       	function printResult1()
		{
			//obj=document.getElementById('lefttd');
			//obj.style.width=0;
			//document.getElementById('footertd').align='center';
			window.print();
			//document.getElementById('footertd').align='right';
		}
       </script>
			
				
				
       			
       			

		
		
	</div>
</div>

<s:form action="inputBarcode" id="barcodefrm" method="post" onsubmit="return openBarcodePopup()" target="formtarget">

</s:form>