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

        _.each(users,function(el){
          if(el.email === email){
            if(el.password === password){
              if(res === 1){
                $location.path('/create-wedding')
              }else{
                $location.path('/landingPage/'+el.id);
              }
              return true;
            }
          }else{
            return false;}
        });
      });
    };
    $scope.reRoute = function(){
      $location.path('landingPage/1');
    }
    });
})();
