<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>clinicaltrials.com.ua</title>
    <script src='/js/tinymce/tinymce.min.js'></script>
    <script type="text/javascript" src="/js/tinymce/tiny_mce_init.js"></script>
</head>
<%@ include file="../bootstrapsource.jsp"%>
<body>
<div class="container">
    <%@ include file="header.jsp"%>
    <h2>Create new article.</h2>
    <div>
        <sf:form class="form-horizontal" action="/admin/article/new" method="POST" modelAttribute="article" enctype="multipart/form-data">
            <div class="form-group">
                <label class="col-sm-2 control-label">Заголовок:</label>
                <div class="col-sm-10">
                    <sf:textarea class="form-control" path="title"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Рисунок:</label>
                <div class="col-sm-10">
                    <input name="image" type="file"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">SEO-описание:</label>
                <div class="col-sm-10">
                    <sf:textarea class="form-control" path="seoDesc"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Описание:</label>
                <div class="col-sm-10">
                    <sf:textarea class="form-control" path="desc"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Статья:</label>
                <div class="col-sm-10">
                    <sf:textarea class="form-control" path="body" id="mytextarea"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">URL рисунка:</label>
                <div class="col-sm-10">
                    <sf:input class="form-control" path="imgUrl"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Категория:</label>
                <div class="col-sm-10">
                    <sf:select path="category" class="form-control">
                        <sf:option value="NONE"> --SELECT--</sf:option>
                        <sf:options items="${categories}" itemValue="id" itemLabel="name"></sf:options>
                    </sf:select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Тэг:</label>
                <div class="col-sm-10">
                    <sf:select path="tags" class="form-control">
                        <sf:option value="NONE"> --SELECT--</sf:option>
                        <sf:options items="${tags}" itemValue="id" itemLabel="name"></sf:options>
                    </sf:select>
                </div>
            </div>

            <div class="row">
                <button type="submit" class="btn btn-default">Submit</button>
            </div>
        </sf:form>
        <iframe id="form_target" name="form_target" style="display:none"></iframe>
        <form id="my_form" action="/image/upload" target="form_target" method="post" enctype="multipart/form-data" style="width:0px;height:0;overflow:hidden">
            <input name="image" type="file" onchange="$('#my_form').submit();this.value='';">
        </form>
    </div>
</div>
</body>
</html>