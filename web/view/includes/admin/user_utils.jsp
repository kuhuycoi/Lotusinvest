<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="panel panel-primary">
    <div class="panel-heading" data-toggle="collapse" href="#user-utils-body" aria-expanded="false" aria-controls="note-body">
        <a>User's Utilities</a>
    </div>
    <div class="panel-body collapse" id="user-utils-body">
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#note" aria-controls="note" role="tab" data-toggle="tab">Note</a></li>
            <li role="presentation"><a href="#clock" aria-controls="clock" role="tab" data-toggle="tab">Clock</a></li>
            <li role="presentation"><a href="#calendar" aria-controls="calendar" role="tab" data-toggle="tab">Calendar</a></li>
        </ul>
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="note">
                <div class="row">
                    <div class="col-md-12">
                        <textarea class="form-control" id="txtarea-note" style="width: 278px" rows="10"></textarea>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="pull-right btn-group">
                            <button class="btn btn-default btn-select-text" data-target="#txtarea-note">Select All</button>
                            <button class="btn btn-default btn-clear-text" data-target="#txtarea-note">Clear</button>
                        </div>
                    </div>
                </div>
            </div>
            <div role="tabpanel" class="tab-pane" id="clock">
                <ul class="clock-content">
                    <li class="face">
                        <img src="${pageContext.servletContext.contextPath}/view/img/clock/clockFace.png" />
                    </li>
                    <li class="sec">
                        <img src="${pageContext.servletContext.contextPath}/view/img/clock/sechand.png" />
                    </li>
                    <li class="hour">
                        <img src="${pageContext.servletContext.contextPath}/view/img/clock/hourhand.png" />                        
                    </li>
                    <li class="min">
                        <img src="${pageContext.servletContext.contextPath}/view/img/clock/minhand.png" />
                    </li>
                </ul>
            </div>
            <div role="tabpanel" class="tab-pane calendar-container" id="calendar">
                <div class="calendar">
                    <header>
                        <h2 class="month"></h2>
                        <a class="btn-prev fa fa-angle-left"></a>
                        <a class="btn-next fa fa-angle-right"></a>
                    </header>
                    <table>
                        <thead class="event-days">
                            <tr></tr>
                        </thead>
                        <tbody class="event-calendar">
                            <tr class="1"></tr>
                            <tr class="2"></tr>
                            <tr class="3"></tr>
                            <tr class="4"></tr>
                            <tr class="5"></tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>