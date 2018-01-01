 <%@ taglib uri="/struts-tags" prefix="s" %>
 
  
  <script type="text/javascript" src="common/js/pagination.js"></script>

<script type="text/javascript" src="user/js/setbranch.js"></script>

<div id="login_main" class="main_layout_content">
	<h2 class="heading" >View Shop</h2>
	
	<div id="login" class="block_div">
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		<div><a href="inputShopBranch">ADD Shop</a><br>
			<s:form action="Branch" theme="simple">
			<div align="center">Search: <s:textfield theme="simple" name="searchText" placeholder="Search by Category" size="80"/>
					<input type="submit" value="Go"/>
				</div>
				
			</s:form>
		</div>
		
		
			
		<div class="form_elements">	
						
				<table id="results" cellpadding="0" cellspacing="0" class="my-table"  style="width:100%;">
					<tr>
	                     <!--<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Description</th>
						--><th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Shop Name</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Mobile no.</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">City</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Email</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">UserType</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Edit</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Delete</th>
					</tr>
					<s:if test="branchList.size!=0">
						<s:iterator value="branchList" status="rowstatus">
						<tr>	
							<s:if test="#rowstatus.even == true">
								<tr class="ac_odd">
							</s:if>
									
							       <td><s:property value="companyName"/></td>
									<!--<td><s:property  value="description" /></td>
									--><td><s:property value="mobileNo"/></td>
									<td><s:property value="city"/></td>
									<td><s:property value="email"/></td>
									<td>
									<!--<s:property value="userType"/>-->
									 <s:if test="userType==0">
									       Admin
									 </s:if>
									 <s:else>
									   Shop
									 </s:else>
									</td>   
									 <s:url action="geteditBranch" id="getedit">
    								<s:param name="selectedid" value="%{id}"></s:param>
									</s:url>
     								<td><s:a href="%{getedit}"><img src="common/images/edit.jpg"></img></s:a></td>
    								<s:url action="getdeleteBranch" id="getdelete">
									<s:param name="selectedid" value="%{id}"></s:param>
									</s:url>
									<td><s:a href="%{getdelete}"><img src="common/images/delete.gif"></img></s:a></td> 	
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
				
				
				
				
				
				
       			
       			

		
		
	</div>
</div>