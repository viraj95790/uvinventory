<%@taglib uri="/struts-tags" prefix="s" %>

<script type="text/javascript" src="common/js/pagination.js"></script>

<script type="text/javascript" src="user/js/setbranch.js"></script>


<script type="text/javascript">
	var tableToExcel = (function() {
		var uri = 'data:application/vnd.ms-excel;base64,', template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>', base64 = function(
				s) {
			return window.btoa(unescape(encodeURIComponent(s)))
		}, format = function(s, c) {
			return s.replace(/{(\w+)}/g, function(m, p) {
				return c[p];
			})
		}
		return function(table, name) {
			if (!table.nodeType)
				table = document.getElementById(table)
			var ctx = {
				worksheet : name || 'Worksheet',
				table : table.innerHTML
			}
			window.location.href = uri + base64(format(template, ctx))
		}
	})()
</script>



<div id="login_main" class="main_layout_content">
	<h2 class="heading" >View User
	              <a href="#"
					onclick="tableToExcel('tblExport', 'Case List Report')"
					title="Download Excel File">Download Excel</a>
	
	</h2>
	
	<div id="login" class="block_div">
		<div><a href="inputCustomer">Add Customer</a> |
			<a href="vendorCustomer">Add Vendor</a>
		</div>
		<span class="error"><s:actionerror id="server_side_error"/></span>
		<div>
		
		<br>
		     
			<s:form action="Customer" theme="simple">
			
				<div align="center">Search: <s:textfield theme="simple" name="searchText" placeholder="Search Name or Mobile" size="80"/>
					<s:select list="#{'0':'All','1':'Customer','2':'Vendor'}" name="filterusertype" id="filterusertype"/>
					<input type="submit" value="Go"/>
				</div>
				
				
				
			</s:form>
		</div>
		
		
			
		<div class="form_elements">	
						
				<table id="results" cellpadding="0" cellspacing="0" class="my-table"  style="width:100%;">
					<tr>
	                     <!--<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Description</th>
						--><th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Name</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Mobile</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">City</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">UserType</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">View Account</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Edit</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Delete</th>
					</tr>
					<s:if test="customerList.size!=0">
						<s:iterator value="customerList" status="rowstatus">
						<tr>	
							<s:if test="#rowstatus.even == true">
								<tr class="ac_odd">
							</s:if>
									
							       <td><s:property value="title"/><s:property value="name"/> <s:property value="surname"/></td>
									<!--<td><s:property  value="description" /></td>
									--><td><s:property value="mob"/></td>
									<td><s:property value="city"/></td>
									<td>
									<%-- <s:property value="usertype"/> --%>
										<s:if test="usertype==0">
											Customer
										</s:if>
										<s:else>
											Vendor
										</s:else>
									
									
									</td>
									
									
     								<td><a href="#" onclick="openDisplayPopup('accountInvoiceReport?selectedid=<s:property value="id"/>')">View Account</a></td>
									   
										<s:url action="editCustomer" id="edit">
	    									<s:param name="selectedid" value="%{id}"></s:param>
										</s:url>
     								<td><s:a href="%{edit}"><img src="common/images/edit.jpg"></img></s:a></td>
     								
     								
    								<s:url action="deleteCustomer" id="delete">
									<s:param name="selectedid" value="%{id}"></s:param>
									</s:url>
									<td><s:a href="%{delete}"><img src="common/images/delete.gif"></img></s:a></td>	
							</tr>

						</s:iterator>
					</s:if>
					<s:else>
						There is no category found!!
					</s:else>
							 
				</table>
			
	     <%-- <s:form action="Category.action" name="paginationForm" id="paginationForm" theme="simple">
			<table align="center">
				<tr>
					<td align="left">Showing all <b>(<s:property value="totalRecords"/> of <s:property value="pagerecords"/> Categories)</b></td>
				<td align="right"><%@ include file="/common/pages/pagination.jsp" %></td>
				
				</tr>
		</table>
		</s:form>  --%>
		
		<table id="tblExport"  cellpadding="0" cellspacing="0" class="my-table"  style="width:100%; display: none;">
					<tr>
	                     <!--<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Description</th>
						--><th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Name</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Mobile</th>
						<!-- <th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">City</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">UserType</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">View Account</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Edit</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Delete</th> -->
					</tr>
					<s:if test="customerList.size!=0">
						<s:iterator value="customerList" status="rowstatus">
						<tr>	
							<s:if test="#rowstatus.even == true">
								<tr class="ac_odd">
							</s:if>
									
							       <td><s:property value="title"/><s:property value="name"/> <s:property value="surname"/></td>
									<!--<td><s:property  value="description" /></td>
									--><td><s:property value="mob"/></td>
									<%-- <td><s:property value="city"/></td>
									<td>
									<s:property value="usertype"/>
										<s:if test="usertype==0">
											Customer
										</s:if>
										<s:else>
											Vendor
										</s:else>
									
									
									</td>
									
									
     								<td><a href="#" onclick="openDisplayPopup('accountInvoiceReport?selectedid=<s:property value="id"/>')">View Account</a></td>
									   
										<s:url action="editCustomer" id="edit">
	    									<s:param name="selectedid" value="%{id}"></s:param>
										</s:url>
     								<td><s:a href="%{edit}"><img src="common/images/edit.jpg"></img></s:a></td>
     								
     								
    								<s:url action="deleteCustomer" id="delete">
									<s:param name="selectedid" value="%{id}"></s:param>
									</s:url>
									<td><s:a href="%{delete}"><img src="common/images/delete.gif"></img></s:a></td> --%>	
							</tr>

						</s:iterator>
					</s:if>
					<s:else>
						There is no category found!!
					</s:else>
							 
				</table>
				
				
				
				
				
				
       			
       			

		
		
	</div>
</div>