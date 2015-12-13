(function () {
  "use strict";

  var underscore = angular.module('underscore', []);
          underscore.factory('_', function() {
              return window._; //Underscore should be loaded on the page
          });


  angular
    .module('create-wedding', [
      'ngRoute',
      'ui.router'
    ])
    .config(function ($routeProvider) {
      $routeProvider
        .when('/create-weddings', {
          templateUrl: 'app/create-wedding/views/create-wedding.list.html',
          controller: 'CreateWeddingController'
        })
        .when('/connect/facebook#_=_',{
          redirectTo: "create-weddings"
        })

    })





})();
