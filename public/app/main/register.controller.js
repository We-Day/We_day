(function(){
"use strict"
angular
  .module('main')
  .controller('RegisterController',function($scope,RegisterService){
    $scope.passwordsAreSame = true;

    $scope.addNewUser = function(){
      var currObj = {
        userName: $scope.userName,
        email: $scope.email,
        phone: $scope.phone,
        password: $scope.password
      }
      console.log('currObj',currObj);
      if($scope.password == $scope.passwordAuth){
        RegisterService.addNewUser(currObj);
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
