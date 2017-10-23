<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<nav aria-label="Page navigation">
    <ul class="pagination pagination-lg">
        <c:choose>
            <c:when test="${page > 1}">
                <li>
                    <a href="${page - 1}" aria-label="Previous">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="disabled">
                    <a href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:otherwise>
        </c:choose>
        <c:forEach begin="1" end="${pages}" varStatus="loop">
            <c:choose>
                <c:when test="${loop.index == page}">
                    <li class="active"><a href="#">${loop.index} <span class="sr-only">(current)</span></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${loop.index}">${loop.index} </a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${page < pages}">
                <li>
                    <a href="${page + 1}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="disabled">
                    <a href="#" aria-label="Next">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
</nav>

