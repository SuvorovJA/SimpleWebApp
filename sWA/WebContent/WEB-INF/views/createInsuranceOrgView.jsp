<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pageContext.request.contextPath}: Create Insurance Organisation</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/test.css"></link>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>

	<h3>Create Insurance Organisation</h3>

	<p style="color: red;">${errorString}</p>

	<form method="POST"
		action="${pageContext.request.contextPath}/createInsuranceOrg">
		<table border="0">
			<tr>
				<td>ИНН</td>
				<td><input type="text" name="inn" value="${insuranceorg.inn}" /></td>
			</tr>
			<tr>
				<td>ОГРН</td>
				<td><input type="text" name="ogrn" value="${insuranceorg.ogrn}" /></td>
			</tr>
			<tr>
				<td>Наименование</td>
				<td><input type="text" name="name" value="${insuranceorg.name}" /></td>
			</tr>
			<tr>
				<td>Адрес</td>
				<td><input type="text" name="address" value="${insuranceorg.address}" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" /> <a
					href="insuranceOrgsList">Cancel</a></td>
			</tr>
		</table>
	</form>

	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>