<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pageContext.request.contextPath}: Insurance Organisations List</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/test.css"></link>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>

	<h3>Insurance Organisations List</h3>

	<p style="color: red;">${errorString}</p>

	<table border="1" cellpadding="5" cellspacing="1">
		<tr>
			<th>Code</th>
			<th>Name</th>
			<th>Price</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<c:forEach items="${insuranceOrgsList}" var="insuranceorg">
			<tr>
				<td>${insuranceorg.code}</td>
				<td>${insuranceorg.name}</td>
				<td>${insuranceorg.price}</td>
				<td><a href="editInsuranceOrg?code=${insuranceorg.code}">Edit</a></td>
				<td><a href="deleteInsuranceOrg?code=${insuranceorg.code}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>

	<p><a href="createInsuranceOrg">Create Insurance Organisation</a>

	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>