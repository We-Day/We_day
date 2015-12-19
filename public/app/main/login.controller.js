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
      LoginService.isInvitedToWedding(email, password).success(function(res,other,it){
        console.log('res',res,other,it)
        if(!res){
          $location.path('/create-wedding')
        }else{
          $location.path('/landingPage/'+res.id);
        }
      });
    };

    });
})();
