<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <meta charset="UTF-8">
	<title>Create user page</title>
	<style>
        input[type=text]#about {
            width: 100%;
            padding: 40px 20px;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
    <div class="base">
        <h1>Создать пользователя</h1>
        <div class="center">
            <form action="${pageContext.servletContext.contextPath}/manager/users/create" method="POST" commandName="user">
                <label>Имя : </label>
                <input type="text" name="name">
                <label>Фамилия : </label>
                <input type="text" name="surname">
                <label>Дата рождения : </label>
                <input type="text" name="birthday">
                <label>Логин : </label>
                <input type="text" name="login">
                <label>Пароль : </label>
                <input type="text" name="password">
                <label>О себе : </label>
                <input type="text" name="about" id="about">
                <label>Адрес : </label>
                <input type="text" name="address">
                <input type="submit" align="center" value="Создать"/></td>
            </form>
        </div>
    </div>
</body>
</html>