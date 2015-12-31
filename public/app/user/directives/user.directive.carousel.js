(function () {
  "use strict";
  angular
    .module('user')
    .directive('userCarousel', function () {
      return {
        restrict: 'EA',
        templateUrl: 'app/user/views/user.carousel.html',
        link: function (scope, element, attributes) {


        }
      };
    });

})();
