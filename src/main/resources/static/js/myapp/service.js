'use strict';
var AppUpload = angular.module('AppUpload', []);

AppUpload.factory('foodService', ['$http', '$q', 'urls', function($http, $q, urls){

    var factory = {

        saveFood: saveFood,
        findFood: findFood,
        updateFood: updateFood
    };
    return factory;

    //update data food
    function updateFood(data){

        var deferred = $q.defer();
        if(data.id != null){
           $http.put(urls.FOOD_URL+'update', data)
           .then(function(response){
                deferred.resolve(response.data);
           }, function(errResponse){
              alert(errResponse.data.errorMessage);
              deferred.reject(errResponse);
           });
           return deferred.promise;
        }
    }

    //save data food
    function saveFood(file){

        var deferred = $q.defer();
        var formData = new FormData();
        var myForm = document.getElementById('myForm');
        formData = new FormData(myForm);

        $http.post(urls.FOOD_URL+'upload', formData,{
            transformRequest: angular.identity,
            headers: {
                'Content-Type': undefined
            }
        }).then(function(response){
            deferred.resolve(response.data);
        },function(errResponse){
            alert(errResponse.data.errorMessage);
            deferred.reject(errResponse);
        }
       );
       return deferred.promise;
    };

    //find food by id
    function findFood(id){
        var deferred = $q.defer();
        $http.get(urls.FOOD_URL+'foods/'+id)
            .then(function(response){
                deferred.resolve(response.data)
            }, function(errResponse){
                alert(errorMessage.data.errorMessage);
                deferred.reject(errorMessage);
            });
            return deferred.promise;
        }
  }]);