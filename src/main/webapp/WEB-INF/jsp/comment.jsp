<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-sm-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Комментарии:</h3>
            </div>
            <div class="panel-body">
                <c:choose>
                <c:when test="${not empty article.comments}">
                    <c:forEach var="comment" items="${article.comments}">
                        <p>${comment.user.username}  ${comment.dateTimeField}</p>
                        <p>${comment.body}</p>
                        <hr>
                    </c:forEach>
                </c:when>
                    <c:otherwise>
                        <p>Комментариев еще нет</p>
                        <hr>
                    </c:otherwise>
                </c:choose>
                <br>
                <sec:authorize access="isAnonymous()">
                    <p><a href="/login">Авторизируйтесь чтобы комментировать</a></p>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <p>Добавить комментарий</p>
                    <sf:form class="form-horizontal" action="/comment/new" method="POST" modelAttribute="comment">
                        <div class="form-group">
                            <div class="col-sm-12">
                                <sf:textarea class="form-control" path="body"/>
                                <sf:hidden path="user" value="${user.id}"/>
                                <sf:hidden path="article" value="${article.id}"/>
                                <br>
                                <button class="btn btn-default" type="submit">Отправить</button>
                            </div>
                        </div>
                    </sf:form>
                </sec:authorize>
            </div>
        </div>
    </div>
</div>
