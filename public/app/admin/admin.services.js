(function(){
  "use strict"
  angular
    .module('admin')
    .factory('AdminService',function($http){
      var guestsUrl = "/create-guest";
      var getInvitees = "/display-invites/";
      var currWedding = '/create-wedding/';
      var currentUser = '/current-user';
      var logoutUrl = '/logout';
      var getPicsUrl = '/photos/'
      var removeUserUrl = '/delete-invite'
//      var currId = $routeParams.weddingId;
      var logOut = function(){
        return $http.post(logoutUrl);
      }
      var getWeddingObject = function(weddingId){
        return $http.get(currWedding + weddingId);
      }
      var getUsers = function(weddingId){
        return $http.get(getInvitees + weddingId);
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
      var getPhotos = function(weddingId){
        return $http.get(getPicsUrl + weddingId);
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
