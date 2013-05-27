<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<html>


<head>
    <title>Login</title>

<link rel="stylesheet" type="text/css" href="<c:url value="/static_resources/css/common.css" />" />

<script src="//ajax.googleapis.com/ajax/libs/dojo/1.8.3/dojo/dojo.js" type="text/javascript" djConfig="parseOnLoad:true"></script>
 <script type="text/javascript">
        dojo.require("dojo.parser");
        dojo.require("dijit.form.ValidationTextBox");
        dojo.require("dijit.form.Button");
    </script>
</head>



<body>
 
<h2>Please login</h2>
 
<form:form method="post" action="doLogin" commandName="credentials">
 
    <table>
					<tr>
						<td><label path="j_username">UserName<span
									class="small">(Employee number)</span>
							</label>
						</td>
						<td><input name="j_username" path="j_username" type="text" 
								id="name" required="required"								
								class="loginFormUserName" />
						</td>
					</tr>
					<tr>
						<td class="first"><label path="j_password">Password<span
									class="small">&nbsp;</span>
							</label>
						</td>
						<td><input name="j_password" type="password" path="j_password"
								id="password" required="required"
								missingMessage="Please enter password" />
						</td>
					</tr>
					<tr>
						<td class="first">&nbsp;</td>
						<td><button data-dojo-type="dijit.form.Button" type="submit">Submit!!!</button></td>
					</tr>
					
					<tr>
</table> 
</form:form>
    
</body>
</html>