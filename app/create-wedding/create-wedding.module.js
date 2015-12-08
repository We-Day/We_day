(function () {
  "use strict";

  var underscore = angular.module('underscore', []);
          underscore.factory('_', function() {
              return window._; //Underscore should be loaded on the page
          });


  angular
    .module('create-wedding', [
      'ngRoute',
    ])
    .config(function ($routeProvider) {
      $routeProvider
        .when('/create-wedding', {
          templateUrl: 'app/create-wedding/views/create-wedding.list.html',
          controller: 'CreateWeddingController'
        })

    })





})();
