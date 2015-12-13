(function () {
  "use strict";

  var underscore = angular.module('underscore', []);
          underscore.factory('_', function() {
              return window._; //Underscore should be loaded on the page
          });


  angular
    .module('main', [
      'ngRoute',
      'ui.router',
      'underscore',
      'admin',
      'ngAnimate',
      'ui.bootstrap',
      'create-wedding',
    ])
    .config(function ($routeProvider) {
      $routeProvider
        .when('/', {
          templateUrl: 'app/main/views/list.html',
          controller: 'MainController as mainCtrl'
        })
        .when('/404', {
          template: '<img src= "http://www.theinquirer.net/IMG/129/231129/fbi-web-site-seized-image.jpg"/>'
        })
        .otherwise({ redirectTo: '/404'})

    })





})();
