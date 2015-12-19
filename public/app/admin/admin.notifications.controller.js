(function(){
"use strict"
angular
  .module('admin')
  .controller('NotControllers',function($scope,NotServices){
    $scope.refresh = function(){
    NotServices.getNot().success(function(res){
      $scope.notifications = res;
      });
    };
    $scope.refresh();
    var newDate = new Date();
    console.log(moment(newDate).calendar());

    $scope.formatDate = function(item){
      return moment(item).calendar()
    };
    $scope.postNot = function(){
      console.log('clicked');
      var dateTime = moment().calendar();
      var currObj = {
        title: $scope.title,
        email: $scope.email,
        text: $scope.text,
        notification: $scope.notification,
        time: new Date()
      }
      NotServices.postNot(currObj).success(function(res){
        $scope.notifications.push(res);
      })
    };
    $scope.deleteNot = function(item){
      console.log('hello');
      NotServices.deleteNot(item).success(function(){
        $scope.refresh();
      })
    }

  })
})();
