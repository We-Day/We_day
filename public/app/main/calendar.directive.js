(function () {
  "use strict";
  angular
    .module('main')
    .directive('mainCalendarDirective', function () {
      return {
        restrict: 'EA',
        transclude: true,
        templateUrl: '/app/main/calendar.directive.html',
        link: function (scope, element, attributes) {


        }
      };
    });

})();
