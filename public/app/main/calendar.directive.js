(function () {
  "use strict";
  angular
    .module('main')
    .directive('mainCalendarDirective', function () {
      return {
        restrict: 'EA',
        transclue: true,
        templateUrl: '/app/main/calendar.directive.html',
        link: function (scope, element, attributes) {
          
        }
      };
    });

})();
