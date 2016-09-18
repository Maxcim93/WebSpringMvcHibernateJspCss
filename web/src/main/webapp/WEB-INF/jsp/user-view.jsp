<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
        <meta charset="UTF-8">
   	    <title>View user page</title>
        <style>
            td.name {
                background-color: #7CF886;
                color: black;
                font-style: normal;
            }
        </style>
    </head>
    <body>
        <div class="base">
            <h1>Информация о пользователе</h1>
                <div class="center">
                    <table>
                        <tr>
                            <td class="name">Имя :</td>
                            <td>"${user.name}"<td>
                        </tr>
                        <tr>
                            <td class="name">Фамилия : </td>
                            <td>"${user.surname}"</td>
                        </tr>
                        <tr>
                            <td class="name">Дата рождения : </td>
                            <td><fmt:formatDate pattern='dd.MM.yyyy' value='${user.birthday}'/></td>
                        </tr>
                        <tr>
                            <td class="name">Логин : </td>
                            <td>"${user.login}"</td>
                        </tr>
                        <tr>
                            <td class="name">Пароль : </td>
                            <td>"${user.password}"</td>
                        </tr>
                        <tr>
                            <td class="name">О себе : </td>
                            <td>"${user.about}"</td>
                        </tr>
                        <tr>
                            <td class="name">Адрес : </td>
                            <td>"${user.address}"</td>
                        </tr>
                    </table>
                </div>
        </div>
    </body>
</html>
