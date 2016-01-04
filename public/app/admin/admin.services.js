(function(){
  "use strict"
  angular
    .module('admin')
    .factory('AdminService',function($http,$routeParams){
      var guestsUrl = "/create-guest";
      var getInvitees = "/display-invites";
      var currWedding = '/create-wedding';
      var currentUser = '/current-user';
      var logoutUrl = '/logout';
      var getPicsUrl = '/photos/';
      var removeUserUrl = '/delete-invite'

      var logOut = function(){
        return $http.post(logoutUrl);
      }
      var getWeddingObject = function(id){
        return $http.get(currWedding+'/'+id);
      }
      var getUsers = function(id){
        return $http.get(getInvitees+'/'+id);
      };
      var inviteUser = function(obj){
        return $http.post(guestsUrl,obj);
      }
      var removeUser = function(id){
        return $http.delete(removeUserUrl+'/'+id)
      }
      var getCurrentUser = function(){
        return $http.get(currentUser);
      }
      var getPhotos = function(id){
        return $http.get(getPicsUrl+'/'+id);
      }
    return{
      getPhotos:getPhotos,
      logOut:logOut,
      getCurrentUser:getCurrentUser,
      removeUser:removeUser,
      getWeddingObject:getWeddingObject,
      getUsers:getUsers,
      inviteUser:inviteUser
    };
  });
})();
