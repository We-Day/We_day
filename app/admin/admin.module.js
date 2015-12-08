(function(){
  "use strict";
  var underscore = angular.module('underscore',[]);
    underscore.factory('_',function(){
      return window._;
    });
    angular
      .module('admin',[
        'ngRoute',
        'underscore'
      ])
      .config(function ($routeProvider) {
      $routeProvider
        .when('/admin:userId', {
          templateUrl: 'app/admin/views/admin.list.html',
          controller: 'AdminController as adminCtrl'
        })
        .when('/admin',{
          templateUrl:'app/admin/views/admin.list.html',
          controller:'AdminController as adminCtrl'
        })
    })

})();
