<%@taglib uri="/struts-tags" prefix="s" %>

<script type="text/javascript" src="common/js/pagination.js"></script>



<script type="text/javascript" src="user/js/customcake.js"></script>

<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Custom Cake</h2>
	
	<div id="login" class="block_div">
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		<div>
			<s:if test="#session.LOGIN_INFO.userType!=1">
				<a href="inputCustomCake">Add</a>
			</s:if>
			<s:if test="#session.LOGIN_INFO.userType==1">
				<s:form action="CustomCake" theme="simple">
				<table>
					<tr>
						<td>Select Branch : </td>
						<td>
							<s:select theme="simple" name="branchID" list="branchList" listKey="id" listValue="branchName" headerKey="0" headerValue="Select Branch" label="Select a Branch" onchange="setBranch(this.value)"/>
							
						</td>
						<td>
							<input type="submit" value="G0">
						</td>
					</tr>
				</table>
				</s:form>
			</s:if>
			
		</div>	
		
			<div class="form_elements">	
						
				<table id="results" cellpadding="0" cellspacing="0" class="my-table"  style="width:100%;">
					<col width="8%"/>
					<col width="10%"/>
					<col width="15%"/>
					<col width="14%"/>
					<col width="8%"/>
					<col width="15%"/>
					<col width="6%"/>
					<col width="6%"/>
					<col width="5%"/>
					
					<tr>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Weight</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Flavour</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Model Design Notes</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Msg on Cake</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Amount</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Delivery Time</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Edit</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Delete</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Status</th>
						
					</tr>
					<%int count = 1; %>
					<s:if test="customCakeList.size!=0">
						<s:iterator value="customCakeList" status="rowstatus">
						<tr>	
							<s:if test="#rowstatus.even == true">
								<tr class="ac_odd">
							</s:if>
									
									
									<td><s:property  value="weight" /></td>
									<td><s:property  value="flavour" /></td>
									<td><s:property  value="notes" /></td>
									<td><s:property  value="msgOnCake" /></td>
									<td><s:property  value="amount" /></td>
									<td><s:property value="deliveryDate"/> <s:property value="time"/>:<s:property value="minute"/> <s:property value="amorpm"/></td>
									
									<s:hidden value="%{id}" name="id"></s:hidden>
									<s:url action="editCustomCake" id="edit">
    								<s:param name="selectedid" value="%{id}"></s:param>
									</s:url>
     								<td><s:a href="%{edit}"><img src="common/images/edit.jpg"></img></s:a></td>
    								<s:url action="deleteCustomCake" id="delete">
									<s:param name="selectedid" value="%{id}"></s:param>
									</s:url>
									<td><s:a href="%{delete}"><img src="common/images/delete.gif"></img></s:a></td>
									<s:if test="#session.LOGIN_INFO.userType == 1">
										<s:url id="changestatusCustomCake" action="changestatusCustomCake">
											<s:param name="selectedid" value="%{id}"></s:param>
											<s:param name="status" value="%{status}"/>
										</s:url>
										<s:if test="status == 0">
											<td><s:a href="%{changestatusCustomCake}">Pending</s:a></td>
										</s:if>	
										<s:else>
											<td><s:a href="%{changestatusCustomCake}">Delivered</s:a></td>
										</s:else>
									</s:if>
									<s:else>
										<s:if test="status == 0">
											<td>Pending</td>
										</s:if>	
										<s:else>
											<td>Delivered</td>
										</s:else>
									</s:else>
							</tr>

						</s:iterator>
					</s:if>
					<s:else>
						There is no Data found!!
					</s:else>
							
				</table>
				<s:form action="CustomCake.action" name="paginationForm" id="paginationForm" theme="simple">
					<table align="center">
						<tr>
							<td align="left">Showing all <b>(<s:property value="totalRecords"/> of <s:property value="pagerecords"/> Categories)</b></td>
						<td align="right"><%@ include file="/common/pages/pagination.jsp" %></td>
						
						</tr>
					</table>
				</s:form>
			
				
				
				
				
				
       			
       			

		
		
	</div>
</div>