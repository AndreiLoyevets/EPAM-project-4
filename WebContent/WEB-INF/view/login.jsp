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
<div style="text-align: center">
<h1>Ласкаво просимо до<br/>Віртуальної Залізничної Каси!</h1>
<form action="/RailwayOfficeSystem/check">
            <br/><br/>E-mail:&nbsp;&nbsp;&nbsp;
            <input name="email" type="email" size="35" required/><br/>
            Пароль:
            <input name="password" type="password" size="35" maxlength="35" required/>
            <p></p>
            <table style="margin: auto">
               <tr>
                  <td><input type="submit" class="button-login" value="Користувач"/></td>
                  <td><input type="submit" class="button-login" value="Адміністратор"/></td>
               </tr>
            </table>
         </form>
		 <form action="/RailwayOfficeSystem/registration">
		    <input type="submit" class="button-register" value="Зареєструватись"/>
		 </form>
</div>
</body>
</html>