(function () {
  "use strict";
  angular
    .module('user')
    .directive('userGuests', function () {
      return {
        restrict: 'EA',
        transclude: true,
        controller: 'UserController',
        templateUrl: 'app/user/views/user.guests.html',
        link: function (scope, element, attributes) {


        }
      };
    });

})();
