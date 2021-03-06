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
      'ngAnimate',
      'ui.bootstrap',
      'ngSanitize',
      'angular-timeline',
      'ui.calendar',
      'textAngular',
      'admin',
      'create-wedding',
      'landing',
      'user'
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
