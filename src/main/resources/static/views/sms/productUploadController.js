app = angular.module('productAddApp', []);
app.controller('productAddCtr', function ($scope) {
    productAddController.init($scope);
});

let productAddController = (function () {
    let ProductAddController = function () {
    }
    ProductAddController.prototype = {

        scope: null,
        formData: new FormData(),
        addModel: {
            productName: null,
            brandId: null,
            supplierId: null,
            categoryId: null,
            parentCategoryId: null
        },
        parentCategoryList: [],
        categoryList: [],
        brandList: [],
        supplierList: [],

        init: function (scope) {
            this.scope = scope;
            this.initModel();
            this.bindEvent();
        },

        initModel: function () {
            const word = prompt("请输入登陆使用的token", "");
            if (word) {
                localStorage.setItem("token", word);
            }
            this.scope.columnDataList = this.columnDataList;
            this.scope.tableData = this.tableData;
            this.scope.addModel = this.addModel;
            this.scope.formData = this.formData;
            this.getParentCategoryList();
            this.getBrandList();
            this.getSupplierList();
        },

        getParentCategoryList: function () {
            let self = this;
            let param = {
                categoryLevel: 1,
                pageNum: 1,
                pageSize: 100
            }
            essAjax.post(getUrl(API_URL.category, 'list'), param, function (data) {
                self.parentCategoryList = data.data.dataList;
                self.scope.parentCategoryList = self.parentCategoryList;
                self.scope.$apply()
            })
        },

        getBrandList: function () {
            let self = this;
            let param = {
                pageNum: 1,
                pageSize: 200
            }
            essAjax.post(getUrl(API_URL.brand, 'list'), param, function (data) {
                self.brandList = data.data.dataList;
                self.scope.brandList = self.brandList;
                self.scope.$apply()
            })
        },
        getSupplierList: function () {
            let self = this;
            let param = {
                pageNum: 1,
                pageSize: 500
            }
            essAjax.post(getUrl(API_URL.supplier, 'list'), param, function (data) {
                self.supplierList = data.data.dataList;
                self.scope.supplierList = self.supplierList;
                self.scope.$apply()
            })
        },


        bindEvent: function () {
            let self = this;
            self.scope.onClickSave = function () {
                let files = document.getElementById('files').files;
                for (const file of files) {
                    console.log(file);
                    self.formData.append("files", file);
                }
                self.formData.append("productName", self.addModel.productName);
                self.formData.append("brandId", self.addModel.brandId);
                self.formData.append("supplierId", self.addModel.supplierId);
                self.formData.append("categoryId", self.addModel.categoryId);
                essAjax.uploadPost(getUrl(API_URL.product, 'add'), self.formData, function (data) {
                    self.formData = new FormData();
                    self.scope.formData = self.formData;
                    self.scope.$apply();
                    alert(data.message)
                })
            }

            self.scope.onChangeParentCategory = function (id) {
                let param = {
                    categoryLevel: 2,
                    pageNum: 1,
                    pageSize: 100,
                    parentId: id
                }
                essAjax.post(getUrl(API_URL.category, 'list'), param, function (data) {
                    self.categoryList = data.data.dataList;
                    self.scope.categoryList = self.categoryList;
                    self.scope.$apply()
                })
            }
        }
    }
    return new ProductAddController();
})();