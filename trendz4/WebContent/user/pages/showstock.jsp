<%@taglib uri="/struts-tags" prefix="s" %>

<script type="text/javascript" src="common/js/pagination.js"></script>

<div id="login_main" class="main_layout_content">
	<h2 class="heading" >All Stock</h2>
	
	<div id="login" class="block_div">
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		
		<div>
		<s:if test="%{#categoryList != 'null'}" >
	   		<s:select theme="simple" id="categoryList" name="categoryList" list="categoryList" headerKey="0" headerValue="-All-" 
	   					 labelposition="left" required="true"
						title="Select Branch" /></s:if>
		</div>		
		
			<div class="form_elements">	
						
				<table id="results" cellpadding="0" cellspacing="0" class="my-table"  style="width:100%;">
					<tr>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Category</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Description</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Last Updated</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Edit</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Delete</th>
					</tr>
					
						<s:iterator value="categoryList" status="rowstatus">
						<tr>	
							<s:if test="#rowstatus.even == true">
								<tr class="ac_odd">
							</s:if>
									
									<td><s:property  value="categoryName" /></td>
									<td><s:property  value="description" /></td>
									<td><s:property value="lastupdated" /></td>
									<td><a href="#"><img src="common/images/edit.jpg"></img></a></td>
									<td><a href="#"><img src="common/images/delete.gif"></img></a></td>
							</tr>

						</s:iterator>
					
							
				</table>
					
				<table width="100%">
					<tr>
          
			          <td>
			          <form action="Category.action" method="post" id="paginationForm" >
						<%@ include file="/common/pages/pagination.jsp" %>
					</form>
			          </td>
			        </tr>
			
				</table>
				
			
	</div>
</div>