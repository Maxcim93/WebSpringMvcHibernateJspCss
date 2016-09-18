<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <input type="text" name="name" value="${user.name}">
                <label>Фамилия : </label>
                <input type="text" name="surname" value="${user.surname}">
                <label>Дата рождения : </label>
                <input type="text" name="birthday" value="<fmt:formatDate pattern='dd.MM.yyyy' value='${user.birthday}'/>">
                <label>Логин : </label>
                <input type="text" name="login" value="${user.login}">
                <label>Пароль : </label>
                <input type="text" name="password" value="${user.password}">
                <label>О себе : </label>
                <input type="text" name="about" id="about" value="${user.about}">
                <label>Адрес : </label>
                <input type="text" name="address" value="${user.address}">
                <input type="submit" align="center" value="Создать"/>
            </form>
        </div>
    </div>
</body>
</html>