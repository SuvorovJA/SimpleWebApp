<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pageContext.request.contextPath}: Edit Product</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/test.css"></link>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>

	<h3>Edit Product</h3>

	<p style="color: red;">${errorString}</p>

	<c:if test="${not empty product}">
		<form method="POST"
			action="${pageContext.request.contextPath}/editProduct">
			<input type="hidden" name="code" value="${product.code}" />
			<table border="0">
				<tr>
					<td>Code</td>
					<td style="color: red;">${product.code}</td>
				</tr>
				<tr>
					<td>Name</td>
					<td><input type="text" name="name" value="${product.name}" /></td>
				</tr>
				<tr>
					<td>Price</td>
					<td><input type="text" name="price" value="${product.price}" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Submit" /> <a
						href="${pageContext.request.contextPath}/productList">Cancel</a></td>
				</tr>
			</table>
		</form>
	</c:if>

	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>