<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>
<html>
<head><title>Blog</title></head>
<%@ include file="../bootstrapsource.jsp"%>
<body>
<div class="container">
    <%@ include file="header.jsp"%>
    <h2>Роли</h2>
        <a href="/admin/role/new">Создать новую</a>
    <table class="table table-striped">
        <tr>
            <th>Id</th>
            <th>Имя роли</th>
            <th>Редактировать</th>
            <th>Удалить</th>
        </tr>
        <c:if test="${not empty roles}">
            <c:forEach var="role" items="${roles}">
                <tr>
                    <td>${role.id}</td>
                    <td text="${role.name}">${role.name}</td>
                    <td><a href="edit/${role.id}">Редактировать</a></td>
                    <td><a href="delete/${role.id}">Удалить</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <a href="/admin/role/new">Создать новую</a>
</div>
</body>
</html>