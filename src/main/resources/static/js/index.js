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
                console.log(response);
            });
        };

        $scope.loadProduct = function (productId) {
            console.log(productId)
            $http({
                url: "/api/v1/product/one",
                method: "GET",
                params: {
                    id: productId
                }
            }).then(function (response) {
                $scope.modalProduct = response.data;
                console.log(response)
            });
        };

        $scope.loadProducts = function (pageNumber) {
            console.log("call")
            $http({
                url: "/api/v1/product/all",
                method: "GET"
            }).then(function (response) {
                $scope.products = response.data;
                console.log($scope.products)
            });
        };

        $scope.saveProduct = function () {
            console.log($scope.modalProduct)
            $http.post(contextPath + "/product/save", $scope.modalProduct)
                .then(function successCallback(response) {
                    console.log(response);
                    $scope.loadProducts(1);
                }, function errorCallback(response) {
                    console.log(response);
                });
        };

        $('#modalFormProduct').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget);
            var id = button.data('productId');
            $scope.loadProduct(id);
            $scope.loadAllCategories();
        })

        $scope.addToCart = function (product) {
            console.log(product);
            $http.post(contextPath + "/cart/add", product)
                .then(function successCallback(response) {
                    console.log(response);
                }, function errorCallback(response) {
                    console.log(response);
                });
        };

        $scope.loadProducts(1)
    });