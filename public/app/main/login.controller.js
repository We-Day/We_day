(function(){
"use strict"
angular
  .module('main')
  .controller('LoginController',function($scope,LoginService){
    var users = [];
    LoginService.getUsers().success(function(res){
      var users = res;
    })
    $scope.loginUser = function(email,password){
      _.each(users,function(el){
        if(el.email === email){
          if(el.password === password){
            $location.path('/landingPage/'+el.id);
            return true;
          }
        }return false;
        }
      })
    }
  });
})();
