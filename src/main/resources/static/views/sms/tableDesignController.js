app = angular.module('tableDesignApp', []);
app.controller('tableDesignCtr', function ($scope) {
    tableDesignController.init($scope);
});

let tableDesignController = (function () {

    let TableDesignController = function () {
    }

    TableDesignController.prototype = {

        tableColumns: [],
        scope: null,
        model: {
            author: 'YY',
            moduleName: null,
            comment: null,
            tableName: null,
            flag: false
        },

        init: function (scope) {
            this.scope = scope;
            this.initModel();
            this.iunitColums();
            this.bindEvent();
        },

        initModel: function () {
            this.scope.tableColumns = this.tableColumns;
            this.scope.author = this.author;
            this.scope.moduleName = this.moduleName;
            this.scope.comment = this.comment;
            this.scope.tableName = this.tableName;
            this.scope.flag = this.flag;
            this.scope.model = this.model;
        },

        iunitColums: function () {
            let self = this;
            let tName = getQueryVariable("tableName");
            let tcomment = getQueryVariable("tableComment"), str;
            this.scope.model.tableName = tName;
            this.scope.model.comment = tcomment;
            this.scope.model = this.model;
            essAjax.default(getUrl(API_URL.tableInfo, 'columnList'), 'POST', {tableName: tName}, function (data) {
                self.tableColumns = data.data;
                self.tableColumns.forEach(function (item) {
                    ta:for (let i = 0; i < DATE_TYPE.length; i++) {
                        if (DATE_TYPE[i].jdbcType === item.dataType.toLowerCase()) {
                            item.javaType = DATE_TYPE[i].javaType;
                            break ta;
                        }
                    }
                    switch (item.columnName) {
                        case "id":
                            item.columnComment = "主键ID";
                            break;
                        case "create_user_id":
                            item.columnComment = "创建人ID";
                            break;
                        case "create_time":
                            item.columnComment = "主键ID";
                            break;
                        case "modify_user_id":
                            item.columnComment = "修改人ID";
                            break;
                        case "modify_time":
                            item.columnComment = "修改时间";
                            break;
                        case "del_flag":
                            item.columnComment = "删除标记";
                            break;
                    }
                })
                self.scope.tableColumns = self.tableColumns;
                self.scope.$apply()
            })
        },


        bindEvent: function () {
            let self = this;

            /**
             * 删除字段
             * @param index
             */
            this.scope.onClickDel = function (index) {
                self.tableColumns.splice(index, 1);
                self.scope.tableColumns = self.tableColumns;
            }

            /**
             * 生成代码
             */
            this.scope.onClickBuildCode = function () {
                self.model.tableInfoDTOList = self.tableColumns;
                essAjax.post(getUrl(API_URL.tableInfo, 'saveDesign'), self.model, function (data) {
                    alert(data.message);
                    Win10_child.close();
                })
            }
        }
    }
    return new TableDesignController();
})();