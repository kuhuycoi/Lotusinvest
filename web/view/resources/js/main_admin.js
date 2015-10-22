$(document).ready(function () {
    $(document).on('click', '.dropdown-select', function (e) {
        e.stopPropagation();
    });
//    // fixed ajax-content navigation
//    $(window).scroll(function () {
//        var scroll = $(window).scrollTop();
//        if (scroll > 65 && $('#content>.panel>.panel-heading').length === 1) {
//            $('#content>.panel>.panel-heading').addClass('scrolled');
//            $('#content>.panel>.panel-heading').width($('#content>.panel>.panel-body').width());
//        } else {
//            $('#content>.panel>.panel-heading').removeClass('scrolled');
//            $('#content>.panel>.panel-heading').removeAttr('style');
//        }
//    });
    $(document).on('change', '.dropdown-select input[type="checkbox"]', function (e) {
        var target_normal = $(this).parents('.dropdown-select').find('input[type="checkbox"]:not(.select-all)');
        var target_all = $(this).parents('.dropdown-select').find('input[type="checkbox"].select-all');
        if ($(this).hasClass('select-all')) {
            if ($(this).is(':checked')) {
                $(target_normal).prop('checked', true);
            } else {
                $(target_normal).prop('checked', false);
            }
        } else if ($(target_normal).not(':checked').length) {
            $(target_all).prop('checked', false);
        } else if (!$(target_normal).not(':checked').length) {
            $(target_all).prop('checked', true);
        }
    });
    $(document).on('change', '.table input[type="checkbox"]', function (e) {
        var target_normal = $(this).parents('.table').find('input[type="checkbox"]:not(.select-all)');
        var target_all = $(this).parents('.table').find('input[type="checkbox"].select-all');
        if ($(this).hasClass('select-all')) {
            if ($(this).is(':checked')) {
                $(target_normal).prop('checked', true);
            } else {
                $(target_normal).prop('checked', false);
            }
        } else if ($(target_normal).not(':checked').length) {
            $(target_all).prop('checked', false);
        } else if (!$(target_normal).not(':checked').length) {
            $(target_all).prop('checked', true);
        }
    });
    $(document).on('click', '.btn-refresh', function () {
        var targetContent = $(this).parents('section').find('.item-content');
        var targetPagination = $(this).parents('section').find('.pagination-content');
        refreshTarget(targetContent, targetPagination);
    });
    /* ========================================================================= */
    /*  Fullscreen web
     /* ========================================================================= */
    $('#fullscreen-toggle').on('click', function (e) {
        var element = document.documentElement;
        if (!$('body')
                .hasClass("full-screen")) {
            $('body')
                    .addClass("full-screen");
            $('#fullscreen-toggle')
                    .addClass("active");
            if (element.requestFullscreen) {
                element.requestFullscreen();
            } else if (element.mozRequestFullScreen) {
                element.mozRequestFullScreen();
            } else if (element.webkitRequestFullscreen) {
                element.webkitRequestFullscreen();
            } else if (element.msRequestFullscreen) {
                element.msRequestFullscreen();
            }
        } else {
            $('body')
                    .removeClass("full-screen");
            $('#fullscreen-toggle')
                    .removeClass("active");
            if (document.exitFullscreen) {
                document.exitFullscreen();
            } else if (document.mozCancelFullScreen) {
                document.mozCancelFullScreen();
            } else if (document.webkitExitFullscreen) {
                document.webkitExitFullscreen();
            }
        }
    });
    /* ========================================================================= */
    /*  Expand side bar
     /* ========================================================================= */
    $(document).on('click', '#btn-expand-sidebar', function (e) {
        e.stopPropagation();
        if ($('#sidebar').hasClass('small-sidebar')) {
            $('#sidebar').removeClass('small-sidebar');
            $('#container').removeClass('lg-container');
        } else {
            $('#sidebar').addClass('small-sidebar');
            $('#container').addClass('lg-container');
        }
    });
    /* ========================================================================= */
    /*  Hide side bar
     /* ========================================================================= */
    $(document).on('click', '#hide-sidebar', function (e) {
        e.stopPropagation();
        if ($('#sidebar').hasClass('hidden-sidebar')) {
            $('#sidebar').removeClass('hidden-sidebar');
            $('#container').removeClass('sp-large-container');
        } else {
            $('#sidebar').addClass('hidden-sidebar');
            $('#container').addClass('sp-large-container');
        }
    });
    /* ========================================================================= */
    /*  Digital clock
     /* ========================================================================= */
    setInterval(function () {
        var seconds = new Date().getSeconds();
        var sdegree = seconds * 6;
        var srotate = "rotate(" + sdegree + "deg)";
        $("#clock .sec").css({"-moz-transform": srotate, "-webkit-transform": srotate});
    }, 1000);
    setInterval(function () {
        var hours = new Date().getHours();
        var mins = new Date().getMinutes();
        var hdegree = hours * 30 + (mins / 2);
        var hrotate = "rotate(" + hdegree + "deg)";
        $("#clock .hour").css({"-moz-transform": hrotate, "-webkit-transform": hrotate});
    }, 1000);
    setInterval(function () {
        var mins = new Date().getMinutes();
        var mdegree = mins * 6;
        var mrotate = "rotate(" + mdegree + "deg)";
        $("#clock .min").css({"-moz-transform": mrotate, "-webkit-transform": mrotate});
    }, 1000);
    /* ========================================================================= */
    /*  Note
     /* ========================================================================= */
    $(document).on('click', '.btn-select-text', function (e) {
        e.stopPropagation();
        $($(this).attr('data-target')).select();
    });
    $(document).on('click', '.btn-clear-text', function (e) {
        e.stopPropagation();
        $($(this).attr('data-target')).val('');
    });
    /* ========================================================================= */
    /*  Change Content
     /* ========================================================================= */
    $(document).on('click', '#sidebar .sidebar-nav>li>a.external', function (e) {
        e.preventDefault();
        if (!$(this).next('ul')) {
            return;
        }
        var parent = $(this).parent();
        if(!$(parent).hasClass('active')){
            $(parent).addClass('active');
            $(this).children('.item-expand').removeClass('fa-angle-down').addClass('fa-angle-up');
        }else{
            $(parent).removeClass('active');
            $(this).children('.item-expand').removeClass('fa-angle-up').addClass('fa-angle-down');
        }
    });
    $(document).on('click', '#sidebar .sidebar-nav>li>ul>li>a:not(.external)', function (e) {
        e.preventDefault();
        $('#sidebar .sidebar-nav>li>ul>li.active').removeClass('active');
        $(this).parent().addClass('active');
        var controller = $(this).attr('controller');
        if (typeof controller !== "undefined") {
            sendAjax(controller, 'GET', null, function (data) {
                $('#content').html(data);
            });
        }
    });
    /* ========================================================================= */
    /*  Open modal
     /* ========================================================================= */
    $(document).on('click', '.btn-open-modal', function (e) {
        e.preventDefault();
        var controller = $(this).attr('controller');
        if (typeof controller !== "undefined") {
            sendAjax(controller, 'GET', null, function (data) {
                $('body').append(data);
                $('#myModal').modal({show: true});
            });
        }
    });
    $(document).on('hidden.bs.modal', '.modal', function (event) {
        $(this).remove();
    });
    /* ========================================================================= */
    /*  Ajax Pagination
     /* ========================================================================= */
    $(document).on('change', '.display-per-page', function (e) {
        e.stopPropagation();
        if (typeof $(this).attr('controller') !== "undefined") {
            var url = $(this).attr('controller') + $(this).val();
            var target = $(this).parents('.ajax-content');
            sendAjax(url, 'GET', null, function (data) {
                $(target).html(data);
            });
        }
    });
    /* ========================================================================= */
    /*  Ajax Pagination
     /* ========================================================================= */
    $(document).on('change', '.change-date', function (e) {
        e.stopPropagation();
        if (typeof $(this).attr('controller') !== "undefined") {
            var url = $(this).attr('controller') + $(this).val();
            var target = $(this).parents('.panel-heading').next('.ajax-content');
            sendAjax(url, 'GET', null, function (data) {
                $(target).html(data);
            });
        }
    });
    $(document).on('click', '.table-grid-view th:not(.external)', function (e) {
        e.stopPropagation();
        if (typeof $(this).parent('tr').attr('controller') !== "undefined") {
            var url = $(this).parent('tr').attr('controller') + $(this).attr('column');
            var target = $(this).parents('.ajax-content');
            sendAjax(url, 'GET', null, function (data) {
                $(target).html(data);
            });

        }
    });
    $(document).on('click', 'form.pagination .btn:not(.disabled)', function (e) {
        e.stopPropagation();
        if (typeof $(this).parents('form.pagination').attr('action') !== "undefined") {
            var url = $(this).parents('form.pagination').attr('action') + $(this).attr('page');
            var target = $(this).parents('.ajax-content');
            sendAjax(url, 'GET', null, function (data) {
                $(target).html(data);
            });
        }
    });
    $(document).on('submit', 'form.pagination', function (e) {
        e.preventDefault();
        if (typeof $(this).attr('action') !== "undefined") {
            var url = $(this).attr('action') + $(this).find('input[type="number"]').val();
            var target = $(this).parents('.ajax-content');
            sendAjax(url, 'GET', null, function (data) {
                $(target).html(data);
            });
        }
    });
    $(document).on('hidden.bs.modal', '#myModal', function (e) {
        $(this).remove();
    });
    /* ========================================================================= */
    /*  Ajax Form Insert
     /* ========================================================================= */
    $(document).on('submit', '.form-insert', function (e) {
        e.preventDefault();
        var error = $(this).validate();
        if (!error) {
            var customer = JSON.stringify($(this).serializeObject());
            var url = $(this).attr('action');
            $.ajax({
                beforeSend: function () {
                    NProgress.set(0.5);
                },
                url: url,
                type: 'POST',
                data: customer,
                contentType: 'application/json',
                success: function (data) {
                    openMessage(data);
                    reloadAjaxContent();
                    NProgress.done();
                }, error: function () {
                    reloadAjaxContent();
                    NProgress.done();
                }
            });
        }
    }); /* ========================================================================= */
    /*  Ajax Form Search
     /* ========================================================================= */
    $(document).on('submit', '.form-search', function (e) {
        e.preventDefault();
        var searchString = $(this).find('[name=searchString]').val().trim();
        var keywords = [];
        $.each($(this).find('.dropdown-menu input[type=checkbox]:checked:not(.select-all)'), function () {
            keywords.push($(this).val());
        });
        var map = {"searchString": searchString, "keywords": keywords};
        var error = $(this).validate();
        if (!error && searchString && keywords.length) {
            var url = $(this).attr('action');
            $.ajax({
                beforeSend: function () {
                    NProgress.set(0.5);
                },
                url: url,
                type: 'POST',
                data: JSON.stringify(map),
                contentType: 'application/json;charset=UTF-8',
                success: function (data) {
                    $('#content .ajax-content').html(data);
                    NProgress.done();
                }, error: function () {
                    NProgress.done();
                }
            });
        }
    });
    $(document).on('submit', '.form-insert-normal', function (e) {
        e.preventDefault();
        var error = $(this).validate();
        if (!error) {
            var confirmAgain;
            if ($(this).attr('confirm')) {
                confirmAgain = confirm('Xác nhận lại?');
                if (!confirmAgain) {
                    return;
                }
            }
            var data = $(this).serializeObject();
            var url = $(this).attr('action');
            $.ajax({
                beforeSend: function () {
                    NProgress.set(0.5);
                },
                url: url,
                type: 'POST',
                data: data,
                success: function (data) {
                    openMessage(data);
                    reloadAjaxContent();
                    NProgress.done();
                }, error: function () {
                    reloadAjaxContent();
                    NProgress.done();
                }
            });
        }
    });
    $(document).on('click', '#message .close', function () {
        $('#message').fadeOut(300).remove();
    });
    $(document).on('click', '#distributor-view-tree li>a>.fa:not(.fa-user)', function (e) {
        e.preventDefault();
        if ($(this).parent().next('ul').is(":hidden")) {
            $(this).removeClass('fa-plus-square-o').addClass('fa-minus-square-o');
        } else {
            $(this).removeClass('fa-minus-square-o').addClass('fa-plus-square-o');
        }
        $(this).parent().next('ul').toggle(0);
    });

    $(document).on('click', '#distributor-view-tree .tree-handle .btn', function () {
        if ($(this).hasClass('btn-expand-all') && $('#distributor-view-tree>ul>li>ul').is(':hidden')) {
            $('#distributor-view-tree>ul>li>ul').toggle(0);
            $('#distributor-view-tree>ul>li>a>.fa').removeClass('fa-plus-square-o').addClass('fa-minus-square-o');
        } else if ($(this).hasClass('btn-hide-all') && !$('#distributor-view-tree>ul>li>ul').is(':hidden')) {
            $('#distributor-view-tree>ul>li>ul').toggle(0);
            $('#distributor-view-tree>ul>li>a>.fa').removeClass('fa-minus-square-o').addClass('fa-plus-square-o');
        }
    });
    //Auto complete
    var keyMap = {17: false, 65: false, 67: false, 86: false};
    var flag = true;
    $(document).on('keydown', '.block-auto-complete input[type="text"]', function (e) {
        if (e.keyCode in keyMap) {
            keyMap[e.keyCode] = true;
        }
        if (e.keyCode === 17 || (keyMap[17] && keyMap[65])
                || (keyMap[17] && keyMap[67])
                || (keyMap[17] && keyMap[86])
                || e.keyCode > 90) {
            flag = false;
        } else {
            flag = true;
        }
    }).on('keyup', '.block-auto-complete input[type="text"]', function (e) {
        if (e.keyCode in keyMap) {
            keyMap[e.keyCode] = false;
        }
        if (!flag) {
            return;
        }
        if ($(this).val().length >= 2) {
            var panel = $(this).parent().children('.panel-auto-complete');
            var target = $(this).parent().children('.panel-auto-complete').children('.panel-body');
            var controller = $(this).attr('controller') + $(this).val();
            var parent = $(this).attr('data-parent');
            if (parent) {
                var parentName = $(parent).val().trim();
                if ($(parent).val().trim()) {
                    controller += "/" + parentName;
                }
            }
            sendAjaxNormal(controller, 'GET', null, function (data) {
                $(target).html(data);
                $(panel).fadeIn(200);
            });
        } else {
            var panel = $(this).parent().children('.panel-auto-complete');
            $(panel).hide(0);
        }
    });
    $(document).on('mousedown', '.block-auto-complete .table>tbody>tr', function (e) {
        $(this).parents('.block-auto-complete').find('input[type="text"]').val($(this).attr('data-username'));
        $(this).parent().children('.panel-auto-complete').hide(0);
    });
    $(document).on('blur', '.block-auto-complete input[type="text"]', function (e) {
        $(this).parent().children('.panel-auto-complete').hide(0);
    });
    /*  Action item */
    $(document).on('mousedown', '.dropdown-menu-action li a:not(.external):not(.btn-open-modal)', function (e) {
        e.stopPropagation();
        var url = $(this).attr('controller');
        if (confirm('Bạn chắc chắn thực hiện hành động này?')) {
            sendAjax(url, 'GET', null, function (data) {
                openMessage(data);
                reloadAjaxContent();
            });
        }
    });
    /*Reload button*/
    $(document).on('click', '.btn-reload-content', function () {
        reloadAjaxContent();
    });
    $(document).ajaxComplete(function () {
        $('.tooltip').remove();
        $('[data-toggle="tooltip"]').tooltip({'enable': true, 'container': 'body'});
        $('.popover-dismiss').popover({
            trigger: 'hover',
            html: true,
            container: 'body'
        });
    });
    initHightChart();
    /**/
    $(document).on('click', '.btn-icon>a.btn-toggle', function (e) {
        var downIcon = 'ti-angle-down';
        var upIcon = 'ti-angle-up';
        var targetContent = $(this).parents('.panel-heading').next('.panel-body');
        var targetIcon = $(this).children('i');
        if ($(targetIcon).hasClass(upIcon)) {
            $(targetIcon).removeClass(upIcon).addClass(downIcon);
            $(targetContent).slideUp(300);
        } else {
            $(targetIcon).removeClass(downIcon).addClass(upIcon);
            $(targetContent).slideDown(300);
        }
    });
    $(document).on('click', '.btn-icon>a.btn-close', function (e) {
        e.preventDefault();
        var target = $(this).parents('.quick-dashboard');
        $(target).fadeOut(300, function () {
            $(target).remove();
        });
    });

    //multiple select
    $(document).on('click', '.dropdown-menu-multiple>li>a:not(.external,.btn-open-modal)', function () {
        var checkedBoxs;
        var data = [];
        var table = $(this).parents('.ajax-content').find('.table');
        checkedBoxs = $(table).find('input[type="checkbox"]:not(.external):not(.select-all):checked');
        checkedBoxs.each(function (i, target) {
            data.push(parseInt($(target).val()));
        });
        var url = $(this).attr('controller');
        if (typeof url !== 'undefined' && data.length) {
            if (!confirm("Xác nhận lại hành động này?")) {
                return;
            }
            $.ajax({
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                    NProgress.set(0.5);
                },
                url: url,
                type: 'POST',
                data: JSON.stringify(data),
                success: function (data) {
                    openMessage(data);
                    reloadAjaxContent();
                    NProgress.done();
                }, error: function () {
                    reloadAjaxContent();
                    NProgress.done();
                }
            });
//            sendAjax(url,'post',data,function(){
//                    openMessage(data);
//                    reloadAjaxContent();
//            });
        }
    });
});
wow = new WOW({
    animateClass: 'animated',
    offset: 100,
    mobile: false
});
wow.init();
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
function sendAjaxNormal(url, type, data, handle) {
    $.ajax({
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
    }, 3000);
    $('#message').fadeIn(200);
}
$.fn.serializeObject = function () {
    var o = {};
    $.each($(this).find('input:not(.external),select:not(.external),textarea:not(.external)'), function () {
        var v;
        if ($(this).attr('type') === 'checkbox') {
            v = $(this).is(':checked');
            o[$(this).attr('name')] = v;
        } else if ($(this).attr('type') === 'radio') {
            if ($(this).is(':checked')) {
                v = $(this).val();
                o[$(this).attr('name')] = v;
            }
        } else if ($(this).val() === '') {
            v = null;
            o[$(this).attr('name')] = v;
        } else if ($(this).attr('data-json')) {
            v = {};
            v[$(this).attr('data-json')] = $(this).val();
            o[$(this).attr('name')] = v;
        } else {
            v = $(this).val();
            o[$(this).attr('name')] = v;
        }
    });
    return o;
};
$.fn.validate = function () {
    var error = false;
    var msg = {
        'required': 'Vui lòng nhập trường này',
        'pattern': 'Yêu cầu thỏa mãn định dạng ',
        'min': 'Giá trị yêu cầu phải lớn hơn ',
        'max': 'Giá trị yêu cầu phải nhỏ hơn '
    };
    var props = {'data-toggle': "tooltip", 'data-placement': "top", 'data-original-title': "Error"};
    $.each($(this).find('.form-control:not(.external)'), function () {
        $(this).removeClass('error');
        if ($(this).attr('required') && $(this).val().trim().length === 0) {
            $(this).addClass('error');
            props['data-original-title'] = msg['required'];
            if ($(this).attr('data-original-title-required')) {
                props['data-original-title'] = $(this).attr('data-original-title-required');
            }
            $(this).attr(props);
            error = true;
        } else if ($(this).attr('pattern')
                && $(this).val().trim().length !== 0
                && !new RegExp($(this).attr('pattern')).test($(this).val())) {
            $(this).addClass('error');
            props['data-original-title'] = msg['pattern'];
            if ($(this).attr('data-original-title-pattern')) {
                props['data-original-title'] = $(this).attr('data-original-title-pattern');
            }
            $(this).attr(props);
            error = true;
        } else if ($(this).attr('type') === 'number' && $(this).val().trim().length !== 0
                && $(this).attr('min').length > 0
                && parseInt($(this).val()) < parseInt($(this).attr('min'))) {
            $(this).addClass('error');
            props['data-original-title'] = msg['min'] + $(this).attr('min');
            $(this).attr(props);
            error = true;
        } else if ($(this).attr('type') === 'number' && $(this).val().trim().length !== 0
                && $(this).attr('max').length > 0
                && parseInt($(this).val()) > parseInt($(this).attr('max'))) {
            $(this).addClass('error');
            props['data-original-title'] = msg['max'] + $(this).attr('max');
            $(this).attr(props);
            error = true;
        }
        if ($(this).hasClass('error')) {
            $(this).tooltip('show');
        } else {
            $(this).tooltip('hide');
            $(this).tooltip('destroy');
        }
    });
    return error;
};
function reloadAjaxContent() {
    var url = $('#reloadController').val();
    sendAjax(url, 'GET', null, function (data) {
        $('#content').html(data);
    });
}
$.fn.serializeArray = function () {
    var arr = [];
    if ($(this).attr('data-chart-type') === 'line' || $(this).attr('data-chart-type') === 'column') {
        $.each($(this).find('.serial'), function () {
            var map = {};
            map['name'] = $(this).attr('name');
            map['type'] = $(this).attr('display-type') === null ? 'column' : $(this).attr('display-type');
            map['yAxis'] = $(this).attr('yAxis') === '0' ? 0 : 1;
            map['tooltip'] = {valueSuffix: $(this).attr('data-type')};
            var data = $.parseJSON($(this).val());
            if ($(this).attr('data-type') !== '%') {
                $.each(data, function (key, value) {
                    data[key] = value * 10000;
                });
            }
            map['data'] = data;
            arr.push(map);
        });
    }
    if ($(this).attr('data-chart-type') === 'pie') {
        var max = 0;
        var i = 0;
        $.each($(this).find('.data'), function (index, value) {
            var map = {};
            map['name'] = $(this).attr('name');
            var y = parseInt($(this).attr('value'));
            map['y'] = y;
            if (y > max) {
                max = y;
                i = index;
            }
            arr.push(map);
        });
        arr[i]['sliced'] = true;
        arr[i]['selected'] = true;
    }
    return arr;
};

function initHightChart() {
    /*Draw chart*/
    Highcharts.createElement('link', {
        href: '//fonts.googleapis.com/css?family=Signika:400,700',
        rel: 'stylesheet',
        type: 'text/css'
    }, null, document.getElementsByTagName('head')[0]);
    Highcharts.theme = {
        colors: ["#f45b5b", "#8085e9", "#8d4654", "#55BF3B", "#aaeeee", "#ff0066", "#eeaaee",
            "#7798BF", "#DF5353", "#7798BF", "#aaeeee"],
        chart: {
            backgroundColor: '#FFF',
            style: {
                fontFamily: "Arial, serif",
                'margin': '0px auto'
            },
            borderColor: '#fff'
        },
        title: {
            style: {
                color: '#444',
                fontSize: '15px',
                'text-transform': 'uppercase'
            }, align: 'center'
        },
        subtitle: {
            style: {
                color: 'black'
            }
        },
        tooltip: {
            borderWidth: 0
        },
        legend: {
            itemStyle: {
                fontWeight: 'bold',
                fontSize: '13px'
            }
        },
        xAxis: {
            labels: {
                style: {
                    color: '#6e6e70'
                }
            }
        },
        yAxis: {
            labels: {
                style: {
                    color: '#6e6e70'
                }
            }
        },
        plotOptions: {
            series: {
                shadow: true
            },
            candlestick: {
                lineColor: '#404048'
            },
            map: {
                shadow: false
            }
        },
        // Highstock specific
        navigator: {
            xAxis: {
                gridLineColor: '#D0D0D8'
            }
        },
        rangeSelector: {
            buttonTheme: {
                fill: 'white',
                stroke: '#C0C0C8',
                'stroke-width': 1,
                states: {
                    select: {
                        fill: '#D0D0D8'
                    }
                }
            }
        },
        scrollbar: {
            trackBorderColor: '#C0C0C8'
        },
        // General
        background2: '#E0E0E8'
    };
// Apply the theme
    Highcharts.setOptions(Highcharts.theme);
    Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function (color) {
        return {
            radialGradient: {
                cx: 0.5,
                cy: 0.3,
                r: 0.7
            },
            stops: [
                [0, color],
                [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
            ]
        };
    });
    $.each($('body').find('.chart'), function () {
        var target = $(this);
        if ($(target).attr('data-chart-type') === 'column' || $(target).attr('data-chart-type') === 'line') {
            var yAxis = [];
            yAxis[0] = {// Primary yAxis
                labels: {
                    formatter: function () {
                        return Highcharts.numberFormat(this.value, 0) + 'VNĐ';
                    },
                    style: {
                        color: "rgb(141, 70, 84)",
                        'font-weight': 'bold'
                    }
                },
                title: {
                    text: $(target).attr('data-title-v-pri'),
                    style: {
                        color: "rgb(141, 70, 84)",
                        'font-weight': 'bold'
                    }
                }, min: 0
            };
            if ($(target).attr('data-yaxis-percent')) {
                yAxis[1] = {
                    title: {
                        text: null,
                        style: {
                            color: "#55BF3B"
                        }
                    },
                    labels: {
                        format: '{value} %',
                        style: {
                            color: "#55BF3B"
                        },
                        type: 'logarithmic',
                        minorTickInterval: 'auto'
                    },
                    opposite: true,
                    min: 0
                };
            }
            var xAxis = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
            $(target).highcharts({
                chart: {
                    type: $(target).attr('data-chart-type'),
                    zoomType: 'xy',
                    height: $(target).attr('data-height')
                },
                title: {
                    text: $(target).attr('data-title-h')
                },
                subtitle: {
                    text: $(target).attr('data-source')
                },
                tooltip: {
                    valueSuffix: $(target).attr('data-unit'),
                    shared: true
                },
                xAxis: {
                    categories: xAxis,
                    crosshair: true
                },
                yAxis: yAxis,
                plotOptions: {
                    line: {
                        dataLabels: {
                            enabled: $(target).attr('data-enabled')
                        },
                        enableMouseTracking: $(target).attr('data-enableMouseTracking')
                    }
                }, credits: {
                    enabled: false
                },
                series: $(target).serializeArray()
            });
        } else if ($(target).attr('data-chart-type') === 'pie') {
            $(target).highcharts({
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false,
                    type: 'pie',
                    height: 300
                },
                exporting: {
                    enabled: false
                },
                title: {
                    text: ''
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: false
                        },
                        showInLegend: true
                    }
                }, credits: {
                    enabled: false
                },
                series: [{
                        name: " abc",
                        colorByPoint: true,
                        data: $(target).serializeArray()
                    }]
            });
        }
    });
}