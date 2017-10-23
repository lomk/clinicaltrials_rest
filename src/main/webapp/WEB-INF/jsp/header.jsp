<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div class="jumbotron">
    <%--<div class="row text-center">
        <img src="/images/clinicaltrials.png" width="500" height="100"/>
        <div class="">
            <h3>clinicaltrials.com.ua</h3>
            <h3>Site in development</h3>
        </div>
    </div>--%>
    <%@include file="carousel.jsp"%>
</div>

<div class="header">
    <div class="row text-center">
    </div>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <%--<div class="navbar-header">--%>
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav" >
                    <%--<c:choose>
                        <c:when test="${fn:contains(pageContext.request.requestURI, '/article/all/page/')}">
                            <li class="active"><a href="/article/all/page/1">?????????? <span class="sr-only">(current)</span></a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="/article/all/page/1">??????????</a></li>
                        </c:otherwise>
                    </c:choose>--%>
                        <c:if test="${not empty menues}">
                            <c:forEach var="menu" items="${menues}" varStatus="loop">
                                <li class="dropdown" id="my">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${menu.name}<span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <c:if test="${not empty menu.getSections()}">
                                            <c:forEach var="section" items="${menu.getSections()}" varStatus="loop">
                                                <li><a href="/sections/${section.url}">${section.getName()}</a></li>
                                                <c:if test="${!loop.last}">
                                                    <li role="separator" class="divider"></li>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </ul>
                                </li>
                            </c:forEach>
                        </c:if>
<%--                    <li class="dropdown" id="my">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Про нас<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Хто ми</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Міссія та цілі</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Як користуватися сайтом</a></li>
                            <li> <a href="/logout">Контакти</a></li>
                        </ul>
                    </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Про клінічні дослідження<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Що таке клінічні дослідження</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">Історія клінічних досліджень у світі</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">Історія клінічних досліджень в Україні</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">Словник термінів</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">Законодавча та нормативно-правова бази</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">Корисні посилання</a></li>
                            </ul>
                        </li>--%>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">База досліджень<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">Separated link</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">One more separated link</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">База компаній<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">Separated link</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">One more separated link</a></li>
                            </ul>
                        </li>
                </ul>
            </div>
            <%--
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            </div>--%>
        </div>
    </nav>
</div>