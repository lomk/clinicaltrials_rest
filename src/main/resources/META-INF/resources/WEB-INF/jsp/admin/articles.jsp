<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>
<html>
<head><title>Hello world Example</title></head>
<%@ include file="../bootstrapsource.jsp"%>
<body>
<div class="container">
    <%@ include file="header.jsp"%>
    <h2>Статьи</h2>
    <p><a href="/admin/article/new">Создать новую</a></p>
    <table class="table table-striped">
        <tr>
            <th>Id</th>
            <th>Заголовок</th>
            <th>Категория</th>
            <th>Пользователь</th>
            <th>Дата</th>
            <th>Редактировать</th>
            <th>Удалить</th>
        </tr>
        <c:if test="${not empty articles}">
            <c:forEach var="article" items="${articles}">
                <tr>
                    <td text="${article.id}"><a href="/admin/article/${article.id}">${article.id}</a></td>
                    <td text="${article.title}"><a href="/admin/article/${article.id}">${article.title}</td>
                    <td text="${article.category.name}">${article.category.name}</td>
                    <td text="${article.user.username}">${article.user.username}</td>
                    <td text="${article.dateField}">${article.dateField}</td>
                    <td><a href="/admin/article/edit/${article.id}">Редактировать</a></td>
                    <td><a href="/admin/article/delete/${article.id}">Удалить</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <p><a href="/admin/article/new">Создать новую</a></p>
</div>
</body>
</html>