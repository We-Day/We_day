(function(){
  "use strict"
  angular
    .module('admin')
    .factory('AdminService',function($http){
      var guestsUrl = "/create-guest";
<<<<<<< HEAD
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
=======
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
>>>>>>> 4799203b3a3ea4266cd9cc81ee81fae3289d3066
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
<<<<<<< HEAD
      var getPhotos = function(id){
        return $http.get(getPicsUrl+'/'+id);
=======
      var getPhotos = function(weddingId){
        return $http.get(getPicsUrl + weddingId);
>>>>>>> 4799203b3a3ea4266cd9cc81ee81fae3289d3066
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
