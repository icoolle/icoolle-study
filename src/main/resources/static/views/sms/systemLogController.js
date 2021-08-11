app = angular.module('systemLogApp', []);
app.controller('systemLogCtr', function ($scope) {
    systemLogController.init($scope);
});

let systemLogController = (function () {
    let SystemLogController = function () {
    }
    SystemLogController.prototype = {

        scope: null,
        searchModel: {
            keywords: null
        },
        columnDataList: [{
            key: 'modelName',
            remind: '模块名',
            align: 'center',
            text: '模块名',
        }, {
            key: 'description',
            remind: '描述',
            align: 'left',
            text: '描述',
        }, {
            key: 'modelUrl',
            remind: '接口地址',
            align: 'left',
            text: '接口地址',
        }, {
            key: 'targetName',
            remind: '接口类全称',
            align: 'left',
            text: '接口类全称',
        }, {
            key: 'targetArgs',
            remind: '请求参数',
            align: 'left',
            text: '请求参数',
        }, {
            key: 'targetResult',
            remind: '请求结果',
            align: 'left',
            text: '请求结果',
        }, {
            key: 'remoteAddr',
            remind: 'ip地址',
            align: 'left',
            text: 'ip地址',
        }, {
            key: 'logType',
            remind: '日志类型',
            text: '日志类型',
            align: 'center',
            template: function (type, rowObject) {
                return TYPE_MAP[type];
            }
        }, {
            key: 'browser',
            remind: '浏览器',
            text: '浏览器',
        }, {
            key: 'username',
            remind: '用户',
            align: 'center',
            text: '用户'
        }, {
            key: 'startTime',
            remind: '开始时间',
            text: '开始时间',
            // 使用函数返回 htmlString
            template: function (createDate, rowObject) {
                return new Date(createDate).toLocaleDateString();
            }
        }, {
            key: 'consumTime',
            remind: '消耗时长',
            text: '消耗时长',
        }, {
            key: 'finishTime',
            remind: '完成时间',
            text: '完成时间',
            // 使用函数返回 htmlString
            template: function (lastDate, rowObject) {
                return new Date(lastDate).toLocaleDateString();
            }
        }],

        tableData: null,


        init: function (scope) {
            this.scope = scope;
            this.initModel();
            this.initSystemLogList();
            this.bindEvent();
        },

        initModel: function () {
            this.scope.columnDataList = this.columnDataList;
            this.scope.tableData = this.tableData;
            this.scope.searchModel = this.searchModel;
        },

        initSystemLogList: function () {
            let self = this;
            // GridManager 渲染
            self.tableData = document.querySelector('table');
            self.tableData.GM({
                gridManagerName: 'test'
                , height: '90vh'
                , ajaxHeaders: {'Content-Type': 'application/json'}
                , supportAjaxPage: true
                , isCombSorting: false
                , disableCache: false
                , currentPageKey: "pageNum"
                , pageSizeKey: "pageSize"
                , totalsKey: "total"
                , dataKey: "list"
                , ajaxData: function (setting) {
                    // 传入分页及排序的配置项
                    //return getBlogList(Object.assign({}, setting.pageData, setting.sortData));
                    return "./../../../syslog/list_log";
                }
                , ajaxType: 'POST'
                , supportMenu: true
                , pageSize: 10
                , useWordBreak: true
                , columnData: self.columnDataList
                , responseHandler: function (response) {
                    console.log(response);
                    response.list = response.data.dataList;
                    response.total = response.data.total;
                    return response;
                },
            });

        },

        bindEvent: function () {
            let self = this;

            self.scope.onClickSearch = function () {
                self.tableData.GM('setQuery', self.searchModel);
            }

            self.scope.onClickReset = function () {
                self.searchModel.keywords = "";
                self.tableData.GM('setQuery', self.searchModel);
            }


        }
    }
    return new SystemLogController();
})()