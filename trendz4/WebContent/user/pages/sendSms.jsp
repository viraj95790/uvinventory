<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="s" uri="/struts-tags" %>    
<script type="text/javascript" src="user/js/sendsms.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;" charset=ISO-8859-1">


<%-- <style>

	.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    padding: 3px 0px 1px 7px !important;
    line-height: 1.42857143;
    vertical-align: top;
  border-top: none !important; 
    font-weight: normal;
}
input[type="radio"], input[type="checkbox"] {
    margin: 2px 2px 0px;
    margin-top: 1px \9;
}
.radio, .checkbox {
    position: relative;
    display: block;
    min-height: 17px;
    margin-top: 3px !important;
    margin-bottom: 3px !important;
}
.pado{
    padding-left: 0px !important;
}
.setovehe{
	    width: 81%;
    min-height: 484px;
    overflow: hidden;
    border: 1px solid #efefef;
}

</style> --%>


</head>
<body style="height: 500px !important;" cz-shortcut-listen="true">
	
	

								
								
							
						
						
		
		

<div id="login_main" class="main_layout_content">
	<h2 class="heading" >Send SMS</h2>
	<span id="mandatory_message" class="error">Note:Fields marked with asterisk(*) are required.</span> 
	<div id="login" class="block_div">
		
		<div class="">
   <s:form action="importfileSendsms" theme="simple" name="smsform" id = "smsform" method="post" namespace="/" onsubmit="return importContact()" enctype="multipart/form-data">
							<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4></h4>
								</div>
							</div>
							
							<div class="row ">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<br>
									<div class="row">
									<div class="col-lg-8 col-md-8">
									 <div class="form-group">
									 
									 <div class="form-group">
										<div class="col-lg-8 col-md-8" style="padding-left:0px;padding-right:0px;">
											 <label for="exampleInputFile">Contact List</label>
									    <s:file name="userFile" id="userFile" />
									    <button type="submit" class="btn btn-primary" style="margin-top: 21px;">Import</button>
									    <!-- <p class="help-block">File should be in .XLS, .CSV</p> -->
										</div>
										<div class="col-lg-4 col-md-4" style="padding-left:0px;padding-right:0px;">
										 <!-- <div class="form-group">
											<button type="submit" class="btn btn-primary" style="margin-top: 21px;">Import</button>
										</div> -->
										</div>
									  </div>
									  
									  <div class="form-group">
									  <div class="setovehe">
									  	<table class="table table-hover table-bordered"> 
									  	<thead> 
									  		<tr> 
									  			<th>Name</th> 
									  			<th>Contact Number</th> 
									  		</tr> 
									  	</thead>
									  		<tbody class=""> 
									  			<tr> 
									  				<th>
									  					<div class="checkbox"><label><input type="checkbox" class="mcase" value="<s:property value="mob"/>" onclick="selectallcontact()"  > Select All</label></div>
									  				</th> 
									  				<td></td> 
									  			</tr> 
												<s:iterator value="customerList">
												<%-- <s:iterator value="listContacts"> --%>									  			
									  			<tr> 
									  				<th>
									  					<div class="checkbox"><label><input type="checkbox" class="mcase" value="<s:property value="mob"/>" onclick="addtosms(this.value,this)" /><s:property value="name"/></label></div>
									  				</th> 
									  				<td>
									  					<div class="checkbox"><label class="pado"><s:property value="mob"/></label></div>
									  				</td> 
									  			</tr> 
									  			</s:iterator>
									  			
									  		</tbody> 
									  	</table>
									  	</div>
									  </div>
									  
									  
									    
									  </div>
									  <div class="form-group">
									    <label for="exampleInputEmail1">Message:</label>
									   </div>
									   
									  <div class="form-group">
									  	 <s:textarea cssClass="form-control" id="smstxt" name="smstxt" rows="10"/>
									  </div>
									   
									   <div class="form-group">
									   <label for="exampleInputEmail1">Selected Numbers:</label>
									   </div>
									   
									   <div class="form-group">
									    <s:textarea cssClass="form-control" rows="3" id="numbers" name="numbers"/>
									    </div>
									    
									     <button type="button" class="btn btn-primary pull-right" onclick="sendlotsms()">Send</button>
									    
									   
									
									  <%-- <div class="form-group">
									    <label for="exampleInputEmail1">Selected Numbers</label>
									    <s:textarea cssClass="form-control" rows="3" id="numbers" name="numbers"/>
									  </div>
									  
									  <button type="button" class="btn btn-primary pull-right" onclick="sendlotsms()">Send</button> --%>
									</div>
									<div class="col-lg-4 col-md-4">
									<%-- <div class="form-group">
										<div class="col-lg-8 col-md-8" style="padding-left:0px;padding-right:0px;">
											 <label for="exampleInputFile">Import Contact</label>
									    <s:file name="userFile" id="userFile" />
									    <p class="help-block">File should be in .XLS, .CSV</p>
										</div>
										<div class="col-lg-4 col-md-4" style="padding-left:0px;padding-right:0px;">
										<div class="form-group">
											<button type="submit" class="btn btn-primary" style="margin-top: 21px;">Import</button>
										</div>
										</div>
									  </div> --%>
									  <%-- <div class="form-group">
									  <div class="setovehe">
									  	<table class="table table-hover table-bordered"> 
									  	<thead> 
									  		<tr> 
									  			<th>Name</th> 
									  			<th>Contact Number</th> 
									  		</tr> 
									  	</thead>
									  		<tbody class=""> 
									  			<tr> 
									  				<th>
									  					<div class="checkbox"><label><input type="checkbox" class="mcase" value="<s:property value="phone"/>" onclick="selectallcontact()"  > Select All</label></div>
									  				</th> 
									  				<td></td> 
									  			</tr> 
												<s:iterator value="listContacts">									  			
									  			<tr> 
									  				<th>
									  					<div class="checkbox"><label><input type="checkbox" class="mcase" value="<s:property value="phone"/>" onclick="addtosms(this.value,this)" /><s:property value="name"/></label></div>
									  				</th> 
									  				<td>
									  					<div class="checkbox"><label class="pado"><s:property value="phone"/></label></div>
									  				</td> 
									  			</tr> 
									  			</s:iterator>
									  			
									  		</tbody> 
									  	</table>
									  	</div>
									  </div> --%>
									</div>
									
									</div>
									</div>
									</div>
									</s:form>
								</div>
								
		
		
	</div>
</div>



</body>
</html>