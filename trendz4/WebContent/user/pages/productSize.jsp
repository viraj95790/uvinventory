<%@taglib uri="/struts-tags" prefix="s" %>

<script type="text/javascript" src="common/js/pagination.js"></script>



<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Stock</h2>
	
	<div id="login" class="block_div">
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		<div class="form_elements">	
				
				<div>Total Stock : <s:text name="%{sizeList.size}"/></div>
				
				<table id="results" cellpadding="0" cellspacing="0" class="my-table"  style="width:100%;">
					<tr>
						
						
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Size</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Color</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Edit</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Delete</th>
					</tr>
					<s:if test="sizeList.size!=0">
						<s:iterator value="sizeList" status="rowstatus">
						<tr>	
							<s:if test="#rowstatus.even == true">
								<tr class="ac_odd">
							</s:if>
									
									<td><s:property  value="sizeValue" /></td>
									<td><s:property  value="colorName" /></td>
									<s:hidden value="%{id}" name="id"></s:hidden>
									<s:url action="editsizeProduct" id="edit">
    								<s:param name="selectedid" value="%{id}"></s:param>
									</s:url>
     								<td><s:a href="%{edit}"><img src="common/images/edit.jpg"></img></s:a></td>
    								<s:url action="deletesizeProduct" id="delete">
									<s:param name="selectedid" value="%{id}"></s:param>
									</s:url>
									<td><s:a href="%{delete}"><img src="common/images/delete.gif"></img></s:a></td>	
							</tr>

						</s:iterator>
					</s:if>
					<s:else>
						There is no Stock found!!
					</s:else>
							
				</table>
			
			
				
				<div>
					<s:form action="editProduct">
						<input type="hidden" name="selectedid" value="<s:property value="selectedID"/>"/>
						<input type="submit" value=" Back ">
					</s:form>
				
				</div>
				
				
				
				
       			
       			

		
		
	</div>
</div>