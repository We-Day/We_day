(function(){
"use strict"
angular
  .module('main')
  .controller('LoginController',function($location,$scope,LoginService){


    var users = [];
    LoginService.getUsers().success(function(res){
      var users = res;
    })
    $scope.loginUser = function(email,password){
      LoginService.isInvitedToWedding(email, password).success(function(res){
        console.log('res',res)
        if(!res){
          $location.path('/create-wedding')
        }else{
          $location.path('/landingPage/'+el.id);
        }
      });
    };
    $scope.reRoute = function(){
      $location.path('landingPage/1');
    }
    });
})();
