<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
   <head>
       <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
       <meta charset="UTF-8">
       <title>View users page</title>
   </head>
   <body>
       <div class="base">
            <h1>Пользователи</h1>
            <div class="center">
                <table>
                    <th>ID</th>
                    <th>Имя</th>
                    <th>Фамилия</th>
                    <th>Операции</th>
                    <c:forEach items="${users}" var="user" varStatus="status">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.surname}</td>
                            <td>
                                <a href="${pageContext.servletContext.contextPath}/manager/users/user/id=${user.id}">Подробно</a>
                                <a href="${pageContext.servletContext.contextPath}/manager/users/edit/id=${user.id}">Редактировать</a>
                                <a href="${pageContext.servletContext.contextPath}/manager/users/delete/id=${user.id}">Удалить</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <form action="${pageContext.servletContext.contextPath}/manager/users/create" method="GET">
                    <input type="submit" align="center" value="Добавить пользователя"/>
                </form>
            </div>
        </div>
   </body>
</html>
