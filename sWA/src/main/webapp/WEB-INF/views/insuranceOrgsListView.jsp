<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pageContext.request.contextPath}:Информацияостраховых
	организациях</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/test.css"></link>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>

	<h3></h3>

	<p style="color: red;">${errorString}</p>

	<form method="GET">
		<table border="1" cellpadding="5" cellspacing="1">
			<tr>
				<td><input type="text" style="width:98%;" name="search_inn" value="${search_inn}" /></td>
				<td><input type="text" style="width:98%;" name="search_ogrn" value="${search_ogrn}" /></td>
				<td><input type="text" style="width:98%;" name="search_name" value="${search_name}" /></td>
				<td><input type="text" style="width:98%;" name="search_address" value="${search_address}" /></td>
				<td><button type="submit">Поиск</button></td>
				<td><a href="${pageContext.request.contextPath}/insuranceOrgsList"><button type="button">Очистить</button></a></td>
			</tr>

			<tr>
				<th>ИНН</th>
				<th>ОГРН</th>
				<th>Наименование</th>
				<th>Адрес</th>
				<th colspan="2"></th>
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
	</form>

	<p>
		<a href="createInsuranceOrg"><button type="button">Добавить страховую организацию</button></a>

		<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>