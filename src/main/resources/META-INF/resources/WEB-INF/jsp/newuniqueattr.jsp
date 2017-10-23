<%@ form:taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form class="form-horizontal" action="/product" method="POST" modelAttribute="uniqueAttribute">
    <form:hidden field="${productId}" path="product"/>
    <div class="form-group">
        <label class="col-sm-2 control-label">Name:</label>
        <div class="col-sm-10">
            <form:input class="form-control" path="name"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">Description:</label>
        <div class="col-sm-10">
            <form:input class="form-control" path="content"/>
        </div>
    </div>
</form:form>