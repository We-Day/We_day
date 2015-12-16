(function(){
"use strict"
angular
  .module('main')
  .controller('RegisterController',function($window,$location,$scope,RegisterService){
    $scope.passwordsAreSame = true;

    $scope.addNewUser = function(){
      var currObj = {
        username: $scope.userName,
        email: $scope.email,
        phone: $scope.phone,
        password: $scope.password
      }
      if($scope.password == $scope.passwordAuth){
        RegisterService.addNewUser(currObj).success(function(res){
          console.log('res',res);
          $window.location.reload();
          console.log('addedNewUser',res);
        });
      }
      else{alert('wrong password')};
    };
    $scope.createAdminInputsEmpty = function(){
      console.log('createAdminInputsEmpty')
      if($scope.userName && $scope.email && $scope.phone&& $scope.password){
        return true;
      }else{
        return false;
      }
    };
    $scope.passwordsSame = function(){
      console.log('passwords the same');
      if($scope.password == $scope.passwordAuth){
        return true;
      }else{
        $scope.passwordsAreSame = false;
        return false
      }
    }
  });
})();
