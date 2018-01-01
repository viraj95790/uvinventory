<%@ taglib uri="/struts-tags"  prefix="s"%>
<html>
<head>
<title>All Branches</title>
</head>
<body>

<div id="login_main" class="main_layout_content">


<h3 align="center">All Branches Details</h3>

<div id="login" class="block_div">
<div class="form_elements">	

<s:a href="inputBranch">Add Branch</s:a></div>
<table id="results" cellpadding="0" cellspacing="0" class="my-table"  style="width:100%;">
<tr>
	<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Branch ID</th>
	<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Branch Name</th>
	<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Edit</th>
	<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Delete</th>
</tr>
<s:iterator value="branchList" status="rowstatus">

<tr>
<s:if test="#rowstatus.even == true">
	<tr class="ac_odd">
</s:if>

	<td><s:property value="userId"/></td>
	<td><s:property value="branchName"/></td>
	
	
	<s:hidden value="%{id}" name="id"></s:hidden>
	<s:url action="editBranch" id="edit">
    <s:param name="selectedid" value="%{id}"></s:param>
	</s:url>
     <td><s:a href="%{edit}"><img src="common/images/edit.jpg"></img></s:a></td>
    <s:url action="deleteBranch" id="delete">
	<s:param name="selectedid" value="%{id}"></s:param>
	</s:url>
	<td><s:a href="%{delete}"><img src="common/images/delete.gif"></img></s:a></td>	
	
</tr>
</s:iterator>
</table>	
</div>
</div>
<div>

</body>
</html>