(function(){
  "use strict"
  angular
    .module('admin')
    .factory('AdminService',function($http,$routeParams){
      var guestsUrl = "/create-guest";
      var currWedding = '/create-wedding/'+$routeParams.weddingId;
      var currentUser = '/current-user';
      var logoutUrl = '/logout';
      var logOut = function(){
        return $http.post(logoutUrl);
      }
      var getWeddingObject = function(){
        return $http.get(currWedding);
      }
      var getUsers = function(){
        return $http.get(guestsUrl)
      };
      var inviteUser = function(obj){
        return $http.post(guestsUrl,obj);
      }
      var removeUser = function(id){
        return $http.delete(guestsUrl+id)
      }
      var getCurrentUser = function(){
        return $http.get(currentUser);
      }
    return{
      logOut:logOut,
      getCurrentUser:getCurrentUser,
      removeUser:removeUser,
      getWeddingObject:getWeddingObject,
      getUsers:getUsers,
      inviteUser:inviteUser
    };
  });
})();
