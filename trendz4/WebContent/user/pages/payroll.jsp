<%@ taglib uri="/struts-tags" prefix="s" %>


<%@page import="com.inventorymanagement.main.web.common.helper.LoginInfo"%>
<%@page import="com.inventorymanagement.main.web.common.helper.LoginHelper"%>
<%@page import="com.inventorymanagement.main.common.constants.Constants"%>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="user/js/recordpayment.js"></script>

 <div id="login_main" class="main_layout_content">
	<h2 class="heading" >PayRoll</h2>
	
	
	</div>
		<span class="error"><s:actionerror id="server_side_error"/></span>
		<div>
	
	<div class="form_elements">	
	<a href="payrolexpenseInvoiceReport">ADD EXPENSES</a>
						
				<table id="results" cellpadding="0" cellspacing="0" class="my-table"  style="width:100%;">
					<tr>
	                     <!--<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Description</th>
						--><th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Date</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Customer</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Description</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">PayMode</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Credit</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Debit</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Balance</th>
						
					</tr>
					
					<s:iterator value="payrolList">
					
					
					<tr>
					    <td><s:property value="date"/></td>
		                <td>
		                	<s:if test="debit==0">
		                		<s:property value="customer"/>
		                	</s:if>
		                </td>
		                <td><s:property value="discription"/></td>
  	                    <td><s:property value="paymode"/></td>
  	                    <td>Rs<s:property value="credit"/></td>
  	                    <td>Rs<s:property value="debit"/></td> 
  	                    <td>Rs<s:property value="balance"/></td>
  	                   
					</tr>
					
		       </s:iterator>
		
		</table>
	  
	
	</div>