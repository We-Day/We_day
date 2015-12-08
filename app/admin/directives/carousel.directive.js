(function () {
  "use strict";
  angular
    .module('admin')
    .directive('myCarousel', function () {
      return {
        restrict: 'EA',
        transclue: true,
        templateUrl: 'app/admin/directives/views/carousel.directive.html',
        link: function (scope, element, attributes) {


        }
      };
    });

})();
