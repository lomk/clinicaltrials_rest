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
    <h2>Тэги</h2>
    <a href="/admin/tag/new">Создать новый</a>
    <table class="table table-striped">
        <tr>
            <th>Id</th>
            <th>Имя</th>
            <th>Редактировать</th>
            <th>Удалить</th>
        </tr>
        <c:if test="${not empty tags}">
            <c:forEach var="tag" items="${tags}">
                <tr>
                    <td>${tag.id}</td>
                    <td text="${tag.name}">${tag.name}</td>
                    <td><a href="edit/${tag.id}">Редактирвать</a></td>
                    <td><a href="delete/${tag.id}">Удалить</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <a href="/admin/tag/new">Создать новый</a>
</div>
</body>
</html>