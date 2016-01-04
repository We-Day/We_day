(function(){
  "use strict"
  angular
    .module('landing')
    .factory('LandingService',function($http,$routeParams){
      var urlWeddings = '/invites';
      var urlWeddingsUser = '/landing/'
      var getWeddings = function(){
        return $http.get(urlWeddings);
      }
    return{
      getWeddings:getWeddings,

    };
  });
})();
