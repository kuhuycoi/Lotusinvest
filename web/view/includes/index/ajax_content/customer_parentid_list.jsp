<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/WEB-INF/tlds/functions.tld" %>
<c:if test="${f:size(PARENTIDLIST)==0}" >
    <div class="alert alert-danger">
        Không tìm thấy kết quả nào
    </div>
</c:if>
<c:if test="${f:size(PARENTIDLIST)>0}">
    <table class="auto-complete-data table table-hover table-valign-midle table-align-center">
        <thead>
            <tr>
                <th class="th-checkbox"></th>
                <th class="th-id">ID</th>
                <th class="col-md-6">Tên</th>
                <th class="col-md-6">Username</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${PARENTIDLIST}" var="cus">
                <tr data-username="${cus.userName}">
                    <td><span class="fa fa-user"></span></td>
                    <td>${cus.id}</td>
                    <td>${cus.firstName} ${cus.lastName}</td>
                    <td>${cus.userName}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>
