<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Вхід | Віртуальна Залізнична Каса</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<div id="wrapper">
		<h1>
			Ласкаво просимо до<br />Віртуальної Залізничної Каси!
		</h1>
		<h3><font color="red">
		   <c:if test="${not empty notActivated and notActivated eq 'true'}">
		      Обліковий запис ще не активовано
		   </c:if>
		   <c:if test="${not empty notExists and notExists eq 'true'}">
		      Обліковий запис не знайдено
		   </c:if>
		   </font>
		</h3>
		<form action="/RailwayOfficeSystem/check-login" method="post">
			<table style="margin: auto">
				<tr>
					<td style="text-align: left">E-mail:</td>
					<td><input name="email" type="email" size="35" required /></td>
				</tr>
				<tr>
					<td style="text-align: left">Пароль:</td>
					<td><input name="password" type="password" size="35"
						maxlength="35" required /></td>
				</tr>
			</table>
			<table style="margin: auto">
				<tr>
					<td><input type="submit" class="button-accept"
					    name="userLogin"
						value="Користувач" /></td>
					<td><input type="submit" class="button-accept"
						name="adminLogin"
						value="Адміністратор" /></td>
				</tr>
			</table>
		</form>
		<form action="/RailwayOfficeSystem/registration" method="get">
			<input type="submit" class="button-register"
			name="register" value="Зареєструватись" />
		</form>
	</div>
</body>
</html>