(function(){
"use strict"
angular
  .module('admin')
  .controller('NotControllers',function($scope,NotServices){
    var inputNotifcation = '';
    $scope.toLong = function(){
      return inputNotifcation.length > 115 ? true :false
    }
    $scope.updateLength = function(item){
      inputNotifcation = item;
    }
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
      $scope.notifications.push(res);
      NotServices.postNot(currObj).success(function(res){
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
