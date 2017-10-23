<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false" %>
<html>
<head><title>clinicaltrials.com.ua</title></head>
<script src='/js/tinymce/tinymce.min.js'></script>
<script type="text/javascript" src="/js/tinymce/tiny_mce_init.js"></script>
<%@ include file="../bootstrapsource.jsp"%>
<body>
<div class="container">
    <%@ include file="header.jsp"%>
    <h2>Новий розділ меню</h2>
    <div>
        <sf:form class="form-horizontal" action="/admin/section/new" method="POST" modelAttribute="section">
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
            <div class="form-group">
                <label class="col-sm-2 control-label">SEO-описание:</label>
                <div class="col-sm-10">
                    <sf:textarea class="form-control" path="seoDesc"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">URL</label>
                <div class="col-sm-10">
                    <sf:textarea class="form-control" path="url"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Контент:</label>
                <div class="col-sm-10">
                    <sf:textarea class="form-control" path="body" id="mytextarea"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Вибір меню:</label>
                <div class="col-sm-10">
                    <sf:select path="menu" class="form-control">
                        <sf:option value="NONE"> --Вибрати--</sf:option>
                        <sf:options items="${menues}" itemValue="id" itemLabel="name"></sf:options>
                    </sf:select>
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