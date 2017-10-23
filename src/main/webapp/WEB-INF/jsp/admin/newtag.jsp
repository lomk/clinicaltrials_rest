<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@ page isELIgnored="false" %>
<html>
<head><title>Hello world Example</title></head>
<%@ include file="../bootstrapsource.jsp"%>
<body>
<div class="container">
    <%@ include file="header.jsp"%>
    <h2>Новая роль</h2>
    <div>
        <sf:form class="form-horizontal" action="/admin/tag/new" method="POST" modelAttribute="tag">
            <div class="form-group">
                <label class="col-sm-2 control-label">Имя:</label>
                <div class="col-sm-10">
                    <sf:input class="form-control" path="name"/>
                </div>
            </div>
            <div class="row">
                <button type="submit" class="btn btn-default">Создать</button>
            </div>
        </sf:form>
    </div>
</div>
</body>
</html>