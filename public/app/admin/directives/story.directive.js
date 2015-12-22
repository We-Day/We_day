(function () {
  "use strict";
  angular
    .module('admin')
    .directive('storyDirective', function () {
      return {
        restrict: 'EA',
        transclue: true,
        templateUrl: '/app/admin/directives/views/story.directive.html',
        link: function (scope, element, attributes) {


          // <span class="fc-icon fc-icon-right-single-arrow"></span>
          // element.getElementsByClass
          // element.css('color','red');
        }
      };
    });

})();
