<%@ taglib prefix="s" uri="/struts-tags" %>   
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
   <link rel="stylesheet" href="chosen/chosen.css">
</head>
<body>


<s:form action="" theme="simple">
			<div class="col-lg-12 col-md-12 col-sm-12 searchtab">
							<div class="col-lg-1 col-md-1 col-sm-2 col-xs-3">
								<label for="inputEmail3" class="text-left">Search :</label>
							</div>
							<div class="col-lg-5 col-md-5 col-sm-8 col-xs-3">
							<div class="form-group">
								<%-- <s:select list="pharmapatientlist" theme="simple" name="epname" onchange="selectExternalPatient(this.value)" cssClass="form-control chosen-select" id="epname" listKey="id" listValue="allinfo" headerKey="0" headerValue="Select Existing Patient">
								</s:select>  --%>
								<input type="text" name="inhousepid" id="inhousepid" class="form-control" onclick="showInhousePatientPopup()"  placeholder="Search Patient" style="width: 100%;"> 	
							</div>
							</div>
							
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" style="text-align: left;">
								<input type="button" value="Refresh" class="btn btn-primary"  onclick="clearAll()" />&nbsp;&nbsp;&nbsp;
								<a class="btn btn-success" href="#" onclick="openBlankPopup('salepriscPharmacy')" title="New Sale" >New Sale</a>
								
							</div>
							
							
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" style="text-align: right;">
								<span><i class="fa fa-square" aria-hidden="true" style="color: #e05d6f;"></i> Narcotics &nbsp; | &nbsp; <i class="fa fa-square" aria-hidden="true" style="color: #e69522;"></i> H1</span>
							</div>
							
							</div>
							
							
								<div class="col-lg-12 col-md-12 col-sm-12" style="padding:0px;padding-top: 8px;">
								<div class="col-lg-1 col-md-1 col-sm-2 col-xs-4">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">B.NO</label>
										    <p>-</p>
										    <input type="hidden" id="extpid" name="extpid" />
										    <input type="hidden" id="tpid" value="0" name="tpid" />
								  		</div>
									</div>
									<div class="col-lg-3 col-md-3 col-sm-2 col-xs-4">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">PATIENT NAME</label> 
										    <s:textfield readonly="true" onchange="updatePharmaClientInfo(this.value,'fullname')" cssClass="form-control" name="fullname" id="fullname" cssStyle="text-transform: uppercase;"/>
								  		</div>
								  		
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">MOBILE</label>
										    <s:textfield readonly="true" onchange="updatePharmaClientInfo(this.value,'mobile')" cssClass="form-control" name="mobno" id="mobile" maxlength="10"/>
								  		</div>
									</div>
									<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4">
	                                		<div class="form-group">
											    <label for="inputEmail3" class="text-left">ADDRESS</label>
											    <s:textfield readonly="true" onchange="updatePharmaClientInfo(this.value,'address')" cssClass="form-control" name="wardname" id="wardname" cssStyle="text-transform: uppercase;"/>
									  		</div>
									</div>
									
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">DR. NAME</label>
										    	<!--<select class="form-control chosen hidden">
										    		<option>Select Doctor</option>
										    		<option>Dr.Abc</option>
										    		<option>Dr.Abc</option>
										    		<option>Dr.Abc</option>
										    	</select>
										      --><s:textfield readonly="true" onchange="updatePharmaClientInfo(this.value,'reference')" cssClass="form-control" name="practitionerName" id="doctor"  cssStyle="text-transform: uppercase;"/>
								  		</div>
									</div>
									<div class="col-lg-1 col-md-1 col-sm-1 col-xs-4">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">DATE</label>
										     <s:if test="back_date_access==1">
										    	<s:textfield cssClass="form-control" id="date" name="dateTime" /> 
										     </s:if> 
										     <s:else>
										    		 <s:textfield readonly="true" cssClass="form-control" id="date1" name="dateTime" />
										     </s:else>
										    
								  		</div>
									</div>
							</div>
							
							
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
									
									<div class="col-lg-9 col-sm-12 col-md-9 col-xs-12 rightset">
									
										
									
									<s:hidden name="paymode" id="paymode"></s:hidden>
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 padsets" style="background-color: #dff0d8;padding: 14px 0px 0px 11px;padding-bottom: 0px;">
								         <div class="form-inline disinset">
								         <div class="form-group hidden-xs" style="">
								                <input type="text" id="barcode" onchange="addBarcodeRequest(this.value)" class="form-control" placeholder="Barcode Here" autofocus="autofocus" style="width:100%;" />
								          </div>
								          <div class="form-group hidden-xs">
								           <p style="font-size: 13px;margin: 0px 0px 2px;">Medicine :</p> 
								          </div>
								          
								          <div class="form-group set100">
								            <select class="form-control chosen-select" >
								            	<option value="0">Select Product</option>
								            	<option value="0">jean black</option>
								            	<option value="0">jeans red</option>
								            	<option value="0">jeans blue</option>
								            	<option value="0">jeans blue</option>
								            	<option value="0">jeans blue</option>
								            </select>
								          </div>
								          <div class="form-group qtyset" style="width: 5%;">
								                <input type="text" id="qty" class="form-control" placeholder="Qty" style="width:100%;"/>
								          </div>
								          <a href="#" onclick="addnewRow('mytable')" title="Add New" class="plusbtnse"><i class="fa fa-plus-circle" aria-hidden="true" style="font-size: 25px;padding-top: -8px;">+</i></a>
								         
								         </div>
         								</div>
         								
         								<div class="minhesigh">
										<table id="results" cellpadding="0" cellspacing="0" class="my-table"  style="width:100%;">
                                <thead>
                                    <tr class="tableback"> 
                                    	<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Gender</th>
                                    	<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Gender</th>
                                    	<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Gender</th>
                                    	<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Gender</th>
                                    	<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Gender</th>
                                    	<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Gender</th>
                                       	
                                    </tr>
                                    </thead>
                                    </table>
                                
                                
								
								
                           
                            
									</div>
						</div>
						
							<div class="col-lg-3 col-sm-12 col-md-3 col-xs-12">
										<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;border-bottom: 1px solid #ddd;" id="baldiv">
											<h3 style="color:#d9534f;">Balance : <span style="float: right;" >Rs.<span id="rebalance">00.00</span> <input type="hidden" value="0" id="prebalance" /> </span></h3>
										</div>
										<div class="col-lg-12 col-md-12 col-xs-12" style="padding-right: 3px;text-align: right;padding:0px;">
			                            	<!--<h4><a href="#" title="clear Balance" onclick="clearbaldb()" class="btn btn-success btn-sm pull-left">clear balance</a>Sub Total : Rs.<span id="subtotal">00.00</span></h4>
			                            	
			                            	--><h4 style="color: forestgreen;"><a href="#" title="clear Balance" onclick="clearBalanceTemp()" class="btn btn-success btn-sm pull-left">clear balance</a>Sub Total : Rs.<span id="subtotal">00.00</span></h4>
			                            	
			                            		<h4 style="color: #868686;">Discount <select id="distype" onchange="perorrs()" class="form-control" style="width: 20%;display:-webkit-inline-box;"><option value="1">Rs.</option><option value="0">%</option></select> : <input type="text" id="discsmt" onchange="calDiscount(this.value)" class="form-control" value="0" style="width: 20%;display:-webkit-inline-box;"><br><small>Rs.<label id="tdisc">00.00</label></small></h4>
			                            	
			                            	<h4 class="hidden" style="color: #868686;">Refund: Rs.00.00</h4>
			                            	<h4 class="hidden">Vat : Rs.<label id="vat">00.00</label></h4> <input type="hidden" id="tvat" name="vat"/> <input type="hidden" id="tdiscount" name="discount" />
			                            	<h4 class="">CGST : Rs.<label id="cgst">00.00</label></h4> <input type="hidden" id="tcgst" name="cgst"/> 
			                            	<h4 class="">SGST : Rs.<label id="sgst">00.00</label></h4> <input type="hidden" id="tsgst" name="sgst"/> 
			                            	<h4 style="font-weight: bold;color:green;">Net Total : Rs.<span id="total">00.00</span> <input type="hidden" name="total" id="ttotal"/> </h4> 
			                            	<h4 class="hidden" style="color: #868686;">Balance : <input type="text" name="balance" id="balance" class="form-control" value="0" style="width: 20%;"></h4>
			                            	<h4 class=""><small>Total with balance: Rs.<span id="baltotal"></span></small></h4>
	                            		</div>
	                            		<div class="col-lg-12 col-md-12 col-xs-12 hidden-xs returfed">
			                            <div class="form-inline">
											  <div class="form-group" style="width: 49%">
											    <label for="exampleInputName2">Received Rs.</label>
											    <s:textfield onkeyup="getRemainAmt(this.value)" id="received" name="payamt" cssClass="form-control" cssStyle="width: 43%;background-color: rgba(165, 42, 42, 0.07);" />
											  </div>
											  <div class="form-group" style="width: 49%;">
											    <label for="exampleInputEmail2">Return Rs.</label>
											    <input id="returnamt" type="text" class="form-control" style="width: 45%;background-color: rgba(165, 42, 42, 0.07);">
											  </div>
											</div>
											
			                             </div>
			                             <div class="col-lg-12 col-md-12 col-xs-12 text-right" style="padding:0px;">
			                             <div class="form-group">
			                             	<s:textarea theme="simple" cssClass="form-control"  name="notes" rows="3" placeholder="Write Note" cssStyle="height: 50px !important;"/>
			                             </div>
	                            
	                            
	                            <div class="form-group" >
	                            	<select class="form-control" style="width: 100%;"  id="paytype" onchange="setPaymode(this.value)" style="background-color: rgba(255, 140, 0, 0.36);">
	                            	<option value="Cash">CASH</option>
	                            	<option value="Card">CARD</option>
	                            	<option value="Cheque">CHEQUE</option>
	                            	<option value="NEFT">NEFT/RTGS</option>
	                            	<option value="Credit">CREDIT</option>
	                            	<option value="Estimate">ESTIMATE</option>
	                            	<option value="Hospital">HOSPITAL</option>
	                            </select>
	                            </div>
	                             <div class="form-group">
	                            	<input type="text" id="card" name="card" placeholder="Enter 4 Digit No" class="form-control hidden"  />
	                            </div>
	                             <div class="form-group">
	                            	<input type="number" id="chequeno" name="chequeno" placeholder="Enter Cheque No" class="form-control hidden"  />
	                            </div>
	                             <div class="form-group">
	                            	<input type="text" id="neftid" name="neftid" placeholder="Enter Transaction ID" class="form-control hidden"  />
	                            </div>
	                            
	                            <div class="form-group">
	                            	<a type="button" class="btn btn-primary" onclick="newsale()">Save & Print</a>
	                            </div>
	                            
			                             <span class="hidden" style="float: left;font-size: 15px;color: darkblue;">Item : 0</span>
                            	<a type="button" class="btn btn-primary hidden"  title="Online Payment" onclick="newsale('Online')">Online</a>
                            	<!--<a type="button" class="btn btn-primary"  title="Credit" onclick="credit()">Credit</a>-->
                            	
                            	<a type="button" class="btn btn-info hidden"  title="Estimate" onclick="newsale('Estimate')" style="float: left;">Estimate</a>
	                            <a type="button" class="btn btn-primary hidden"  title="Cash Payment" onclick="newsale('Cash')">Save & Print</a>
	                            <a type="button" class="btn btn-warning hidden"  title="Card Payment" onclick="showCard()">Card Payment</a>
	                            
	                            
                            </div>
                           <div class="col-lg-12 col-xs-12 col-md-12 text-right" style="margin-top: 15px;padding:0px;">
                            <div id="divcard" class="hidden">
                            	<div class="form-inline">
								  <div class="form-group">
								    <label class="sr-only" for="exampleInputEmail3">Email address</label>
								    
								  </div>
								  
								  <a type="button" class="btn btn-warning"  onclick="newsale('Card')" style="margin-top: -15px;"> Save & Print</a>
								</div>
                            	 <br>
                            	
                            	
                            </div>
                            </div>
										
									</div>
						
						</div>
						</s:form>

<!-- <div class="container">
  <h2>Modal Example</h2>
  Trigger the modal with a button
  <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>

  Modal
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      Modal content
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modal Header</h4>
        </div>
        <div class="modal-body">
          <p>Some text in the modal.</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
</div>
 -->
</body>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"
    type="text/javascript"></script>
   <script src="chosen/chosen.jquery.js" type="text/javascript"></script>
   <script src="chosen/docsupport/prism.js" type="text/javascript"
    charset="utf-8"></script>
   <script type="text/javascript">
    var config = {
     '.chosen-select' : {},
     '.chosen-select-deselect' : {
      allow_single_deselect : true
     },
     '.chosen-select-no-single' : {
      disable_search_threshold : 10
     },
     '.chosen-select-no-results' : {
      no_results_text : 'Oops, nothing found!'
     },
     '.chosen-select-width' : {
      width : "95%"
     }
    }
    for ( var selector in config) {
     $(selector).chosen(config[selector]);
    }
   </script>
