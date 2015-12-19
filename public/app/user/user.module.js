(function () {
  "use strict";

  var underscore = angular.module('underscore', []);
          underscore.factory('_', function() {
              return window._; //Underscore should be loaded on the page
          });


  angular
    .module('user', [
      'ngRoute',
      'ui.router'
    ])
    .config(function ($routeProvider) {
      $routeProvider
        .when('/user/:weddingId', {
          templateUrl: 'app/user/views/user.list.html',
          controller: 'UserController'
        })


    })





})();
