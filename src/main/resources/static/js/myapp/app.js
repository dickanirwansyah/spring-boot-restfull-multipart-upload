AppUpload.controller('ControllerUpload',
['$scope', '$rootScope', 'foodService', '$http', function($scope, $rootScope, foodService, $http){

    $scope.file = '';
    $scope.name = {};

    $scope.fixSaved = function(){
        var file = $scope.file;

        foodService.saveFood(file)
        .then(function(response){
            $("#formFood").modal('hide');
            alert("file berhasil disimpan !");
            $http.get("http://localhost:8080/multipart/api/foods")
            .success(function(response){
                $rootScope.foodList = response;
            });
        }, function(errResponse){
        });
     };

     $scope.updateFix = function(){

        var name = $scope.name;
        foodService.updateFood(name).then(function(response){
            alert("data berhasil diupdate !");
            $('#formUpdate').modal('hide');
            $http.get("http://localhost:8080/multipart/api/foods")
            .success(function(response){
                $rootScope.foodService = response;
            });
        }, function(errResponse){
        });
     };

    $scope.editFood = function(data){
        foodService.findFood(data.id).then(sukses, gagal);

        function sukses(response){
            console.log(response);
            $scope.name = data;
        };

        function gagal(response){
            console.log(response);
            alert("bad !");
        };
      }
   }
]);

AppUpload.constant('urls', {

    FOOD_URL: 'http://localhost:8080/multipart/api/'
});

AppUpload.directive('fileModel', ['$parse', function($parse){

    return{
        restrict: 'A',
        link: function(scope, element, attrs){
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function(){

                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);


AppUpload.run(function($rootScope, $http){

    $http.get("http://localhost:8080/multipart/api/foods").success(
        function(response){
            $rootScope.foodList = response;
     });
});