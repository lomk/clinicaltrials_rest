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
    <div class="row">
        <div class="col-sm-10">
            <div class="row">
                <c:if test="${not empty article}">
                    <div class="col-sm-10">
                        <p><a href="/admin/article/new">Создать новую</a></p>
                        <a href="/admin/article/edit/${article.id}">Редактировать</a>
                        <a href="/admin/article/delete/${article.id}">Удалить</a>
                        <h3><a href="/article/${article.category.url}">${article.category.name}</a> - ${article.title}</h3>
                        <p>${article.dateField}</p>
                        <p>${user.username}</p>
                        <p class="text-justify">${article.body}</p>
                        <br>
                        <p>Комментарии:</p>
                        <c:if test="${not empty article.comments}">
                            <c:forEach var="comment" items="${article.comments}">
                                <p>${comment.dateTimeField}</p>
                                <p>${comment.user.username}</p>
                                <p>${comment.body}</p>
                                <br>
                                <a href="/admin/comment/delete/${comment.id}">Удалить</a>
                            </c:forEach>
                        </c:if>
                        <br>
                        <p><a href="/admin/article/new">Создать новую</a></p>
                        <a href="/admin/article/edit/${article.id}">Редактировать</a>
                        <a href="/admin/article/delete/${article.id}">Удалить</a>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>