<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<%@page import="com.inventorymanagement.main.web.common.helper.LoginInfo"%>
<%@page import="com.inventorymanagement.main.web.common.helper.LoginHelper"%>
<html lang="en">
<head>
<title>Title</title>
<meta charset="utf-8"/>

<link href="common/css/960.css" rel="stylesheet" type="text/css"/>
<link href="common/css/coolMenu.css" rel="stylesheet" type="text/css" media="screen"/>
<script type="text/javascript" src="common/js/modernizr-1.6.min.js"></script>
</head>
<body>

	<div class="container_16 .suffix_1">
		<div class="grid_16">
		
		
		
		<ul id="coolMenu">
		   <li><a href="Default">Home</a></li>
			
			<s:if test="(#session.LOGIN_INFO != null)&&(#session.LOGGED_IN)">
				<%LoginInfo loginInfo = LoginHelper.getLoginInfo(request);%>
				<li><a href="Logout">Logout</a></li>
				<% if(loginInfo.getUserType()==2){%>
				      <li><a href="Customer">View User</a></li>
				<% }%>
				
				<% if(loginInfo.getUserType()==0){%> 
				<li><a href="#">Admin</a>
				<ul class="noJS">
							<li><a href="Category">Category</a></li>
							<li><a href="AdminSubcategory">SubCategory</a></li>
							<li><a href="Product">Product</a></li>
							<li><a href="Color">Add Color</a></li>
							<li><a href="Customer">View User</a></li>
							<li><a href="Branch">View shop</a></li>
							<!--<li><a href="Accessories">Accessories</a></li>
							
							--><!--
							<li><a href="adminStockProduct">Stock</a></li>
							<li><a href="AdminBill">Product Bill</a></li>
				--></ul>
				</li>
				<% }%>
				<!--<li><a href="CustomCake">Custom Cake</a></li>
				-->
				<% if(loginInfo.getUserType()==0) {%>
				<li><a href="inputPurchase">Purchase</a>
					<ul class="noJS">
						<li><a href="inputPurchase">Purchase</a></li>
						<li><a href="accountPurchaseInvoice">Purchase Account</a></li>
						
					</ul>
				</li>
				<% }%>
				<li><a href="inputSale">Sale</a>
					<ul class="noJS">
						<li><a href="inputSale">Sale</a></li>
						<li><a href="backSaleReturn">Sale Return</a></li>
						<!-- <li><a href="accountInvoiceReport">Sale Account</a></li> -->
					</ul>
				</li>
				<li><a href="InvoiceReport">Invoice Report</a>
					<ul class="noJS">
						<li><a href="InvoiceReport">Sale Invoce</a></li>
						
						<% if(loginInfo.getUserType()==0) {%>
						<li><a href="PurchaseInvoice">Purchase Invoice</a></li>
						<% }%>
						<li><a href="Report">Product Period Report</a></li>
						<li><a href="profitlossReport">Profit-Loss Report</a></li>
						
					</ul>
				</li>
				<li><a href="Sendsms">Send SMS</a></li>
				<li><a href="SalesMan">Sales Man</a>
					<ul class="noJS">
						<li><a href="SalesMan">SalesMan</a></li>
						<li><a href="inputInvoiceReport">Sales</a></li>
						
					</ul>
				</li>
				<li><a href="inputAttendance">Attendance</a>
					<ul class="noJS">
						<li><a href="inputAttendance">Add Attendance</a></li>
						<li><a href="Attendance">Attendance Report</a></li>
					</ul>
				</li>
				<li><a href="orBarcode">Barcode</a></li>
				<li><a href="displayInvoiceReport">PayRoll</a></li>
			</s:if>	
			
			<s:else>
				<li><a href="inputLogin">Login</a></li>
				<!-- <li><a href="inputBranch">Registration</a></li> -->
			</s:else>
		</div>
	</div>


<script type="text/javascript" src="common/js/scripts.js"></script>
</body>
</html>