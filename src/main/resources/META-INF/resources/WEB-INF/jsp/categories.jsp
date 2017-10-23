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
    <div class="row">
        <c:if test="${not empty cateory}">
            <h1>${category.name}</h1>
        </c:if>
        <c:if test="${not empty tag}">
            <h1>${tag.name}</h1>
        </c:if>
        <div class="col-sm-10">
                <c:if test="${not empty categories}">
                    <c:forEach var="category" items="${categories}">
                        <%--<c:if test="${!category.articles}">--%>
                        <h3><a href="/article/category/${category.url}">${category.name}</a> </h3>
                            <div class="row">
                                <c:forEach var="article" items="${category.articles}" begin="0" end="2">
                                    <div class="col-sm-3">
                                        <h4><a href="/article/${article.id}">${article.title}</a></h4>
                                        <p>${article.desc}</p>
                                    </div>
                                </c:forEach>
                            </div>
                        <%--</c:if>--%>
                    </c:forEach>
                    <c:forEach begin="1" end="${pages}" varStatus="loop">
                        <a href="/categories/all/${loop}">${loop} </a>
                    </c:forEach>
                </c:if>
        </div>
    </div>
        <div class="col-sm-2">
            <%@ include file="sidebar.jsp"%>
        </div>

    <%@ include file="footer.jsp"%>
</div>
</body>
</html>