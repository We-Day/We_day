(function () {
  "use strict";
  angular
    .module('admin')
    .directive('calendarDirective', function () {
      return {
        restrict: 'EA',
        transclue: true,
        template: '<h1>Charles</h1>',
        link: function (scope, element, attributes) {


        }
      };
    });

})();
