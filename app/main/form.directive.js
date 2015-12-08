(function () {
  "use strict";
  angular
    .module('main')
    .directive('myForm', function () {
      return {
        restrict: 'EA',
        templateUrl: 'app/main/views/form.directive.html',
        link: function (scope, element, attributes) {
          var submit =   angular.element(document.querySelector('#submit'));
          var wrapper=  angular.element(document.querySelector('#wrapper'));
          var form =   angular.element(document.getElementsByTagName('form'));
          submit.on('click',function(el){
            el.preventDefault();
            form.css('display','none');
            angular.element(document.querySelector('#wrapper')).css('display','block')
            console.log('hello this is clicked');
          });

        }
      };
    });

})();
