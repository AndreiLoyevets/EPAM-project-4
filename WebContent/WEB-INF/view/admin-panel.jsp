<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Панель адміністратора | Віртуальна Залізнична Каса</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
<div style="text-align: right"><a class="link-cancel" href="/RailwayOfficeSystem/logout">Вийти</a></div>
<div id="admin-panel">
<h3>З поверненням, Адміністратор!</h3>
<c:choose>
<c:when test="${not empty unconfirmedUsers}">
<form action="/RailwayOfficeSystem/admin-panel" method="post">
<table style="margin: auto">
<tr class="odd">
<th>№</th>
<th>Email</th>
<th>Прізвище</th>
<th>Ім'я</th>
<th>Телефон</th>
<th>Виконати дію</th>
</tr>
<c:forEach var="unconfUser" varStatus="loopStatus" items="${unconfirmedUsers}">
<tr class="${loopStatus.index % 2 == 0 ? 'even' : 'odd'}">
<td><c:out value="${unconfUser.id}"/></td>
<td><c:out value="${unconfUser.email}"/></td>
<td><c:out value="${unconfUser.surname}"/></td>
<td><c:out value="${unconfUser.name}"/></td>
<td><c:out value="${unconfUser.phone}"/></td>
<td>
<select name="select${unconfUser.id}">
<option value="ignore">Ігнорувати</option>
<option value="activate">Активувати</option>
  <option value="delete">Видалити</option>
</select>
</td>
</tr>
</c:forEach>
</table>
<input type="submit" class="button-accept" name="updateUsers" value="Підтвердити" />
</form>
</c:when>
<c:otherwise>
<c:out value="Неактивованих користувачів не знайдено"></c:out>
</c:otherwise>
</c:choose>
</div>
</body>
</html>