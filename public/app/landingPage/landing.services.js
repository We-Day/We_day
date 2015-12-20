(function(){
  "use strict"
  angular
    .module('landing')
    .factory('LandingService',function($http,$routeParams){
      var urlWeddings = '/create-wedding';
      var urlWeddingsUser = '/landing/'
      var getWeddings = function(){
        console.log($routeParams.userId)
        console.log(urlWeddingsUser+$routeParams.userId);
        return $http.get(urlWeddingsUser+$routeParams.userId);
      }

    return{
      getWeddings:getWeddings,

    };
  });
})();
