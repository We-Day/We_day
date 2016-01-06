(function(){
"use strict"
angular
  .module('user')
  .controller('NotificationsController',function($scope,NotServices,$routeParams){
    $scope.refresh = function(){
    NotServices.getNot($routeParams.weddingId).success(function(res){
      $scope.notifications = res;
      $scope.notifications.length > 0 ? $scope.areNotifications = true : $scope.areNotifications = false;

      });
    };
    $scope.refresh();
    $scope.formatDate = function(item){
      return moment(item).calendar()
    };
  })
})();
