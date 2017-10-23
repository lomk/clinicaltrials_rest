<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<html>
<head><title>Hello world Example</title></head>
<%@ include file="../bootstrapsource.jsp"%>
<body>
<div class="container">
    <%@ include file="header.jsp"%>
    <h2>Новый пользователь</h2>
    <div>
        <sf:form class="form-horizontal" action="/registration" method="POST" modelAttribute="user">
            <sf:hidden field="${id}" path="id"/>
            <div class="form-group">
                <label class="col-sm-2 control-label">Логин:</label>
                <div class="col-sm-10">
                    <sf:input class="form-control" path="username"/>
                </div>
            </div>
            <spring:bind path="password">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Пароль:</label>
                    <div class="col-sm-10">
                        <form:input type="password" path="password" class="form-control" placeholder="Пароль"></form:input>
                        <form:errors path="password"></form:errors>
                    </div>
                </div>
            </spring:bind>

            <spring:bind path="passwordConfirm">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Снова пароль:</label>
                    <div class="col-sm-10">
                    <form:input type="password" path="passwordConfirm" class="form-control"
                                placeholder="Confirm your password"></form:input>
                    <form:errors path="passwordConfirm"></form:errors>
                    </div>
                </div>
            </spring:bind>
            <div class="form-group">
                <label class="col-sm-2 control-label">Роль:</label>
                <div class="col-sm-10">
                    <sf:select path="role" class="form-control">
                        <sf:option value="NONE"> --SELECT--</sf:option>
                        <sf:options items="${roles}" itemValue="id" itemLabel="name"></sf:options>
                    </sf:select>
                </div>
            </div>
            <div class="row">
                <button type="submit" class="btn btn-default">Submit</button>
            </div>
        </sf:form>
    </div>
</div>
</body>
</html>