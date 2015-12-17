(function () {
  "use strict";
  angular
    .module('main')
    .directive('registerForm', function () {
      return {
        restrict: 'EA',
        templateUrl: 'app/main/views/form.directive.html',
        link: function (scope, element, attributes) {


        }
      };
    });

})();
