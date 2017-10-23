<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>
<html>
<head><title>Hello world Example</title></head>
<%@ include file="bootstrapsource.jsp"%>
<body>

<div class="container">
    <%@ include file="header.jsp"%>

    <h2>Product Details</h2>
    <div>
        <form class="form-horizontal">
            <c:if test="${not empty product}">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Product Id:</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">ID ${product.id}</p></div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Product Title:</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">Title ${product.title}</p></div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Price:</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">Price ${product.price}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Price:</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">Sell price ${product.salePrice}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Quantity:</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">Quantity ${product.quantity}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Product URL:</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">URL ${product.url}</p></div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">Description:</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">Description ${product.desc}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Full description:</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">Full description ${product.fullDesc}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">SEO description:</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">SEO description ${product.seoDesc}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Is bestseller:</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">Is bestseller ${product.bestseller}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Image Url:</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">url.... ${product.imageUrl}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Product Category:</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">Product Category ${product.productCategory.name}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Product Static attributes:</label>
                    <c:if test="${not empty product.staticAttributes}">
                        <c:forEach var="attribute" items="${product.staticAttributes}">
                            <div class="col-sm-10">
                                <p class="form-control-static">${attribute.name}: ${attribute.content}</p>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Product Static attributes:</label>
                    <c:if test="${not empty product.uniqueAttributes}">
                        <c:forEach var="attribute" items="${product.uniqueAttributes}">
                            <div class="col-sm-10">
                                <p class="form-control-static">${attribute.name}: ${attribute.content}</p>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${not empty product.uniqueAttributes}">
                        <%@ include file="newuniqueattr.jsp"%>
                    </c:if>
                </div>
            </c:if>
        </form>
    </div>
</div>
</body>
</html>
