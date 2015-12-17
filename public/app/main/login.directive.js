(function () {
  "use strict";
  angular
    .module('main')
    .directive('loginForm', function () {
      return {
        restrict: 'EA',
        templateUrl: 'app/main/views/login.directive.html',
        link: function (scope, element, attributes) {

        }
      };
    });

})();
