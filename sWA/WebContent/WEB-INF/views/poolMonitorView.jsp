<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pageContext.request.contextPath}:JDBC Pool Info</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/test.css"></link>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>

	<h3>JDBC Pool Info Page</h3>
	
	<p>attributes total: ${fn:length(poolAttributes)}</p>

	<table border="1" cellpadding="2" cellspacing="1">
		<tr>
			<th>Attribute</th>
			<th>Value</th>
		</tr>
		<c:forEach items="${poolAttributes}" var="entry">
			<tr>
				<td>${entry.key}</td>
				<td>${entry.value}</td>
			</tr>
		</c:forEach>
	</table>

	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>