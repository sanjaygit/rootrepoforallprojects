<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="contextPath" value='${pageContext.request.contextPath}' />

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>Add Expense</title>
	<script src="http://code.jquery.com/jquery-1.10.0.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
 	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>	
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/static_resources/css/common.css" />
	
	<!-- For autocomplete -->
		<link rel="stylesheet" href="${contextPath}/static_resources/fbautocomplete/test.css" type="text/css" media="screen" title="Test Stylesheet" charset="utf-8" />    
<%-- 	    <script src="${contextPath}/static_resources/fbautocomplete/mootools-beta-1.2b1.js" type="text/javascript" charset="utf-8"></script> --%>
<!-- 	    <script src="textboxlist.js" type="text/javascript" charset="utf-8"></script> -->
<%-- 	    <script src="${contextPath}/static_resources/fbautocomplete/textboxlist.compressed.js" type="text/javascript" charset="utf-8"></script> --%>
<%-- 	    <script src="${contextPath}/static_resources/fbautocomplete/test.js" type="text/javascript" charset="utf-8"></script>	 --%>
	<!-- end autocomplete -->
	
	<script type="javascript">
		function doAjaxPost() {
			alert('In ajaxpost!');
		}		
	</script>
	 <script>
			$(function() {
			$( "#expenseDate_formfield" ).datepicker();
			});
	</script>

	<script>
	
	function split(val) {
	    return val.split(/,\s*/);
	}
	function extractLast(term) {
    	return split(term).pop();
	}

	$(document).ready(function() {
		
			$( "#tags_formfield").autocomplete({
	        source: function (request, response) {
	            $.getJSON("${pageContext. request. contextPath}/expenses/getAllTagNamesJSON", {
	                term: extractLast(request.term)
	            }, response);
	        },
	        search: function () {
	            // custom minLength
	            var term = extractLast(this.value);
	            if (term.length < 1) {
	                return false;
	            }
	        },
	        focus: function () {
	            // prevent value inserted on focus
	            return false;
	        },
	        select: function (event, ui) {
	            var terms = split(this.value);
	            // remove the current input
	            terms.pop();
	            // add the selected item
	            terms.push(ui.item.value);
	            // add placeholder to get the comma-and-space at the end
	            terms.push("");
	            this.value = terms.join(", ");
	            return false;
	        }
	    });
    
	})
	</script>
</head>
<body>

<jsp:useBean id="now" class="java.util.Date" />
<div id="form_container">

<h3>Add an expense -- <fmt:formatDate type="time" value="${now}" /></h3>

<form:form modelAttribute="expense" name="add_expense_form" method="post" id="addExpenseForm" action="addExpenses">
<table>
<tbody>
<tr>
<td>Amount</td>
<td><form:input path="amount" type="text" name="amount" id="amount_formfield" /></td>
</tr>
<tr>
<td>Tags</td>
<td><form:input path="tags" type="text" name="tags" id="tags_formfield" /></td>
</tr>
<tr>
<td>Description</td>
<td>
	<form:input path="description" type="text" name="description" id="description_formfield" />
	<span><form:errors path="description"/></span>
</td>
</tr>
<tr>
<td>Date</td>
<td><form:input path="expenseDate" type="text" name="expenseDate" id="expenseDate_formfield" /></td>
</tr>
<tr>
<td><input type="submit" name="submit" id="submit_formfield" /><input type="button" name="doAdjaxPost" onclick="doAjaxPost()" /></td>
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