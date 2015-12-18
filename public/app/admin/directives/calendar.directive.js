(function () {
  "use strict";
  angular
    .module('admin')
    .directive('calendarDirective', function () {
      return {
        restrict: 'EA',
        transclue: true,
        templateUrl: '/app/admin/views/calendar.directive.html',
        link: function (scope, element, attributes) {
          // <span class="fc-icon fc-icon-right-single-arrow"></span>
          // element.getElementsByClass
          // element.css('color','red');
        }
      };
    });

})();
