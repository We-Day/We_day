(function(){
"use strict"
angular
  .module('main')
  .controller('LoginController',function($location,$scope,LoginService){


    // var users = [];
    // LoginService.getUsers().success(function(res){
    //   var users = res;
    // })
    $scope.loginUser = function(email,password){
      LoginService.isInvitedToWedding(email, password).success(function(res){
        console.log('res',res)
        if(!res[1]){
          $location.path('/create-weddings')
        }else{
          $location.path('/landingPage');
        }
      });
    };

    });
})();
