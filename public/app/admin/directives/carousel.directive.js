(function () {
  "use strict";
  angular
    .module('admin')
    .directive('userCarousel', function () {
      return {
        restrict: 'EA',
        transclude: true,
        templateUrl: 'app/admin/directives/views/carousel.directive.html',
        link: function (scope, element, attributes) {


        }
      };
    });

})();
