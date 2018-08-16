<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pageContext.request.contextPath}: информация о страховых организациях</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/test.css"></link>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>

	<h3>информация о страховых организациях</h3>

	<p style="color: red;">${errorString}</p>

	<table border="1" cellpadding="5" cellspacing="1">
		<tr>
			<th>ИНН</th>
			<th>ОГРН</th>
			<th>Наименование</th>
			<th>Адрес</th>
			<th>Изменить</th>
			<th>Удалить</th>
		</tr>
		<c:forEach items="${insuranceOrgsList}" var="insuranceorg">
			<tr>
				<td>${insuranceorg.inn}</td>
				<td>${insuranceorg.ogrn}</td>
				<td>${insuranceorg.name}</td>
				<td>${insuranceorg.address}</td>
				<td><a href="editInsuranceOrg?inn=${insuranceorg.inn}">Изменить</a></td>
				<td><a href="deleteInsuranceOrg?inn=${insuranceorg.inn}">Удалить</a></td>
			</tr>
		</c:forEach>
	</table>

	<p><a href="createInsuranceOrg">Create Insurance Organisation</a>

	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>