//создаем ангуляр модуль ('имя приложения в html', [подключаемая библиотека]).имя контроллера в html
angular.module('web-shop', ['ngStorage'])
    .controller('productController', function ($scope, $http, $location, $localStorage) {

        const contextPath = 'http://localhost:8080/api/v1'

        $scope.loadAllCategories = function () {
            $http({
                url: "/api/v1/category",
                method: "GET",
            }).then(function (response) {
                $scope.categories = response.data;
            });
        };

        $scope.loadProduct = function (productId) {
            $http({
                url: "/api/v1/product/one",
                method: "GET",
                params: {
                    id: productId
                }
            }).then(function (response) {
                $scope.modalProduct = response.data;
            });
        };

        $scope.loadProducts = function (pageNumber) {
            $http({
                url: "/api/v1/product/all",
                method: "GET"
            }).then(function (response) {
                $scope.products = response.data;
            });
        };

        $scope.saveProduct = function () {
            $http.post(contextPath + "/product/save", $scope.modalProduct)
                .then(function successCallback(response) {
                    $scope.loadProducts(1);
                }, function errorCallback(response) {
                });
            $(".modal-backdrop").hide();
            $('#modalFormProduct').modal('hide');
        };

        $('#modalFormProduct').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget);
            var id = button.data('productId');
            if (id == null){
                var modal = $(this)
                modal.find('.modal-body input').val("")
                return;
            }
            $scope.loadProduct(id);
            $scope.loadAllCategories();
        })

        $('#modalFormProduct').on('hide.bs.modal', function (event){
            //событие при закрытии модалки
        })

        $scope.addToCart = function (product) {
            console.log(product);
            $http.post(contextPath + "/cart/add", product)
                .then(function successCallback(response) {
                }, function errorCallback(response) {
                });
        };

        $scope.loadProducts(1)
    });