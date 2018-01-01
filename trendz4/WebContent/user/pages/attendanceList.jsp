<%@taglib uri="/struts-tags" prefix="s" %>

<script type="text/javascript" src="common/js/pagination.js"></script>

<script type="text/javascript" src="user/js/setbranch.js"></script>
<script type="text/javascript" src="user/js/attendance.js"></script>

	<link href="common/css/datePicker1.css" rel="stylesheet"/>
	<script src="common/js/date.js" type="text/javascript"></script>
	<script src="common/js/jquery/jquery.datePicker.js" type="text/javascript"></script>
	<script src="common/js/CalenderPicture.js" type="text/javascript"></script>

<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Attendance Report</h2>
	
	<div id="login" class="block_div">
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		
			<s:form action="Attendance" theme="simple" id="attendancefrm">
				<div>Select Employee : <s:select name="employeeid" list="populatedSalesManList" listKey="employeeid" listValue="name" headerKey="0" headerValue="Select Employee"   />
				</div>
				
				<table width="100%"  style="padding-left: 100px;">
					<col width="45%"/>
					<col width="45%"/>
					<col width="10%"/>
					<tr>
						<td>
							<table width="100%">
								<col width="5%"/>
								<col width="2%"/>
								<col width="8%"/>
								<tr>
									<td>From Date<span class="reqd-info">*</span></td>
									<td align="center">:</td>
									<td><s:textfield name="fromDate" id="fromDate" cssClass="date-pick dp-applied"/></td>
								</tr>
							</table>
						</td>
						<td>
							<table width="100%">
								<col width="3%"/>
								<col width="2%"/>
								<col width="8%"/>
								<tr>
									<td>To Date<span class="reqd-info">*</span></td>
									<td align="center">:</td>
									<td><s:textfield name="toDate" id="toDate" cssClass="date-pick dp-applied"/>
									<input type="submit" value="Go"/>
									</td>
								</tr>
							</table>
						</td>
						
					</tr>
				
				
					
				</table>
				
				<s:if test="attendanceList.size!=0">
				<table id="results" cellpadding="0" cellspacing="0" class="my-table"  style="width:100%;">
					<tr>
						
						
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Employee Name</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Date</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Status</th>
						
					</tr>
					
						<s:iterator value="attendanceList" status="rowstatus">
						<tr>	
							<s:if test="#rowstatus.even == true">
								<tr class="ac_odd">
							</s:if>
									
									<td><s:property  value="firstName" /> <s:property  value="lastName" /></td>
									<td><s:property value="currentDateTime"/></td>
									<td><a href="#">Present</a></td>
									
							</tr>

						</s:iterator>
					
					
					<tr>
						<td colspan="3"><b>Total salary as per attendance : Rs. <s:property value="totalSalary"/> </b></td>
					</tr>
							
				</table>
			
				</s:if>
				
				
				</s:form>
		
		
		
			
				
				
				
				
       			
       			

		
		
	</div>
</div>