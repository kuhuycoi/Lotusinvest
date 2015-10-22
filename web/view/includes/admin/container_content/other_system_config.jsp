<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content-title">
    <h3 class="text-center">Thông tin công ty</h3>
</div>
<div class="panel panel-default">
    <div class="panel-heading clearfix">
        <button class="btn pull-right btn-default">Cập nhật thông tin</button>
    </div>
    <div class="panel-body">
        <form action="#" method="post" class="form-horizontal">
            <div class="row">
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="name" class="control-label col-sm-4">Tên <i>(*)</i></label>
                        <div class="col-sm-8">
                            <p class="form-control-static">${SYSTEM_CONFIG.name}</p>
                        </div>
                        <div class="col-sm-4 hidden">
                            <input type="text" id="name" required name="name" class="form-control" value="${SYSTEM_CONFIG.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="control-label col-sm-4">Email <i>(*)</i></label>
                        <div class="col-sm-8">
                            <p class="form-control-static">${SYSTEM_CONFIG.email==null?'<i>Chưa cung cấp</i>':SYSTEM_CONFIG.email}</p>
                        </div>
                        <div class="col-sm-4 hidden">
                            <input type="text" id="email" required name="email" class="form-control" value="${SYSTEM_CONFIG.email}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email1" class="control-label col-sm-4">Email khác</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">${SYSTEM_CONFIG.email1==null?'<i>Chưa cung cấp</i>':SYSTEM_CONFIG.email1}</p>
                        </div>
                        <div class="col-sm-4 hidden">
                            <input type="text" id="email1" name="email1" class="form-control" value="${SYSTEM_CONFIG.email1}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phoneAdvice" class="control-label col-sm-4">Số ĐT tư vấn 1 <i>(*)</i></label>
                        <div class="col-sm-8">
                            <p class="form-control-static">${SYSTEM_CONFIG.phoneAdvice==null?'<i>Chưa cung cấp</i>':SYSTEM_CONFIG.phoneAdvice}</p>
                        </div>
                        <div class="col-sm-4 hidden">
                            <input type="text" id="phoneAdvice" required name="phoneAdvice" class="form-control" value="${SYSTEM_CONFIG.phoneAdvice}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phoneAdvice1" class="control-label col-sm-4">Số ĐT tư vấn 2</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">${SYSTEM_CONFIG.phoneAdvice1==null?'<i>Chưa cung cấp</i>':SYSTEM_CONFIG.phoneAdvice1}</p>
                        </div>
                        <div class="col-sm-4 hidden">
                            <input type="text" id="phoneAdvice1" name="phoneAdvice1" class="form-control"  value="${SYSTEM_CONFIG.phoneAdvice1}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phoneAdvice2" class="control-label col-sm-4">Số ĐT tư vấn 3</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">${SYSTEM_CONFIG.phoneAdvice2==null?'<i>Chưa cung cấp</i>':SYSTEM_CONFIG.phoneAdvice2}</p>
                        </div>
                        <div class="col-sm-4 hidden">
                            <input type="text" id="phoneAdvice2" name="phoneAdvice2" class="form-control" placeholder="" value="${SYSTEM_CONFIG.phoneAdvice2}">
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="fax" class="control-label col-sm-4">Số Fax</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">${SYSTEM_CONFIG.fax==null?'<i>Chưa cung cấp</i>':SYSTEM_CONFIG.fax}</p>
                        </div>
                        <div class="col-sm-4 hidden">
                            <input type="text" id="fax" name="fax" class="form-control" placeholder="" value="${SYSTEM_CONFIG.fax}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="address" class="control-label col-sm-4">Địa chỉ</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">${SYSTEM_CONFIG.address==null?'<i>Chưa cung cấp</i>':SYSTEM_CONFIG.address}</p>
                        </div>
                        <div class="col-sm-4 hidden">
                            <textarea type="text" id="address" name="address" class="form-control" rows="4">
                                ${SYSTEM_CONFIG.address}
                            </textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="isShow" class="control-label col-sm-4">Hiển thị</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">${SYSTEM_CONFIG.isShow?'Có':'Không'}</p>
                        </div>
                        <div class="col-sm-4 hidden">
                            <input type="checkbox" id="isShow" name="isShow" ${SYSTEM_CONFIG.isShow?'checked':''} />
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="googleMap" class="control-label col-sm-2">Google map</label>
                <div class="col-sm-10">
                    <p class="form-control-static">${SYSTEM_CONFIG.googleMap==null?'<i>Chưa cung cấp</i>':SYSTEM_CONFIG.googleMap}</p>
                </div>
                <div class="col-sm-4 hidden">
                    <textarea type="text" id="googleMap" name="googleMap" class="form-control" rows="4">
                        ${SYSTEM_CONFIG.googleMap}
                    </textarea>
                </div>
            </div>
        </form>
    </div>
</div>