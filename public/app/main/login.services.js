(function(){
  "use strict"
  angular
    .module('main')
    .factory('LoginService',function($http){
      var urlUsers = '/create-user';
      var getUsers = function(){
        return $http.get(urlUsers);
      }

    return{
      getUsers:getUsers,
    };
  });
})();
