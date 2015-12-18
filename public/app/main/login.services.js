(function(){
  "use strict"
  angular
    .module('main')
    .factory('LoginService',function($http){
      var urlUsers = '/users';
      var urlInvited = '/login'
      var getUsers = function(){
        return $http.get(urlUsers);
      }
      var isInvitedToWedding = function(email){
        return $http.post(urlInvited,email);
      }
    return{
      isInvitedToWedding:isInvitedToWedding,
      getUsers:getUsers,
    };
  });
})();
