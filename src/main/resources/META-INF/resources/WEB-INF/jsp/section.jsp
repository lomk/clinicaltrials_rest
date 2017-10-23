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

                <c:if test="${not empty section}">
                    <p>${section.name}</p>
                    <p>${section.body}</p>
                </c:if>

        </div>
    </div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>