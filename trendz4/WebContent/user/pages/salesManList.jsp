<%@taglib uri="/struts-tags" prefix="s" %>

<script type="text/javascript" src="common/js/pagination.js"></script>



<div id="login_main" class="main_layout_content">
	<h2 class="heading" >SalesMan</h2>
	
	<div id="login" class="block_div">
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		<div class="form_elements">	
				<div><a href="inputSalesMan">Add</a>	
				<table id="results" cellpadding="0" cellspacing="0" class="my-table"  style="width:100%;">
					<tr>
						
						
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">First Name</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Last Name</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Mobile No.</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Commission %</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Edit</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Delete</th>
					</tr>
					<s:if test="salesManList.size!=0">
						<s:iterator value="salesManList" status="rowstatus">
						<tr>	
							<s:if test="#rowstatus.even == true">
								<tr class="ac_odd">
							</s:if>
									
									<td><s:property  value="firstName" /></td>
									<td><s:property  value="lastName" /></td>
									<td><s:property value="mobileNo" /></td>
									<td><s:property value="commission" /></td>
									<s:hidden value="%{id}" name="id"></s:hidden>
									<s:url action="editSalesMan" id="edit">
    								<s:param name="selectedid" value="%{id}"></s:param>
									</s:url>
     								<td><s:a href="%{edit}"><img src="common/images/edit.jpg"></img></s:a></td>
    								<s:url action="deleteSalesMan" id="delete">
									<s:param name="selectedid" value="%{id}"></s:param>
									</s:url>
									<td><s:a href="%{delete}"><img src="common/images/delete.gif"></img></s:a></td>	
							</tr>

						</s:iterator>
					</s:if>
					<s:else>
						There is no Sales Man found!!
					</s:else>
							
				</table>
			
			
				
				
				
				
				
				
       			
       			

		
		
	</div>
</div>