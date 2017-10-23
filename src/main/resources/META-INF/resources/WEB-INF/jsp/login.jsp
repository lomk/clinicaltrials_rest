<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<html>
<head><title>Hello world Example</title></head>
<%@ include file="bootstrapsource.jsp"%>
<body>
<%@include file="topnavbar.jsp"%>
<div class="container">
    <%@ include file="header.jsp"%>
          <form method="POST" action="/login" class="form-default"   style="width: 300px;" alight="center">
              <h2 class="form-heading">Log in</h2>

              <div class="form-group ${error != null ? 'has-error' : ''}" style="text-align: center">
                  <span>${message}</span>
                  <input name="username" type="text" class="form-control" placeholder="Username" autofocus="true"/>
                  <br>
                  <input name="password" type="password" class="form-control" placeholder="Password"/>
                  <span>${error}</span>
                  <br>
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                  <br>
                  <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
                  <br>
                  <h4 class="text-center"><a href="/registration">Регистрация</a></h4>
              </div>

          </form>

</div>
<%@include file="footer.jsp"%>
</body>
</html>