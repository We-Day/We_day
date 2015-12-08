(function () {
  "use strict";
  angular
    .module('main')
    .directive('myLogin', function () {
      return {
        restrict: 'EA',
        templateUrl: 'app/main/views/login.directive.html',
        link: function (scope, element, attributes) {
          element.on('click',function(element){
            element.preventDefault();
            console.log('clicked');
            var look = angular.element(document.querySelector('#wrapper'));
            var form = angular.element(document.getElementsByTagName('form'));
            angular.element(document.querySelector('#wrapper')).css('display','none');
            form.css('display','block');
          });

        }
      };
    });

})();
