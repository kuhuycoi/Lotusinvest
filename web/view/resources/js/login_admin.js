$(document).ready(function () {

    $(document).on('click', '#message .close', function () {
        $('#message').fadeOut(300).remove();
    });
    $(document).on('submit', '#form-login-admin', function (e) {
        e.preventDefault();
        var admin = JSON.stringify($(this).serializeObject());
        var url = $(this).attr('action');
        $.ajax({
            beforeSend: function () {
                NProgress.set(0.5);
            },
            url: url,
            type: 'POST',
            data: admin,
            contentType: 'application/json',
            success: function (data) {
                NProgress.done();                
                openMessage(data);
            }
        });
    });
});
function sendAjax(url, type, data, handle) {
    $.ajax({
        beforeSend: function () {
            NProgress.set(0.5);
        },
        url: url,
        type: type,
        data: data,
        headers: {'Content-Type': undefined},
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        success: function (data) {
            if (typeof handle !== "undefined") {
                handle(data);
            }
            NProgress.done();
        },
        error: function () {
            NProgress.done();
        }
    });
}
var intervalDisplayMessage;
function openMessage(data) {
    clearInterval(intervalDisplayMessage);
    $('#message').remove();
    if (typeof data !== "undefined") {
        $('body').append(data);
    }
    intervalDisplayMessage = setInterval(function () {
        $('#message').remove();
    }, 5000);
    $('#message').fadeIn(300);
}
$.fn.serializeObject = function () {
    var o = {};
    $.each($(this).find('input:not(.external),select:not(.external),textarea:not(.external)'), function () {
        var v;
        if ($(this).is(':checkbox')) {
            v = $(this).is(':checked');
        } else if ($(this).val() === '') {
            v = null;
        } else if ($(this).attr('data-json')) {
            v = {};
            v[$(this).attr('data-json')] = $(this).val();
        } else {
            v = $(this).val();
        }
        o[$(this).attr('name')] = v;
    });
    return o;
};