<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<html>
<head><title>Hello world Example</title></head>
<%@ include file="bootstrapsource.jsp"%>
<body>
<%@include file="topnavbar.jsp"%>
<div class="container">
    <%@ include file="header.jsp"%>
    <div class="row">
        <div class="col-sm-10">
                <c:if test="${not empty article}">
                    <h3><a href="/article/category/${article.category.url}">${article.category.name}</a> - ${article.title}</h3>
                    <p>${article.dateField} ${user.username}</p>
                    <img src="../image/get/${article.imgUrl}" style='height:auto; width: 100%;'/>
                    <p class="text-justify">${article.body}</p>
                    <%@ include file="comment.jsp"%>
                </c:if>
        </div>
        <div class="col-sm-2">
            <%@ include file="sidebar.jsp"%>
        </div>
    </div>

</div>
<%@include file="footer.jsp"%>
</body>
</html>