<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Add Expense</title>

</head>
<body>
<c:set var="contextPath" value='${pageContext.request.contextPath}' />

<jsp:useBean id="now" class="java.util.Date" />
<div id="form_container">

<h3>Add an expense -- <fmt:formatDate type="time" value="${now}" /></h3>
<!--  
<form:form modelAttribute="expense" name="add_expense_form" method="post" id="addExpenseForm" action="addExpenses">
<table>
<tbody>
<tr>
<td>Amount</td>
<td><input type="text" name="amount" id="amount_formfield" /></td>
</tr>
<tr>
<td>Tags</td>
<td><input type="text" name="tagsString" id="tags_formfield" /></td>
</tr>
<tr>
<td>Description</td>
<td><input type="text" name="description" id="description_formfield" /></td>
</tr>
<tr>
<td>Date</td>
<td><input type="text" name="date" id="date_formfield" /></td>
</tr>
<tr>
<td><input type="submit" name="submit" id="submit_formfield" /></td>
<td></td>
</tr>
<tr>
<td><input type="hidden" name="currency" value="INR" /></td>
<td><a href="${contextPath}/expenseImport">Go to People List</a></td>
</tr>
</tbody>
</table>
</form:form>
</div>
 -->
<hr/>

<div id="fileUploadContainer">
	<form:form method="post" enctype="multipart/form-data" action="uploadFile" modelAttribute="uploadForm" >
		<table>
		<tr>
			<td><input type="file" name="file" /></td>
			<td><input type="submit" value="Upload" /></td>
		</tr>
		</table>
	</form:form>
</div>
</body>
</html>