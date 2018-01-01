<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        	<link href="common/css/Style.css" rel="stylesheet" type="text/css" />
        	
			 
			 <script src="common/js/jquery/jquery-1.4.2.js" type="text/javascript"></script>
			 <script src="common/js/validation-common.js" type="text/javascript"></script>
			 <script src="common/js/jquery/jquery.validate.js" type="text/javascript"></script>
			 <script src="common/js/jquery/jquery.timers-1.2.js" type="text/javascript"></script>
			 <script src="common/js/jquery/jquery.tools.min.js" type="text/javascript"></script>
			 <script src="common/js/jquery/additional-validation-methods.js" type="text/javascript"></script>
			 
			 <script type="text/javascript" src="user/js/fullscreen.js"></script>
			        	
            <title><tiles:insertAttribute name="title" ignore="true" /></title>
    </head>
    <body>
    <div>
        <table  style="height: auto; width: 100%" border="0" align="center" cellpadding="0" cellspacing="0">

            <tr>
                <td>
                    <tiles:insertAttribute name="header" />
                </td>
            </tr>
            <tr><td style="height: 2px;"></td></tr>
            <tr>
                <td>
                    <tiles:insertAttribute name="menu" />
                </td>
            </tr>
            <tr>
               
                <td>
                    <tiles:insertAttribute name="body" />
                </td>
            </tr>
            <tr>
                <td>
                    <tiles:insertAttribute name="footer" />
                </td>
            </tr>
        </table>
       </div>
    </body>
</html>
