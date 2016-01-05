(function(){
"use strict"
angular
  .module('admin')
  .controller('NotControllers',function($scope,NotServices,$routeParams){
    var inputNotifcation = '';

    $scope.toLong = function(){
      return inputNotifcation.length > 110 ? true :false
    }
    $scope.updateLength = function(item){
      inputNotifcation = item;
    }
    $scope.notifications = [];
    $scope.refresh = function(){
    NotServices.getNot($routeParams.weddingId).success(function(res){
      console.log(res,'notifications')
      $scope.notifications = res;
      });
    };
    $scope.refresh();
    var newDate = new Date();
    console.log(moment(newDate).calendar());

    $scope.formatDate = function(item){
      return moment(item).calendar()
    };
    $scope.postNot = function(email,text){
      console.log('clicked');
      var dateTime = moment().calendar();
      var currObj = {
        title: $scope.title,
        notificationEmail: email,
        text: text,
        wedId: $routeParams.weddingId,
        // notification: $scope.notification,
        time: new Date()
      }
      $scope.notifications.push(currObj);
      NotServices.postNot(currObj).success(function(res){
        console.log(currObj,'notification');
        $scope.title = '';
      })
    };
    $scope.deleteNot = function(item,index){
      console.log('hello');
      console.log(index)
      $scope.notifications.splice(index,1);
      NotServices.deleteNot(item).success(function(){
      })
    }

  })
})();
