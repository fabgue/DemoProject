<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">
<title>JQuery JTable integration with Spring MVC3</title>
<c:set var="baseURL" value="${pageContext.servletContext.contextPath}" />

<%-- JQuery --%>
<script src="${baseURL}/resources/scripts/jquery-1.6.4.min.js"
	type="text/javascript"></script>
<script
	src="${baseURL}/resources/scripts/jquery-ui-1.8.16.custom.min.js"
	type="text/javascript"></script>

<%-- JTable --%>
<link
	href="${baseURL}/resources/scripts/jtable/themes/metro/blue/jtable.css"
	rel="stylesheet" type="text/css" />
<script src="${baseURL}/resources/scripts/jtable/jquery.jtable.js"
	type="text/javascript"></script>

<link href="${baseURL}/resources/css/jquery-ui-1.8.16.custom.css"
	rel="stylesheet" type="text/css" />

<%-- customized javascript code to manage JTable --%>
<script src="${baseURL}/resources/scripts/expenseJTable.js"
	type="text/javascript"></script>
</head>
<body>
	<div>
		<div id="ExpenseTableContainer" style="width: 99%;"></div>
		<div id="ExpenseCategotyTableContainer" style="width: 99%;"></div>
	</div>
</body>
</html>