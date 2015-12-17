(function(){
"use strict"
angular
  .module('main')
  .controller('MainController',function($scope,MainService){
    $scope.currentView = 0;
    $scope.setCurrentView = function(num){
      $scope.currentView = num;
    };
    $scope.isCurrentView = function(num){

      return $scope.currentView === num ? true:false;
    }
  });
})();
