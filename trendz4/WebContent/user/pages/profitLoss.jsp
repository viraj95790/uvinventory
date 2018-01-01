<%@taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript" src="common/js/pagination.js"></script>

<script type="text/javascript" src="user/js/setbranch.js"></script>
<script src="chosen/chosen.jquery.js" type="text/javascript"></script>
<link rel="stylesheet" href="chosen/chosen.css">
<script type="text/javascript" src="common/js/jquery.table2excel.js"></script>



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


	<div id="login" class="block_div">
		<span class="error"><s:actionerror id="server_side_error" /></span>
		<s:form action="profitlossReport" id="" theme="simple">

			<h2 class="heading">
				Profit-Loss Report <a href="#"
					onclick="tableToExcel('tblExport', 'Case List Report')"
					title="Download Excel File">Download Excel</a>
				
			</h2>
			
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


			<table width="100%" style="padding-left: 100px;">
              <col width="20%">
              <col width="20%">
              <col width="60%">

				<tr>
					<td>SELECT PRODUCT :</td>
					<td><s:select onchange="" name="productName" id="productName"
							list="productList" listKey="id" listValue="productName"
							cssClass="chosen-select" headerKey="0"
							headerValue="Select Product" /></td>
							
							<td><input type="submit" value="Go" /></td>
					 
					
				</tr>

				<%-- <tr>
					<td><s:select onchange="" name="productName" id="productName"
							list="productList" listKey="id" listValue="productName"
							cssClass="chosen-select" headerKey="0"
							headerValue="Select Product" /></td>

					<td><input type="submit" value="Go" /></td>
				</tr> --%>



			</table>


			<table id="tblExport" cellpadding="0" cellspacing="0" class="my-table"
				style="width: 100%;">
				<tr>
					<th
						style="background: transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Product</th>
					<th
						style="background: transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Purchase</th>
					<th
						style="background: transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Sale</th>
					<th
						style="background: transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Profit</th>
					<th
						style="background: transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Loss</th>
				</tr>

				<s:iterator value="profitlossList">
					<tr>
						<td><s:property value="productName" /></td>
						<td><s:property value="price" /></td>
						<td><s:property value="saleprice" /></td>
						<td><s:property value="profit" /></td>
						<td><s:property value="loss" /></td>

					</tr>



				</s:iterator>

			</table>

		


		</s:form>
	</div>
</div>