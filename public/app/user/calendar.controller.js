(function(){
"use strict"
angular
  .module('user')
  .controller('UserCalendarController',function($scope,UserCalendarService){
      $scope.startDate="2015-10-19";
      $scope.endDate="2016-02-19";
  })
})();
