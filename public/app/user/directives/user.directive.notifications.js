(function () {
  "use strict";
  angular
    .module('user')
    .directive('userNotifications', function () {
      return {
        restrict: 'EA',
        transclue: true,
        templateUrl: '/app/user/views/user.notifications.html',
        link: function (scope, element, attributes) {
          // <span class="fc-icon fc-icon-right-single-arrow"></span>
          // element.getElementsByClass
          // element.css('color','red');
        }
      };
    });

})();
