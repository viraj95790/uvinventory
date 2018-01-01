<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%@ taglib uri="/struts-tags"  prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header Page</title>
        <style>
            #headertable{
                            margin-top: 0px;
                            width:100%;
                        }
            #td1   {
                            
                            font-weight: bold;
                            color:white;
                            vertical-align:middle;
                        }
                       
        </style>
    </head>
    <body>
        <table id="headertable" cellspacing="0" align="center" cellpadding="0"   border="0" style="width: 100%;">
            <tr >
                
	<s:if test="(#session.LOGIN_INFO != null)&&(#session.LOGGED_IN)">
		<%-- <font style="font-weight: bold; font-size: 14px; float: right;"> 
				<span>Welcome </span><span id="username"><s:property value="#session.LOGIN_INFO.firstname"/></span>
		</font> --%>
	</s:if>
            </tr>
        </table>
        <table width="100%" style="width: 100%;">
        <tr>
        <td align="center">
          	<font style="font-size: 20px; font-weight: bold">Inventory Management</font>
        </td>
        </tr>
        </table>
    </body>
</html>