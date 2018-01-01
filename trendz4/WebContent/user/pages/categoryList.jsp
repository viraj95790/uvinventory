<%@taglib uri="/struts-tags" prefix="s" %>

<script type="text/javascript" src="common/js/pagination.js"></script>

<script type="text/javascript" src="user/js/setbranch.js"></script>

<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Category</h2>
	
	<div id="login" class="block_div">
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		<div><a href="inputCategory">Add</a>
			<s:form action="Category" theme="simple">
				<div align="center">Search: <s:textfield theme="simple" name="searchText" placeholder="Search by Category" size="80"/>
					<input type="submit" value="Go"/>
				</div>
			</s:form>
		</div>
		
		
			
			<!--<s:if test="#session.LOGIN_INFO.userType==1">
				<s:form action="Category" theme="simple">
				<table>
					<tr>
						<td>Select Branch : </td>
						<td>
							<s:select theme="simple" name="branchID" list="branchList" listKey="id" listValue="branchName" headerKey="0" headerValue="All" label="Select a Branch" onchange="setBranch(this.value)"/>
							
						</td>
						<td>
							<input type="submit" value="G0">
						</td>
					</tr>
				</table>
				</s:form>
			</s:if>
		</div>	
		
			--><div class="form_elements">	
						
				<table id="results" cellpadding="0" cellspacing="0" class="my-table"  style="width:100%;">
					<tr>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;" class="sortable <s:if test="#attr.pagination.sortColumn.equals('categoryname')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
						<a href="#" onclick="fnPagination(6,'categoryname');">Category</a></th>
						
						<!--<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Description</th>
						--><th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Last Updated</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Gender</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Quantity</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Edit</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Delete</th>
					</tr>
					<s:if test="categoryList.size!=0">
						<s:iterator value="categoryList" status="rowstatus">
						<tr>	
							<s:if test="#rowstatus.even == true">
								<tr class="ac_odd">
							</s:if>
									
									<td><s:property  value="categoryName" /></td>
									<!--<td><s:property  value="description" /></td>
									--><td><s:property value="lastupdated" /></td>
										<s:if test="gender==2">
											<td style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Ladies</td>
										</s:if>
										<s:if test="gender==1">
											<td style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Gents</td>
										</s:if>
										<s:if test="gender==3">
											<td style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Accessories</td>
										</s:if>
									<td><s:property value="quantity"/></td>
									<s:hidden value="%{id}" name="id"></s:hidden>
									<s:url action="editCategory" id="edit">
    								<s:param name="selectedid" value="%{id}"></s:param>
									</s:url>
     								<td><s:a href="%{edit}"><img src="common/images/edit.jpg"></img></s:a></td>
    								<s:url action="deleteCategory" id="delete">
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
			
			<s:form action="Category.action" name="paginationForm" id="paginationForm" theme="simple">
			<table align="center">
				<tr>
					<td align="left">Showing all <b>(<s:property value="totalRecords"/> of <s:property value="pagerecords"/> Categories)</b></td>
				<td align="right"><%@ include file="/common/pages/pagination.jsp" %></td>
				
				</tr>
			</table>
		</s:form>
				
				
				
				
				
				
       			
       			

		
		
	</div>
</div>