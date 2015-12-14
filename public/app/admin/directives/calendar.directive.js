(function () {
  "use strict";
  angular
    .module('admin')
    .directive('calendarDirective', function () {
      return {
        restrict: 'EA',
        transclue: true,
        templateUrl: '/app/admin/views/calendar.directive.html',
        link: function (scope, element, attributes) {


        }
      };
    });

})();
