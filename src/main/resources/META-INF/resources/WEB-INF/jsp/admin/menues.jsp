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
    <h2>Меню</h2>
    <a href="/admin/menu/new">Створити нове</a>
    <table class="table table-striped">
        <tr>
            <th>Id</th>
            <th>Назва меню</th>
            <th>Редагувати</th>
            <th>Видалити</th>
        </tr>
        <c:if test="${not empty menues}">
            <c:forEach var="menu" items="${menues}">
                <tr>
                    <td>${menu.id}</td>
                    <td text="${menu.name}">${menu.name}</td>
                    <td><a href="edit/${menu.id}">Редагувати</a></td>
                    <td><a href="delete/${menu.id}">Видалити</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <a href="/admin/menu/new">Створити нове</a>
</div>
</body>
</html>