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
	<title>Get all expenses</title>
	
	<script src="http://code.jquery.com/jquery-1.10.0.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
 	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
 	
 	
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/static_resources/css/common.css" />
	
	<script type="text/javascript" src="${contextPath}/static_resources/jqGrid-4.5.2/js/jqGrid_expenses.js"></script>
	<script type="text/javascript" src="${contextPath}/static_resources/jqGrid-4.5.2/js/jquery.jqGrid.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${contextPath}/static_resources/jqGrid-4.5.2/css/ui.jqgrid.css" />
	
	


</head>

<body>

		<div id="gridBlock">
			<div id="gridCont1" class="gridCont">
				<table id="gridTest" class="gTable"></table>
				<div id="pager" class="gTable"></div>
			</div><br clear="all" />
		</div>



</body>
</html>