(function () {
  "use strict";
  angular
    .module('admin')
    .directive('myNotification', function () {
      return {
        restrict: 'EA',
        transclue: true,
        templateUrl: 'app/admin/directives/views/notification.directive.html',
        link: function (scope, element, attributes) {


        }
      };
    });

})();
