(function(){
"use strict"
angular
  .module('user')
  .controller('UserCalendarController',function($scope,UserCalendarService){
      $scope.startDate="2015-10-19";
      $scope.endDate="2016-02-19";
      $scope.events = [{
    badgeClass: 'info',
    badgeIconClass: 'glyphicon-check',
    title: 'First heading',
    content: 'Some awesome content.'
  }, {
    badgeClass: 'warning',
    badgeIconClass: 'glyphicon-credit-card',
    title: 'Second heading',
    content: 'More awesome content.'
  }];
  })
})();
