<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Особистий кабінет | Віртуальна Залізнична Каса</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<div style="text-align: right">
		<a class="link-cancel" href="/RailwayOfficeSystem/logout">Вийти</a>
	</div>
	<div id="wrapper">
		<h3>З поверненням, ${sessionScope.user.name}!</h3>
		<form action="/RailwayOfficeSystem/find-train" method="post">
			<table style="margin: auto; text-align: left">
				<tr>
					<td>Станція відправлення:</td>
					<td><input name="departureStation" type="text" size="35"
						maxlength="70" required /></td>
				</tr>
				<tr>
					<td>Станція прибуття:</td>
					<td><input name="destinationStation" type="text" size="35"
						maxlength="70" required /></td>
				</tr>
				<tr>
					<td>Дата відправлення:</td>
					<td><input name="departureDate" type="date" required /></td>
				</tr>
				<tr>
					<td>Час відправлення:<br />(наприклад, 19:35)</td>
					<td><input name="departureTime" type="time" required /></td>
				</tr>
			</table>
			<input type="submit" class="button-accept" name="findTrain" value="Пошук" />
		</form>
	</div>
</body>
</html>