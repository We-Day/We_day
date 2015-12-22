(function(){
  "use strict";
  var underscore = angular.module('underscore',[]);
    underscore.factory('_',function(){
      return window._;
    });
    angular
      .module('landing',[
        'ngRoute',
        'underscore'
      ])
      .config(function ($routeProvider) {
      $routeProvider
        .when('/landingPage', {
          templateUrl: 'app/landingPage/views/landing.list.html',
          controller: 'LandingController as landingCtrl'
        })

    })

})();
