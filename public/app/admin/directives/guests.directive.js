(function () {
  "use strict";
  angular
    .module('admin')
    .directive('myGuests', function () {
      return {
        restrict: 'EA',
        transclude: true,
        controller: 'AdminController',
        templateUrl: 'app/admin/directives/views/guests.direcive.html',
        link: function (scope, element, attributes) {


        }
      };
    });

})();
