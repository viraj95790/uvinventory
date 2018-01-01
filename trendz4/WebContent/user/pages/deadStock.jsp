<%@taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript" src="common/js/pagination.js"></script>

<script type="text/javascript" src="user/js/setbranch.js"></script>
<script src="chosen/chosen.jquery.js" type="text/javascript"></script>
<link rel="stylesheet" href="chosen/chosen.css">



<link href="common/css/datePicker1.css" rel="stylesheet" />
<script src="common/js/date.js" type="text/javascript"></script>
<script src="common/js/jquery/jquery.datePicker.js"
	type="text/javascript"></script>
<script src="common/js/CalenderPicture.js" type="text/javascript"></script>

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
<h2 class="heading">
          Product Period Report <a href="#"
					onclick="tableToExcel('tblExport', 'Case List Report')"
					title="Download Excel File">Download Excel</a>
          
          </h2>

<div id="login" class="block_div"><span class="error"><s:actionerror
	id="server_side_error" /></span> <s:form action="Report" theme="simple">
	<!--<div>Search: <s:textfield theme="simple" name="searchText" placeholder="Search by invoice number" />
				<input type="submit" value="Go"/>
				</div>
				
				-->

	<%-- <div>
					<b>( Total Purchase : <s:text name="%{invoiceList.size}"/>
						>> Total Quantity : <s:text name="%{totalQuantity}"/>
						>> Total Amount : Rs. <s:text name="%{totalAmount}"/> )
					</b>
				</div> --%>


	<table width="50%" style="padding-left: 100px;">
	
		
		<tr>
			<td>From Date<span class="reqd-info">*</span></td>
			<td>To Date<span class="reqd-info">*</span></td>
			<td>SELECT PRODUCT :</td>
			<td></td>
		</tr>
		<tr>
			<td><s:textfield name="fromdate" id="fromdate"
						cssClass="date-pick dp-applied" /></td>
			<td><s:textfield name="todate" id="todate"
						cssClass="date-pick dp-applied" /> 
			<td><s:select onchange="" name="productName" id="productName" list="productList" listKey="id"
						listValue="productName" cssClass="chosen-select" headerKey="0" headerValue="Select Product" />
						<td> <input type="submit" value="Go" /></td>
		</tr>
		



	</table>


	<table id="tblExport" cellpadding="0" cellspacing="0" class="my-table"
		style="width: 100%;">
		<tr>
			<th
				style="background: transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">PurchaseDate</th>
			<th
				style="background: transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Name</th>
			<th
				style="background: transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Period</th>
			<th
				style="background: transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Stock</th>
		</tr>

		<s:iterator value="stockInfoList">
			<tr>
				<td><s:property value="lastUpdated" /></td>
				<td><s:property value="productName" /></td>
				<td><s:property value="days" /></td>
				<td><s:property value="quantity" /></td>

			</tr>



		</s:iterator>

	</table>



</s:form></div>
</div>