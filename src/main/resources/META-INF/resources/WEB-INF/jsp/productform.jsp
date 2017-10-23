<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page isELIgnored="false" %>
<html>
<head><title>Hello world Example</title></head>
<%@ include file="bootstrapsource.jsp"%>
<body>
<div class="container">
    <%@ include file="header.jsp"%>

    <h2>Product Details</h2>
    <div>
        <form:form class="form-horizontal" action="/product" method="POST" modelAttribute="product">
            <form:hidden field="${productId}" path="productId"/>
            <%--<form:hidden field="${version}" path="version"/>--%>
            <div class="form-group">
                <label class="col-sm-2 control-label">Title:</label>
                <div class="col-sm-10">
                    <form:input class="form-control" path="title"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Price:</label>
                <div class="col-sm-10">
                    <form:input class="form-control" path="price"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Sale Price:</label>
                <div class="col-sm-10">
                    <form:input class="form-control" path="salePrice"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Quantity:</label>
                <div class="col-sm-10">
                    <form:input class="form-control" path="quantity"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Short Description:</label>
                <div class="col-sm-10">
                    <form:input class="form-control" path="desc"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Fuull Description:</label>
                <div class="col-sm-10">
                    <form:input class="form-control" path="fullDesc"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Seo Description:</label>
                <div class="col-sm-10">
                    <form:input class="form-control" path="seoDesc"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Image Url:</label>
                <div class="col-sm-10">
                    <form:input class="form-control" path="imageUrl"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Is bestseller:</label>
                <div class="col-sm-10">
                    <form:checkbox class="form-control" path="isBestseller"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Product Categories:</label>
                <div class="col-sm-10">
                    <form:select path="productCategory" class="form-control">
                        <form:option value="NONE"> --SELECT--</form:option>
                        <form:options items="${productCategories}" itemValue="id" itemLabel="name"></form:options>
                    </form:select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Product Attributes:</label>
                <div class="col-sm-10">
                    <form:select path="staticAttributes" class="form-control">
                        <form:option value="NONE"> --SELECT--</form:option>
                        <form:options items="${staticAttributes}" itemValue="id" itemLabel="name"></form:options>
                    </form:select>
                </div>
            </div>


            <div class="row">
                <button type="submit" class="btn btn-default">Submit</button>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
