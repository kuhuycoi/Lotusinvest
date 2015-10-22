<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/WEB-INF/tlds/functions.tld" %>
<div class="row">
    <div class="col-md-12 tree-handle">
        <div class="btn-group btn-group-sm">
            <button class="btn btn-default btn-expand-all" data-toggle="tooltip" data-placement="left" data-title="Mở tất cả."><span class="glyphicon glyphicon-resize-full"></span></button>
            <button class="btn btn-default btn-hide-all" data-toggle="tooltip" data-placement="right" data-title="Đóng tất cả."><span class="glyphicon glyphicon-resize-small"></span></button>
        </div>
    </div>
</div>
${TREE_CUSTOMER}