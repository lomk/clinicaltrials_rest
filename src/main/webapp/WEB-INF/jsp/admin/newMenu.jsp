<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false" %>
<html>
<head><title>clinicaltrials.com.ua</title></head>
<%@ include file="../bootstrapsource.jsp"%>
<body>
<div class="container">
    <%@ include file="header.jsp"%>
    <h2>Нове меню</h2>
    <div>
        <sf:form class="form-horizontal" action="/admin/menu/new" method="POST" modelAttribute="menu">
            <div class="form-group">
                <label class="col-sm-2 control-label">ID</label>
                <div class="col-sm-10">
                    <sf:textarea class="form-control" path="id"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Назва</label>
                <div class="col-sm-10">
                    <sf:textarea class="form-control" path="name"/>
                </div>
            </div>
            <div class="row">
                <button class="btn btn-lg btn-primary btn-block" type="submit">Створити</button>
            </div>
        </sf:form>
    </div>
</div>
</body>
</html>