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

          var rightArrow = document.getElementsByClassName('fc-icon-right-single-arrow');

          console.log(rightArrow);
          // <span class="fc-icon fc-icon-right-single-arrow"></span>
          // element.getElementsByClass
          // element.css('color','red');
        }
      };
    });

})();
