<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Результати пошуку | Віртуальна Залізнична Каса</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<div style="text-align: right">
		<a class="link-cancel" href="/RailwayOfficeSystem/logout">Вийти</a>
	</div>
	<div id="trains-result">
	<c:choose>
	<c:when test="${empty routes}">
		<h3>Вибачте, за даними параметрами потягів не знайдено</h3>
		</c:when>
		<c:otherwise>
		<h3>Пошук поїздів на ${departureDate}</h3>
		<table style="margin: auto">
<tr class="odd">
<th>№ поїзда</th>
<th>Звідки</th>
<th>Куди</th>
<th>Відправлення</th>
<th>Прибуття</th>
<th>Люкс</th>
<th>Купе</th>
<th>Плацкарт</th>
</tr>
<c:forEach var="route" varStatus="loopStatus" items="${routes}">
<tr class="${loopStatus.index % 2 == 0 ? 'even' : 'odd'}">
<td><c:out value="${route.id}"/></td>
<td><c:out value="${route.departureStation}"/></td>
<td><c:out value="${route.destinationStation}"/></td>
<td><c:out value="${route.departureTime}"/></td>
<td><c:out value="${route.destinationTime}"/></td>
</tr>
</c:forEach>
</table>
		
		</c:otherwise>
		</c:choose>
	</div>
</body>
</html>