(function(){
"use strict"
angular
  .module('user')
  .controller('UserController',function($scope,UserService){
    $scope.currentIndex = 1;
    $scope.currentUser = 'Charles'
    $scope.setCurrentIndex = function(index){
      $scope.currentIndex = index;
      console.log($scope.currentIndex)
    }
    $scope.isCurrentIndex = function(index){
      if(index === $scope.currentIndex){
        return true;
      }else{
        return false;
      }
    };

  });
})();
