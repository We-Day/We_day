(function () {
  "use strict";
  angular
    .module('main')
    .directive('mainCalendarDirectives', function () {
      return {
        restrict: 'EA',
        templateUrl: '/app/main/calendar.directive.html',
        link: function (scope, element, attributes) {


        }
      };
    });

})();
