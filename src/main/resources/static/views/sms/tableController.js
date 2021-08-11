app = angular.module('tableApp', []);
app.controller('tableCtr', function ($scope) {
    tableController.init($scope);
});

let tableController = (function () {

    let TableController = function () {
    }

    TableController.prototype = {

        scope: null,
        tableColumns: [
            {field: 'categoryCode', title: '分类编号'},
            {field: 'categoryCode', title: '分类编号'},
            {field: 'categoryCode', title: '分类编号'},
            {field: 'categoryCode', title: '分类编号'},
            {field: 'categoryCode', title: '分类编号'},
            {field: 'categoryCode', title: '分类编号'},
            {field: 'categoryCode', title: '分类编号'},
        ],

        tableList: [],
        listParam: {
            "keywords": "",
            "pageNum": 1,
            "pageSize": 300,
            "primaryId": 0,
            "primaryName": ""
        },


        init: function (scope) {
            this.scope = scope;
            this.initModel();
            this.initTableList();
            this.bindEvent();
        },

        initModel: function () {
            this.scope.tableList = this.tableList;
            this.scope.listParam = this.listParam;
        },


        initTableList: function () {
            let self = this;
            let str = '<thead></thead><tr>'

            this.tableColumns.forEach(function (item) {
                str += '<th>' + item.title + '</th>'
            });
            str += '</tr>'
            essAjax.post(getUrl(API_URL.tableInfo, 'listTable'), self.listParam, function (data) {
                self.tableList = data.data.dataList;
                self.scope.tableList = self.tableList;
                self.scope.$apply()
            })

        },

        bindEvent: function () {
            let self = this;

            this.scope.onClickSearch = function () {
                essAjax.post(getUrl(API_URL.tableInfo, 'listTable'), self.listParam, function (data) {
                    self.tableList = data.data.dataList;
                    self.scope.tableList = self.tableList;
                    self.scope.$apply();
                })
            }

            this.scope.onClickDesign = function (item) {
                Win10_child.openUrl('./static/views/sms/tableDesign.html?tableName=' + item.tableName + '&tableComment=' + item.tableComment, '<img class=\'icon\' src=\'./static/img/icon/blogger.png\'/>代码设计')
            }

        }

    }
    return new TableController();
})();