$(document).ready(function () {
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
            sendAjaxNormal(controller, function (data) {
                $(target).html(data);
                $(panel).show(0);
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
    //
    $('body').on('click.collapse-next.data-api', '[data-toggle=collapse-next]', function (e) {
        var target = $(this).next();
        $(target).toggle(300);
    });
    $(window).scroll(function () {
        if ($(window).scrollTop() > 184) {
            $("#gotoTop").fadeIn(300);
        } else {
            $("#gotoTop").fadeOut(300);
        }
    });
    $(document).on('click', '#gotoTop', function () {
        $('html,body').animate({scrollTop: 0}, 300);
    });

    $('.pin').mask('000-00-0000-0000-0000', {selectOnFocus: true});

    $(document).on('submit', '.form-insert', function (e) {
        e.preventDefault();
        var error = $(this).validate();
        if (!error && typeof $(this).attr('action') !== 'undefined') {
            var customer = JSON.stringify($(this).serializeObject());
            var url = $(this).attr('action');
            sendAjaxWithJsonObj(url, 'POST', customer, function (data) {
                openMessage(data);
            });
        }
    });

    $(document).on('submit', '.form-insert-normal', function (e) {
        e.preventDefault();
        var error = $(this).validate();
        if (error) {
            return;
        }
        var confirmAgain;
        if ($(this).attr('confirm')) {
            confirmAgain = confirm('Xác nhận lại?');
            if (!confirmAgain) {
                return;
            }
        }
        var data = $(this).serializeObject();
        var url = $(this).attr('action');
        var target = $(this);
        $.ajax({
            beforeSend: function () {
                NProgress.set(0.5);
            },
            url: url,
            type: 'POST',
            data: data,
            success: function (data) {
                openMessage(data);
                NProgress.done();
                $(target).reset();
            }, error: function () {
                NProgress.done();
            }
        });
    });

    $(document).on('click', '.btn-change-content', function () {
        if (typeof $(this).attr('controller') !== "undefined") {
            sendAjax($(this).attr('controller'), function (data) {
                changeContent(data);
            });
        }
    });

    $(document).on('click', '.tree-horizontal li>a', function () {
        if ($(this).next('ul').is(":hidden")) {
            $(this).children('.fa:not(.fa-user)').removeClass('fa-plus-square-o').addClass('fa-minus-square-o');
        } else {
            $(this).children('.fa:not(.fa-user)').removeClass('fa-minus-square-o').addClass('fa-plus-square-o');
        }
        $(this).next('ul').toggle(0);
    });
    $(document).on('click', '.tree-handle>.btn', function () {
        var target = $('.tree-horizontal>ul>li>a>.fa');
        if ($(this).hasClass('btn-expand-all') && $('.tree-horizontal>ul>li>ul').is(':hidden')) {
            $(target).removeClass('fa-plus-square-o').addClass('fa-minus-square-o');
            $('.tree-horizontal>ul ul').toggle(0);
        } else if ($(this).hasClass('btn-hide-all') && !$('.tree-horizontal>ul>li>ul').is(':hidden')) {
            $(target).removeClass('fa-minus-square-o').addClass('fa-plus-square-o');
            $('.tree-horizontal>ul ul').toggle(0);
        }
    });
    $(document).on('click', '#message .close', function () {
        $('#message').fadeOut(300).remove();
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
    $(document).on('click', '.table-grid-view th:not(.external)', function (e) {
        e.stopPropagation();
        var url = $(this).parent('tr').attr('controller') + $(this).attr('column');
        var target = $(this).parents('.ajax-content');
        sendAjax(url, function (data) {
            $(target).html(data);
        });
    });
    $(document).on('click', 'form.pagination .btn:not(.disabled)', function (e) {
        e.stopPropagation();
        var url = $(this).parents('form.pagination').attr('action') + $(this).attr('page');
        var target = $(this).parents('.ajax-content');
        sendAjax(url, function (data) {
            $(target).html(data);
        });
    });
    $(document).on('submit', 'form.pagination', function (e) {
        e.preventDefault();
        var url = $(this).attr('action') + $(this).find('input[type="number"]').val();
        var target = $(this).parents('.ajax-content');
        sendAjax(url, function (data) {
            $(target).html(data);
        });
    });
    $(document).on('click', '.btn-open-diagram', function (e) {
        e.preventDefault();
        if ($('#myModal').length === 0) {
            var controller = $(this).attr('controller');
            if (typeof controller !== "undefined") {
                sendAjax(controller, function (data) {
                    $('body').append(data);
                    var datas = $.parseJSON($('#myDiagram').attr('datas'));
                    initDiagram(datas);
                    $('#myModal').modal({show: true});
                });
            }
        } else {
            $('#myModal').modal({show: true});
        }
    });
    $(document).ajaxComplete(function () {
        $('[data-toggle="tooltip"]').tooltip({container: 'body'});
    });
    $(document).on('click', '.btn-send-ajax', function (e) {
        e.preventDefault();
        var controller = $(this).attr('controller');
        if (typeof controller !== 'undefined') {
            if ($(this).attr('confirm')) {
                if (!confirm("Xác nhận lại")) {
                    return;
                }
            }
            sendAjax(controller, function (data) {
                openMessage(data);
            });
        }
    });
    //change breadcum
    $(document).on('click', '#ajax-nav ul>li>a', function () {
        var grandText = $(this).html();
        var childText = $(this).parents('.panel').find('.panel-heading>a').html();
        $('#breadcrumb .breadcrumb>li:eq(2)>a').html(childText);
        $('#breadcrumb .breadcrumb>li:eq(3)').html(grandText);
    });
    /* ========================================================================= */
    /*  Ajax change date
     /* ========================================================================= */
    $(document).on('change', '.change-date', function (e) {
        e.stopPropagation();
        if (typeof $(this).attr('controller') !== "undefined") {
            var url = $(this).attr('controller') + $(this).val();
            var target = $(this).parents('.panel-body').find('.ajax-content');
            sendAjax(url, function (data) {
                $(target).html(data);
            });
        }
    });
});
wow = new WOW({
    animateClass: 'animated',
    offset: 100,
    mobile: false
});
wow.init();
function sendAjaxNormal(url, handle) {
    $.ajax({
        url: url,
        type: 'GET',
        data: null,
        success: function (data) {
            if (typeof handle !== "undefined") {
                handle(data);
            }
        }
    });
}
function sendAjax(url, handle) {
    $.ajax({
        beforeSend: function () {
            NProgress.set(0.5);
        },
        url: url,
        type: 'GET',
        data: null,
        success: function (data) {
            if (typeof handle !== "undefined") {
                handle(data);
            }
            NProgress.done();
        },
        error: function () {
            NProgress.done();
        },
    });
}
function sendAjaxWithJsonObj(url, type, data, handle) {
    $.ajax({
        beforeSend: function () {
            NProgress.set(0.5);
        },
        url: url,
        type: type,
        data: data,
        contentType: 'application/json',
        success: function (data) {
            if (typeof handle !== "undefined") {
                handle(data);
            }
            NProgress.done();
        }, error: function () {
            NProgress.done();
        }
    });
}
function sendAjaxWithMultipartData(url, type, data, handle) {
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
    $('body').append(data);
    intervalDisplayMessage = setInterval(function () {
        $('#message').remove();
    }, 5000);
    $('#message').fadeIn(300);
}
$.fn.serializeObject = function () {
    var o = {};
    $.each($(this).find('input:not(.external),select:not(.external),textarea:not(.external)'), function () {
        if (typeof $(this).attr('name') !== 'undefined') {
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
        }
    });
    return o;
};
function changeContent(data) {
    $('#ajax-content').html(data);
}
$.fn.reset = function () {
    $(this).each(function () {
        this.reset();
    });
};
function initDiagram(datas) {
    var $ = go.GraphObject.make;  // for conciseness in defining templates
    myDiagram =
            $(go.Diagram, "myDiagram", {
                allowCopy: false,
                allowDelete: false,
                allowMove: false,
                initialDocumentSpot: go.Spot.TopCenter,
                initialViewportSpot: go.Spot.TopCenter,
                "undoManager.isEnabled": true,
                layout: $(go.TreeLayout, {
                    treeStyle: go.TreeLayout.StyleLastParents,
                    angle: 90,
                    layerSpacing: 30,
                    alternateAngle: 0,
                    alternateAlignment: go.TreeLayout.AlignmentStart,
                    alternateNodeIndent: 20,
                    alternateNodeIndentPastParent: 1,
                    alternateNodeSpacing: 20,
                    alternateLayerSpacing: 20,
                    alternateLayerSpacingParentOverlap: 1,
                    alternatePortSpot: new go.Spot(0, 0.999, 20, 0),
                    alternateChildPortSpot: go.Spot.Left
                })
            });

    function theInfoTextConverter(info) {
        var str = "";
        if (info.userName) {
            str += "\nTên: " + info.name;
        }
        if (info.levelName) {
            str += "\nChức vụ: " + info.levelName;
        }
        if (typeof info.circle !== "undefined") {
            str += "\nChu kỳ: " + info.circle.toString();
        }
        if (typeof info.pVLeft !== "undefined") {
            str += "\nTrái: " + addCommas((info.pVLeft * 1000).toString()) + ' VNĐ';
        }
        if (typeof info.pVRight !== "undefined") {
            str += "\nPhải: " + addCommas((info.pVRight * 1000).toString()) + ' VNĐ';
        }
        return str;
    }
    var gs = $(go.Brush, "Linear", {0: "rgb(75, 108, 183", 1: "rgb(24, 40, 72)"});
    var ctv = $(go.Brush, "Linear", {0: "rgb(170, 7, 107)", 1: "rgb(97, 4, 95)"});
    var npp = $(go.Brush, "Linear", {0: "rgb(82, 194, 52)", 1: "rgb(29, 151, 108)"});

    // Set up a Part as a legend, and place it directly on the diagram
    myDiagram.add($(go.Part, "Table", {
        position: new go.Point(550, 10),
        selectable: false
    }, $(go.TextBlock, "Chức vụ", {
        row: 0,
        font: "bold 10pt Helvetica, Arial, sans-serif",
        stroke: "#fff"
    }), $(go.Panel, "Horizontal", {
        row: 1,
        alignment: go.Spot.Left
    }, $(go.Shape, "Rectangle", {
        desiredSize: new go.Size(30, 30),
        fill: ctv,
        margin: 5
    }), $(go.TextBlock, "CTV", {
        font: "bold 8pt Helvetica, bold Arial, sans-serif",
        stroke: "#fff"
    })), $(go.Panel, "Horizontal", {
        row: 2, alignment: go.Spot.Left
    }, $(go.Shape, "Rectangle", {
        desiredSize: new go.Size(30, 30),
        fill: npp,
        margin: 5
    }), $(go.TextBlock, "NPP", {
        font: "bold 8pt Helvetica, bold Arial, sans-serif",
        stroke: "#fff"
    })), $(go.Panel, "Horizontal", {
        row: 3, alignment: go.Spot.Left
    }, $(go.Shape, "Rectangle", {
        desiredSize: new go.Size(30, 30),
        fill: gs,
        margin: 5
    }), $(go.TextBlock, "Giám Sát", {
        font: "bold 8pt Helvetica, bold Arial, sans-serif",
        stroke: "#fff"
    }))));
    function cvBrushConverter(cv) {
        if (cv === "CTV")
            return ctv;
        if (cv === "NPP")
            return npp;
        if (cv === "GiamSat")
            return gs;
        return "orange";
    }
    // define the Node template
    myDiagram.nodeTemplate = $(go.Node, "Auto", {isTreeExpanded: false, isTreeLeaf: true, isShadowed: false, selectionAdorned: false},
//            $(go.Shape, "RoundedRectangle", new go.Binding("fill", "isHighlighted", function (h) {
//                return h ? "#009688" : "rgba(255,255,255,0.75)";
//            }).ofObject(), {strokeWidth: 1, stroke: "rgba(0,0,0,0.1)", strokeMiterLimit: 1, strokeJoin: "bevel"}),
            $(go.Shape, "RoundedRectangle", {
                name: "SHAPE", fill: "white", stroke: null,
                // set the port properties:
                portId: "", fromLinkable: true, toLinkable: true
            }, new go.Binding("fill", "levelName", cvBrushConverter)),
            // a table to contain the different parts of the node
            $(go.Panel, "Table", {
                name: "PANEL",
                margin: 0,
                maxSize: new go.Size(170, NaN)},
                    $(go.RowColumnDefinition, {
                        column: 0,
                        stretch: go.GraphObject.Horizontal,
                        alignment: go.Spot.Left,
                        separatorStrokeWidth: 0,
                        separatorStroke: "transparent",
                        separatorPadding: 0
                    }),
                    // the name
                    $(go.TextBlock, {
                        row: 0, column: 0,
                        maxSize: new go.Size(150, NaN),
                        margin: 5,
                        font: "bold 14px Arial",
                        stroke: "#FFF",
                        alignment: go.Spot.LeftCenter
                    }, new go.Binding("text", "userName")),
                    $(go.Picture, {
                        row: 0, column: 1,
                        margin: 5,
                        source: "/resources/img/user.svg",
                        desiredSize: new go.Size(30, 30),
                        imageStretch: go.GraphObject.Uniform,
                        alignment: go.Spot.TopRight
                    }),
                    $(go.TextBlock, {
                        row: 1, column: 0,
                        columnSpan: 10,
                        maxSize: new go.Size(160, NaN),
                        margin: 5,
                        font: "12px Arial",
                        stroke: "#FFF"
                    }, new go.Binding("text", "", theInfoTextConverter)),
                    $("TreeExpanderButton", {
                        name: "EXPAND",
                        row: 2,
                        columnSpan: 99,
                        width: 20,
                        height: 20,
                        alignment: go.Spot.Center,
                        visible: false
                    }))  // end Table Panel
            );
    myDiagram.addDiagramListener("InitialLayoutCompleted", function (e) {
        myDiagram.findTreeRoots().each(function (r) {
            r.expandTree(6);
        });
    });


    // define the Link template, a simple orthogonal line
    myDiagram.linkTemplate = $(go.Link,
            go.Link.Orthogonal, {selectable: false},
            $(go.Shape, {strokeWidth: 3, stroke: '#FFF'}));  // the default black link shape


    // set up the nodeDataArray, describing each person/position

    // create the Model with data for the tree, and assign to the Diagram
    myDiagram.model = $(go.TreeModel, {
        nodeParentKeyProperty: "boss",
        nodeDataArray: datas
    });

    document.getElementById('zoomToFit').addEventListener('click', function () {
        myDiagram.zoomToFit();
    });

    document.getElementById('centerRoot').addEventListener('click', function () {
        myDiagram.scale = 1;
        myDiagram.scrollToRect(myDiagram.findNodeForKey(datas[0]['key']).actualBounds);
    });

    // Overview
    myOverview = $(go.Overview, "myOverview", {
        observed: myDiagram,
        contentAlignment: go.Spot.Center,
        drawsTemporaryLayers: false
    });
}

function searchDiagram() {  // called by button
    var input = document.getElementById("mySearch");
    if (!input)
        return;
    input.focus();

    // create a case insensitive RegExp from what the user typed
    var regex = new RegExp(input.value, "i");

    myDiagram.startTransaction("highlight search");
    myDiagram.clearHighlighteds();

    // search four different data properties for the string, any of which may match for success
    var results = myDiagram.findNodesByExample({key: regex},
            {name: regex},
            {levelName: regex});
    myDiagram.highlightCollection(results);
    // try to center the diagram at the first node that was found
    if (results.count > 0)
        myDiagram.centerRect(results.first().actualBounds);

    myDiagram.commitTransaction("highlight search");
}
$.fn.serializeArray = function () {
    var arr = [];
    var i = 0;
    $.each($(this).find('input[type=hidden]'), function () {
        arr.push($.parseJSON($(this).val()));
        i = i + 1;
    });
    return arr;
};
function addCommas(nStr) {
    nStr += '';
    x = nStr.split('.');
    x1 = x[0];
    x2 = x.length > 1 ? '.' + x[1] : '';
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {
        x1 = x1.replace(rgx, '$1' + ',' + '$2');
    }
    return x1 + x2;
}
$.fn.validate = function () {
    var error = false;
    var msg = {
        'required': 'Vui lòng nhập trường này',
        'pattern': 'Yêu cầu thỏa mãn định dạng ',
        'min': 'Giá trị yêu cầu phải lớn hơn ',
        'max': 'Giá trị yêu cầu phải nhỏ hơn ',
        'match': 'Không khớp '
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
        } else if ($(this).attr('type') === 'password' && $(this).val().trim().length !== 0
                && $(this).attr('match') && $($(this).attr('match')).val() !== $(this).val()) {
            $(this).addClass('error');
            props['data-original-title'] = msg['match'] + 'mật khẩu ở trên';
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
    sendAjax(url, function (data) {
        $('#ajax-content').html(data);
    });
}
