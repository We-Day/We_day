(function(){
"use strict"
angular
  .module('main')
  .controller('LoginController',function($location,$scope,LoginService){
    var users = [];
    LoginService.getUsers().success(function(res){
      users.push(res);
    });
    $scope.loginUser = function(email,password){
      console.log('loginUser');
      console.log(email,'email');
      console.log('login users',users[0]);
      _.each(users[0],function(el){
        console.log(el);
        if(el.email === email){
          if(el.password === password){
            $location.path('/landingPage/'+el.id);
            return true;
          }
        }else{
          return false;}
      });
    };
  });
})();
