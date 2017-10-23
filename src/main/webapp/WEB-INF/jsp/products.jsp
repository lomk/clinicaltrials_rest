<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>
<html>
<head><title>Hello world Example</title></head>
<%@ include file="bootstrapsource.jsp"%>
<body>
<%@ include file="header.jsp"%>
<div class="container">
        <h2>Product List</h2>
        <table class="table table-striped">
            <tr>
                <th>Id</th>
                <th>Product Id</th>
                <th>Description</th>
                <th>Price</th>
                <th>View</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:if test="${not empty products}">
                <c:forEach var="product" items="${products}">
                <tr>
                <td text="${product.id}"><a href="/product/${product.id}">Id</a></td>
                <td text="${product.productId}">${product.id}</td>
                <td text="${product.description}">${product.description}</td>
                <td text="${product.price}">${product.price}</td>
                <td><a href="/product/${product.id}">View</a></td>
                <td><a href="/product/edit/${product.id}">Edit</a></td>
                <td><a href="/product/delete/${product.id}">Delete</a></td>
            </tr>
                </c:forEach>
            </c:if>
        </table>

    </div>
<%--</div>--%>
</body>
</html>