<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>
<html>
<head><title>clinicaltrials.com.ua</title></head>
<%@ include file="../bootstrapsource.jsp"%>
<body>
<div class="container">
    <%@ include file="header.jsp"%>
    <h2>Розділи меню</h2>
    <a href="/admin/section/new">Створити новий</a>
    <table class="table table-striped">
        <tr>
            <th>Id</th>
            <th>Назва розділу</th>
            <th>Назва меню</th>
            <th>Редагувати</th>
            <th>Видалити</th>
        </tr>
        <c:if test="${not empty sections}">
            <c:forEach var="section" items="${sections}">
                <tr>
                    <td text="${section.id}"><a href="/section/${section.id}">${section.id}</a></td>
                    <td text="${section.name}">${section.name}</td>
                    <td>${section.menu.name}</td>
                    <td><a href="edit/${section.id}">Редагувати</a></td>
                    <td><a href="delete/${section.id}">Видалити</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <a href="/admin/section/new">Створити новий</a>
</div>
</body>
</html>
