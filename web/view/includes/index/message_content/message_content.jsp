<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${MESSAGE_PAGINATION.messageType==MESSAGE_PAGINATION.MESSAGE_TYPE_WARNING}">
        <div id="message" class="message-warning">
        </c:when>
        <c:when test="${MESSAGE_PAGINATION.messageType==MESSAGE_PAGINATION.MESSAGE_TYPE_ERROR}">
            <div id="message" class="message-error">
            </c:when>
            <c:when test="${MESSAGE_PAGINATION.messageType==MESSAGE_PAGINATION.MESSAGE_TYPE_SUCCESS}">
                <div id="message" class="message-success">
                </c:when>
                <c:otherwise>
                    <div id="message" class="message-info">
                    </c:otherwise>
                </c:choose>
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                    <span class="sr-only">Close</span>
                </button>
                <div class="message-container">
                    <div class="message-title">${MESSAGE_PAGINATION.messageTitle}</div>
                    <div class="message-body">${MESSAGE_PAGINATION.messageBody}</div>
                </div>
            </div>