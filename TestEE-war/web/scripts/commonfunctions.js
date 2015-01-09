
Application = function () {
    var _ = {
        Controller: {
            CENTRAL_CONTROLLER: '/TestEE-war/controller',
            DATA_SERVICE: '/TestEE-war/webresources/dataservice',
            EDIT_USER: "/TestEE-war/html/useredit.html"
        },
        callAJAX: function (paramObj) {
            $.ajax({
                url: paramObj.url,
                type: paramObj.type,
                data: paramObj.data,
                success: paramObj.success,
                error: paramObj.error
            });
        },
        createTable: function (id, data, columns, checkbox, dsid) {
            $("#" + id).html("");
            $("#" + id).append("<table class='place-left' style='margin-top: 30px'><tr><td><button id='addUser'><i class='icon-plus-2'/></button></td><td><button><i class='icon-minus-2'/></button></td></tr></table>");
            $("#" + id).append(_.getRecordSelect());
            $("#" + id).append('<table id="datatable"></table>');
            $("#" + id).append(_.getRecordSelect());
            $("#" + id).append("<table class='place-left'><tr><td><button id='addUser'><i class='icon-plus-2'/></button></td><td><button><i class='icon-minus-2'/></button></td></tr></table>");
            $("#" + id + " #datatable").attr("class", "table striped bordered hovered");
            $("#" + id + " #datatable").append("<thead id='head1'><tr></tr></thead>");
            if (checkbox) {
                $("#" + id + " #datatable #head1 tr").append("<th><div>" + _.getCheckBoxHTML("chk" + id + "all") + "</div></th>");
            }
            for (var key in columns) {
                $("#" + id + " #datatable #head1 tr").append("<th>" + columns[key] + "</th>");
            }
            $("#" + id + " #datatable").append("<tbody></tbody>");
            if (data.records.length > 0) {
                for (var i in data.records) {
                    var record = "<tr>";
                    if (checkbox) {
                        record += "<td>" + this.getCheckBoxHTML("chk" + data.records[i].id) + "</td>";
                    }
                    for (var key in data.records[i]) {
                        record += "<td>" + data.records[i][key] + "</td>";
                    }
                    record += "</tr>";
                    $("#" + id + " #datatable tbody").append(record);
                }
            } else {
                $("#" + id + " #datatable tbody").append("<tr><td colspan='" + (checkbox ? columns.length + 1 : columns.length) + "'>No Record Found</td></tr>");
            }
            $("#" + id + " #datatable").append("<thead id='head2'><tr></tr></thead>");
            if (checkbox) {
                $("#" + id + " #datatable #head2 tr").append("<th><div>" + _.getCheckBoxHTML("chk" + id + "all") + "</div></th>");
            }
            for (var key in columns) {
                $("#" + id + " #datatable #head2 tr").append("<th>" + columns[key] + "</th>");
            }
            $("#addUser").unbind("click").bind("click", function () {
                _.callAJAX({
                    url: Application.Controller.EDIT_USER,
                    type: "POST",
                    success: function (data) {
                        $.Dialog({
                            overlay: true,
                            shadow: true,
                            flat: true,
                            width: 400,
                            padding: 20,
                            title: 'Edit User',
                            content: '',
                            onShow: function (_dialog) {
                                var content = _dialog.children('.content');
                                content.html(data);
                            }
                        });
                    }
                });
            });
            $(".data-record").append("<option value='10'>10</option>");
            $(".data-record").append("<option value='25'>25</option>");
            $(".data-record").append("<option value='50'>50</option>");
            $(".data-record").append("<option value='100'>100</option>");
            $(".data-record").val(data.currentrecords);
            $(".data-record").unbind("change").bind("change", function (e) {
                _.callAJAX({
                    url: Application.Controller.CENTRAL_CONTROLLER,
                    type: "POST",
                    data: {dsid: dsid, start: 0, end: $(".data-record").val() - 1},
                    success: function (data) {
                        console.log(data);
                        Application.createTable("tablediv", data, ["Sr", "Request URL", "Remote Address", "User Agent", "Remote Host Name", "Request Time"], true, dsid);
                    }
                });
            });
        },
        getCheckBoxHTML: function (id, selected) {
            return '<div class="input-control checkbox" data-role="input-control"><label class="inline-block"><input id=' + id + ' type="checkbox"><span class="check"></span></label><div>';
        },
        getRecordSelect: function (id) {
            return '<table class="span4" style="float:right;"><tr><td><div class="place-right"><table><tr><td><label><Strong>Records:</Strong></label></td><td><div class="input-control select nbm"><select class="data-record"/></div></td></tr></table></div></td></tr><tr><td><div class="pagination place-right"><ul><li class="first"><a><i class="icon-first-2"/></a></li><li class="prev"><a><i class="icon-previous"/></a></li><li><div class="input-control select nbm"><select/></div></li><li class="next"><a><i class="icon-next"/></a></li><li class="last"><a><i class="icon-last-2"/></a></li></ul></div></td></tr></table>';
        }
    };
    return{
        createTable: function (id, data, columns, checkbox, dsid) {
            return _.createTable(id, data, columns, checkbox, dsid);
        },
        Controller: _.Controller,
        callAJAX: function (paramObj) {
            return _.callAJAX(paramObj);
        }
    };
}();
