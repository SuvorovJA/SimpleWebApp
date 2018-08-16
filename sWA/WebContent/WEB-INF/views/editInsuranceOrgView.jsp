<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pageContext.request.contextPath}: Edit Insurance Organisation</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/test.css"></link>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>

	<h3>Edit Insurance Organisation</h3>

	<p style="color: red;">${errorString}</p>

	<c:if test="${not empty insuranceOrg}">
		<form method="POST"
			action="${pageContext.request.contextPath}/editInsuranceOrg">
			<input type="hidden" name="inn" value="${insuranceOrg.inn}" />
			<table border="0">
				<tr>
					<td>ИНН</td>
					<td style="color: red;">${insuranceOrg.inn}</td>
				</tr>
				<tr>
					<td>ОГРН</td>
					<td><input type="text" name="ogrn" value="${insuranceOrg.ogrn}" /></td>
				</tr>
				
				<tr>
					<td>Наименование</td>
					<td><input type="text" name="name" value="${insuranceOrg.name}" /></td>
				</tr>
				<tr>
					<td>Адрес</td>
					<td><input type="text" name="address" value="${insuranceOrg.address}" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Submit" /> <a
						href="${pageContext.request.contextPath}/insuranceOrgsList">Cancel</a></td>
				</tr>
			</table>
		</form>
	</c:if>

	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>