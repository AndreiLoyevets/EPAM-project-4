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
<h1>Реєстрація нового користувача</h1>
<form action="/RailwayOfficeSystem/check">
            <table style="margin: auto">
               <tr>
                  <td>E-mail:</td>
                  <td><input name="email" type="email" size="35" required/></td>
               </tr>
               <tr>
               <td>Пароль:</td>
                  <td><input name="password" type="password" size="35" maxlength="35" required/></td>
               </tr>
               <tr>
               <td>Підтвердіть пароль:</td>
                  <td><input name="passwordConf" type="password" size="35" maxlength="35" required/></td>
               </tr>
               <tr>
               <td>Прізвище:</td>
                  <td><input name="name" type="text" size="35" maxlength="35" required/></td>
               </tr>
               <tr>
               <td>Ім'я:</td>
                  <td><input name="surname" type="text" size="35" maxlength="35" required/></td>
               </tr>
               <tr>
               <td>Телефон:(наприклад, 380501234567)</td>
                  <td><input name="phone" type="tel" pattern='\d{12}' size="12" maxlength="12" required/></td>
               </tr>
            </table>
            <table style="margin: auto">
               <tr>
                  <td><input type="submit" class="button-login" value="Підтвердити"/></td>
                  <td><input type="reset" class="button-cancel" value="Відмінити"/></td>
               </tr>
            </table>
            </form>
</div>
</body>
</html>