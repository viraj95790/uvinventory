<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="user/js/attendance.js"></script>



<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Add Attendance</h2>
	<span id="mandatory_message" class="error">Note:Fields marked with asterisk(*) are required.</span> 
	<div id="login" class="block_div">
		
		<s:if test="hasActionMessages()"> 
			<div id="common_message" class="message">
				<s:actionmessage id="common_message_text" theme="simple"/>
			</div>
		</s:if> 
		
		<s:form action="saveAttendance" id="attendancefrm" theme="simple">
			<div class="form_elements">				
				<table width="100%" style="border: 1px solid #e69623; height: auto; padding: 1em; ">
					<col width="10%"/>
					<col width="2%"/>
					<col width="30%"/>
					<tr>
						<td>Today</td>
						<td align="center">:</td>
						<td>
							<s:textfield id="currentdatetime" name="currentdatetime"  readonly="true"  size="30" cssStyle="text-align:center;"/>
						</td>
					</tr>
					<tr>
						<td><s:select name="employeeid" list="populatedSalesManList" listKey="employeeid" listValue="name" headerKey="0" headerValue="Select Employee"   /></td>
						<td align="center">:</td>
						
							<td><s:submit/></td>
						
					</tr>
					
				</table>
				
				
				
				
				
				
				
       			
       			
		
		</s:form>
		
		
	</div>
</div>