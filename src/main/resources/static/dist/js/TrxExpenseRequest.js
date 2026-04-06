var editorD;

function expenseEdit(id) {
    window.location.href = "/expense/detail?requestId=" + id;
}

$(function() {

    //エラー表示欄の隠す
    $('.alert-danger').css("display", "none");

    //テキストエディター
    const E = window.wangEditor;
    editorD = new E('#wangEditor')
    // エディターパラメータの設定
    editorD.config.height = 400
    editorD.config.uploadImgServer = 'images/upload'
    editorD.config.uploadFileName = 'file'
    editorD.config.uploadImgMaxSize = 2 * 1024 * 1024
    editorD.config.uploadImgMaxLength = 1
    editorD.config.showLinkImg = false
    editorD.config.uploadImgHooks = {
        // アップロード成功
        success: function (xhr) {
            console.log('success', xhr)
        },
        // アップロード失敗
        fail: function (xhr, editor, resData) {
            console.log('fail', resData)
        },
        // アップロードエラー
        error: function (xhr, editor, resData) {
            console.log('error', xhr, resData)
        },
        // タイムアウト処理
        timeout: function (xhr) {
            console.log('timeout')
        },
        customInsert: function (insertImgFn, result) {
            if (result != null && result.resultCode == 200) {
                insertImgFn(result.data)
            } else {
                alert("error");
            }
        }
    }
    editorD.create();

    $('#attendanceModal').modal('hide');

    $("#jqGrid").jqGrid({
        url: '/expense/list',
        datatype: "json",
        colModel: [
            {label: 'ID', name: 'id', index: 'id', width: 50, key: true, hidden: true},
            {label: '社員ID', name: 'empId', index: 'empId', width: 80, align: 'center'},
            {label: '社員名', name: 'empName', index: 'empName', width: 100},
            {label: '部門コード', name: 'departmentCode', index: 'departmentCode', width: 100},
            {label: '申請年月', name: 'requestDate', index: 'requestDate', width: 100, align: 'center'},
            {
                label: '定期交通費',
                name: 'regularTransportAmount',
                index: 'regularTransportAmount',
                width: 100,
                align: 'right',
                formatter: 'number'
            },
            {
                label: '不定期交通費',
                name: 'nonregularTransportAmount',
                index: 'nonregularTransportAmount',
                width: 100,
                align: 'right',
                formatter: 'number'
            },
            {
                label: 'その他経費',
                name: 'otherExpenseAmount',
                index: 'otherExpenseAmount',
                width: 100,
                align: 'right',
                formatter: 'number'
            },
            {label: '総計', name: 'totalAmount', index: 'totalAmount', width: 100, align: 'right', formatter: 'number'},
            {
                label: '承認状態', name: 'approvalStatus', index: 'approvalStatus', width: 80, align: 'center',
                formatter: function (cellvalue) {
                    switch (cellvalue) {
                        case '0':
                            return '未承認';
                        case '1':
                            return '承認済';
                        case '2':
                            return '却下';
                        default:
                            return '不明';
                    }
                }
            },
            {
                label: '公開フラグ', name: 'publicFlg', index: 'publicFlg', width: 80, align: 'center',
                formatter: function (cellvalue) {
                    return cellvalue === '1' ? '公開' : '非公開';
                }
            },
            {
                label: '編集フラグ', name: 'editFlg', index: 'editFlg', width: 80, align: 'center',
                formatter: function (cellvalue) {
                    return cellvalue === '1' ? '可' : '不可';
                }
            },
            {label: '備考', name: 'remarks', index: 'remarks', width: 150},
            {
                label: '操作', name: 'actions', index: 'actions', width: 100, align: 'center', sortable: false,
                formatter: function (cellvalue, options, rowObject) {
                    return '<a href="javascript:void(0);" onclick="expenseEdit(' + rowObject.id + ')" class="btn btn-sm btn-info">編集</a>';
                }
            }
        ],
        height: 'auto',
        rowNum: 10,
        rowList: [10, 20, 50],
        styleUI: 'Bootstrap',
        loadtext: '取込中。。。。',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",     // 数据列表
            page: "data.currPage",  // 当前页
            total: "data.totalPage",   // 总页数
            records: "data.totalCount", // 总条数
            repeatitems: false
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order",
        },
        gridComplete: function () {
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });


})


    function searchReimburseList() {
        var searchData = {
            startMonth: $('#startMonth').val(),
            endMonth: $('#endMonth').val(),
            department: $('#department').val(),
            employeeId: $('#employeeId').val(),
            employeeName: $('#employeeName').val(),
        };


        $("#jqGrid").jqGrid('setGridParam', {
            url: "/expense/info",
            postData: searchData,
            datatype: 'json',
            page: 1,
            loadComplete: function(response) {
                // 根据后端返回的实际字段判断
                var rows = response.rows || response.data; // 兼容不同后端
                if (!rows || rows.length === 0) {
                    alert("未找到匹配数据，请检查输入内容！");
                }
            }
        }).trigger("reloadGrid");
    }

function deleteExpense() {
	var rowIds = $("#jqGrid").jqGrid("getGridParam", "selarrrow");

	   if (!rowIds || rowIds.length === 0) {
	       swal("请选择数据", { icon: "warning" });
	       return;
	   }

	   var ids = [];

	   rowIds.forEach(function (rowId) {
	       var rowData = $("#jqGrid").jqGrid("getRowData", rowId);
	       ids.push(parseInt(rowData.id));
	   });

	   var token = $("meta[name='_csrf']").attr("content");
	   var header = $("meta[name='_csrf_header']").attr("content");

	   swal({
	       title: "确认删除？",
	       icon: "warning",
	       buttons: true,
	       dangerMode: true,
	   }).then((flag) => {
	       if (!flag) return;

	       var url = (ids.length === 1) ? "/expense/delete" : "/expense/deleteBatch";

	       var data = (ids.length === 1)
	           ? { id: ids[0] }
	           : { ids: ids };

	       $.ajax({
	           type: "POST",
	           url: url,
	           contentType: "application/json",
	           data: JSON.stringify(data),

	           beforeSend: function (xhr) {
	               xhr.setRequestHeader(header, token);
	           },

	           success: function (res) {
	               if (res.resultCode === 200) {
	                   swal("删除成功", { icon: "success" });
	                   $("#jqGrid").trigger("reloadGrid");
	               } else {
	                   swal("删除失败", { icon: "error" });
	               }
	           },

	           error: function () {
	               swal("请求失败", { icon: "error" });
	           }
	       });
	   });
}

function expenseExport() {
	// 👉 获取所有选中的行
	   var rowIds = $("#jqGrid").jqGrid("getGridParam", "selarrrow");

	   if (!rowIds || rowIds.length === 0) {
	       swal("行を選択してください", { icon: "warning" });
	       return;
	   }

	   var ids = [];

	   rowIds.forEach(function (rowId) {
	       var rowData = $("#jqGrid").jqGrid("getRowData", rowId);
	       ids.push(parseInt(rowData.id));
	   });

	   if (ids.length === 1) {
	       window.location.href = "/expense/export?requestId=" + ids[0];
	       return;
	   }

	   // 👉 多个 → ZIP下载
	   var form = $("<form method='get' action='/expense/exportZip'></form>");

	   ids.forEach(function (id) {
	       form.append('<input type="hidden" name="ids" value="' + id + '">');
	   });

	   $(document.body).append(form);
	   form.submit();
	   form.remove();
}

function expenseAdd() {
  document.getElementById("modal").style.display = "block";
}

function nextStep() {
  if (!document.getElementById("name").value.trim() ||
      !document.getElementById("user").value.trim() ||
      !document.getElementById("date").value.trim() ||
      !document.getElementById("desc").value.trim()) {
    alert("主表不能为空");
    return;
  }

  document.getElementById("step1").style.display = "none";
  document.getElementById("step2").style.display = "block";
}

function addDetail() {
  let div = document.createElement("div");

  div.innerHTML = `
    <input placeholder="行先">
    <input placeholder="区間">
    <input placeholder="交通手段">
    <input placeholder="片往定">
    <input placeholder="事由">
    <input placeholder="金額">
    <input placeholder="支払い先">
    <input placeholder="勘定科目">
	<input placeholder="摘要">
	<input placeholder="金額">
    <hr>
  `;

  document.getElementById("details").appendChild(div);
}

function submit() {
  let inputs = document.querySelectorAll("#details input");

  if (inputs.length === 0) {
    alert("请添加明细");
    return;
  }

  for (let input of inputs) {
    if (!input.value.trim()) {
      alert("明细不能为空");
      return;
    }
  }

  alert("可以提交后端了");

  // TODO: 这里接你的后端
}


