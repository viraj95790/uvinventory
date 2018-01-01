<%@ taglib uri="/struts-tags" prefix="s" %>

<link href="common/css/datePicker1.css" rel="stylesheet" />
<script src="common/js/date.js" type="text/javascript" ></script> <script
	src="common/js/jquery/jquery.datePicker.js" type="text/javascript"></script>
<script src="common/js/CalenderPicture.js" type="text/javascript"></script>
<script type="text/javascript" src="user/js/payrol.js"></script>

<div class="main_layout_content">
	<div id="contact_details" class="block_div">
   
   
   
   
     <s:form action="expenseInvoiceReport" id="payrol_expense" theme="simple">
		<div>
   			<s:textfield cssStyle="width:98px;" name="date" id="date" cssClass="date-pick dp-applied"/>
   			<b>Registration</b>
  		 </div>
			

	<table width="100%" id="payrollexpense"
		style="border: 1px solid #e69623; height: auto; padding: 1em;">
		
		
		    
		
		<col width="20%"/>
		<col width="20%"/>
		<col width="20%"/>
		<col width="20%"/>
		<col width="60%"/>
		
		
		
		
		<tr>
			  <td align="">Vendor<span class="reqd-info"></span></td>
			    <td align="">Invoice<span class="reqd-info"></span></td>
		    <td align="center">Discription<span class="reqd-info"></span></td>
		    <td align="center">Payment<span class="reqd-info"></span></td>
		    
		    </tr>
		
		<tr id="trid1">
		<td id="v0">
		<s:select onchange="setInvoiceAjax(this.value,0)" name="vendor" id="vendor" list="vendorList"
		listKey="id" listValue="vendor" headerKey="0" headerValue="Select Vendor"/>
		</td>
		
		<td id="inv0"><select onchange="setInvoiceNalance(this.value,0)" name="paylist[0].invoice" id="paylist[0].invoice">
			<option value="0">Select Invoice</option>
			</select>
		</td>
		  <td align="center"><s:textfield id="discription0" name="paylist[0].discription" maxlength="25" size="12" cssStyle="text-align:center;"/></td>
		   <td align="center"><s:textfield cssClass="case" onchange="sumofexpencex()" id="debitx0" name="paylist[0].debitx" maxlength="8" size="10" cssStyle="text-align:center;"/></td>
		      <td align="left"><span id="btn0"><input type="button" value="Add" onclick="addRow(0)"></span></td>
		 </tr>
		 
   </table>
   
      <table align="">
		    
		      <tr>
		            <td align="center">Balance<span class="reqd-info"></span></td>
		            <td align="center">TotalPay<span class="reqd-info"></span></td> 
		         
		         </tr>
		         
		         <tr>
		              <td align="center"><s:textfield readonly="true" id="balance" name="balance" cssStyle="text-align:center;"/></td>
		              <td align="center"><s:textfield readonly="true" id="debit" name="debit" cssStyle="text-align:center;"/></td>
		              <td>
		              		<select name="howpaid" id="howpaid" class="form-control">
										<option value="Cash">Cash</option>
										<option value="BACS">BACS</option>
										<option value="Cheque">Cheque</option>
										<option value="C/Card">C/Card</option>
										<option value="D/Card">D/Card</option>
										<option value="D/D">D/D</option>
										<option value="Other">Other</option>
										<option value="S/O">S/O</option>
										
									
									
									</select> 
		              </td>
		              
		              <td align="center"><input type="button" value="submit" onclick="savePayroll()"></td>
		         </tr>
		         
		       
		 </table>
   
   </s:form>
   
   </div>
   </div>
   
