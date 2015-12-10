(function () {
  "use strict";
  angular
    .module('create-wedding')
    .directive('calendar', function () {
      return {
        restrict: 'EA',
        templateUrl: 'app/create-wedding/views/calendar.directive.html',
        link: function (scope, element, attributes) {



        }
      };
    });

})();
