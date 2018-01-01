<%@taglib uri="/struts-tags" prefix="s" %>

<script type="text/javascript" src="common/js/pagination.js"></script>

<script type="text/javascript" src="user/js/setbranch.js"></script>

<div id="login_main" class="main_layout_content">
	<h2 class="heading" >SubCategory</h2>
	
	<div id="login" class="block_div">
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		<div><a href="subcategoryProduct">Add</a>
			<s:form action="AdminSubcategory" theme="simple">
				<div align="center">Search: <s:textfield theme="simple" name="searchText" placeholder="Search by SubCategory" size="80"/>
					<input type="submit" value="Go"/>
				</div>
			</s:form>
			
		</div>	
		
			<div class="form_elements">	
						
				<table id="results" cellpadding="0" cellspacing="0" class="my-table"  style="width:100%;">
					<tr>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Category</th>
							<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;" class="sortable <s:if test="#attr.pagination.sortColumn.equals('productname')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
						<a href="#" onclick="fnPagination(6,'productname');">SubCategory</a></th>
						
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Price</th>

						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Edit</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Delete</th>
					</tr>
					<s:if test="subCategoryList.size!=0">
						<s:iterator value="subCategoryList" status="rowstatus">
						<tr>	
							<s:if test="#rowstatus.even == true">
								<tr class="ac_odd">
							</s:if>
									
									
									<td><s:property  value="categoryName" /></td>
									<td><s:property  value="productName" /></td>
									<td><s:property  value="price" /></td>
								
									<s:hidden value="%{id}" name="id"></s:hidden>
									<s:url action="editAdminSubcategory" id="edit">
    								<s:param name="selectedid" value="%{id}"></s:param>
									</s:url>
     								<td><s:a href="%{edit}"><img src="common/images/edit.jpg"></img></s:a></td>
    								<s:url action="deleteAdminSubcategory" id="delete">
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
			
			<s:form action="AdminSubcategory.action" name="paginationForm" id="paginationForm" theme="simple">
			<table align="center">
				<tr>
					<td align="left">Showing all <b>(<s:property value="totalRecords"/> of <s:property value="pagerecords"/> Sub Categories)</b></td>
				<td align="right"><%@ include file="/common/pages/pagination.jsp" %></td>
				
				</tr>
			</table>
		</s:form>
				
				
				
				
				
				
       			
       			

		
		
	</div>
</div>