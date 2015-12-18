(function(){
"use strict"
angular
  .module('admin')
  .controller('NotController',function($scope,NotServices){
    $scope.refresh = function(){
    NotServices.getNot().success(function(res){
      $scope.notifications = res;
      });
    }
    $scope.refresh();
    $scope.postNot = function(){
      console.log('clicked');
      var dateTime = moment().format('MMMM Do YYYY, h:mm:ss a');
      var currObj = {
        title: $scope.title,
        email: $scope.email,
        text: $scope.text,
        notification: $scope.notification,
        time: dateTime
      }
      NotServices.postNot(currObj).success(function(res){
        $scope.notifications.push(res);
      })
    };
    $scope.deleteNot = function(item){
      NotServices.deleteNot(item).success(function(){
        $scope.refresh();
      })
    }

  })
})();
