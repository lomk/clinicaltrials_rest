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
    <h2>Категории</h2>
    <p><a href="/admin/category/new">Добавить новую</a></p>
    <table class="table table-striped">
        <tr>
            <th>Id</th>
            <th>Название</th>
            <th>URL</th>
            <th>Редактировать</th>
            <th>Удалить</th>
        </tr>
        <c:if test="${not empty categories}">
            <c:forEach var="category" items="${categories}">
                <tr>
                    <td text="${category.id}">${category.id}</td>
                    <td text="${category.name}">${category.name}</td>
                    <td text="${category.url}">${category.url}</td>
                    <td><a href="/admin/category/edit/${category.id}">Редактировать</a></td>
                    <td><a href="/admin/category/delete/${category.id}">Удалить</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <p><a href="/admin/category/new">Добавить новую</a></p>
</div>
</body>
</html>