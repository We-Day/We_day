(function(){
"use strict"
angular
  .module('user')
  .controller('NotificationsController',function($scope,NotServices){
    $scope.refresh = function(){
    NotServices.getNot().success(function(res){
      $scope.notifications = res;
      });
      $scope.notifications.length > 0 ? $scope.areNotifications = true : $scope.areNotifications = false;
    };
    $scope.refresh();
    $scope.formatDate = function(item){
      return moment(item).calendar()
    };
  })
})();
