<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pageContext.request.contextPath}: Информация о страховых организациях</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/test.css"></link>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>

	<h3>Readme</h3>

	Приложение представляющее из себя веб-форму заполнения полей справочника "информация о страховых организациях": ИНН, ОГРН, Полное наименование, адрес. 
	<br>
	<br>
	<b></b>
	<ul>
		<li>Исходный код управляется Maven</li>
		<li>Использованы jsp,servlet &amp; Jdbc</li>
		<li>Tomcat jdbc connection pool, с просмотром параметров пула из приложения</li>
		<li>Встроенная in-memory база данных H2 со ссылкой на  SQL консоль из приложения </li>
		<li>Список организаций</li>
		<li>Создание новой организации</li>
		<li>Изменение и Удаление организации</li>
		<li>Валидация значений минимальная, по факту не реализована</li>
	</ul>

	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>