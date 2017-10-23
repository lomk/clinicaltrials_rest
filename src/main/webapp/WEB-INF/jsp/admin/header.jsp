<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <div class="header">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                    <a class="navbar-brand" href="/">Головна</a>
                    <a class="navbar-brand" href="/admin">Admin</a>
                    <ul class="nav navbar-nav">
                        <li><a href="/admin/category/all">Категорії</a></li>
                        <li><a href="/admin/article/all">Статті</a></li>
                        <li><a href="/admin/role/all">Ролі</a></li>
                        <li><a href="/admin/user/all">Користувачі</a></li>
                        <li><a href="/admin/tag/all">Теги</a></li>
                        <li><a href="/admin/menu/all">Меню</a></li>
                        <li><a href="/admin/section/all">Розділи меню</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <sec:authorize access="isAnonymous()">
                            <li>
                                <button type="button" class="btn btn-default navbar-btn">
                                    <a href="/login">Вхід</a>
                                </button>
                            </li
                        </sec:authorize>
                        <sec:authorize access="isAuthenticated()">
                            <sec:authentication var="principal" property="principal" />
                            <li>
                                <a href="/${principal.username}">${principal.username}</a>
                            </li>
                            <li>
                                <button type="button" class="btn btn-default navbar-btn">
                                    <a href="/logout">Вихід</a>
                                </button>
                            </li>
                        </sec:authorize>
                    </ul>
            </div>
        </nav>
    </div>

