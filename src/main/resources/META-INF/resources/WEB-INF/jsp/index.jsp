<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page isELIgnored="false" %>
<html>
<head><title>clinicaltrials.com.ua</title></head>
<%@ include file="bootstrapsource.jsp"%>

<body>
<%@include file="topnavbar.jsp"%>
<div class="container">
    <%@ include file="header.jsp"%>
    <div class="row">
        <div class="col-lg-10">
            <div class="row">
                <c:if test="${not empty articles}">
                    <c:forEach var="article" items="${articles}" varStatus="loop">
                        <div class="col-sm-12 col-md-6 col-lg-4">
                            <div class="thumbnail">
                                <a href="/article/${article.id}">
                                    <c:if test="${not empty article.imgUrl}">
                                    <img src="/image/get/${article.imgUrl}" alt="/image/get/${article.imgUrl}">
                                    </c:if>
                                </a>
                                <div class="caption">
                                    <h3><a href="/article/category/${article.category.url}">${article.category.name}</a> - <a href="/article/${article.id}">${article.title}</a></h3>
                                    <p>${article.desc}</p>
                                    <p><a href="/article/${article.id}" class="btn btn-default" role="button">Читать</a></p>
                                </div>
                            </div>
                        </div>
                        <c:if test="${not loop.first and (loop.index + 1 ) % 3 == 0}">
                            <div class="clearfix visible-lg"></div>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
<%--        <div class="col-sm-2">
            <%@ include file="sidebar.jsp"%>
        </div>--%>
    </div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>