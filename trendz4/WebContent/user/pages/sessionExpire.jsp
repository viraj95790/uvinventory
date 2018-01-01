<%@taglib uri="/struts-tags" prefix="s" %>

<div id="global_error" >
	<s:if test="hasActionErrors()"> 
		<s:actionerror/>
	</s:if>
	<s:else>
		Your session has been expired.<s:a href="inputLogin">Please login again</s:a>
	</s:else>
</div>
