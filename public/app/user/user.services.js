(function(){
  "use strict"
  angular
    .module('user')
    .factory('UserService',function($http,$location,$window,$routeParams){
      var dateUrl = "/display-events";
      var currentUserUrl = "/current-user"
      var getInvitees = "/display-invites";
      var storyUrl = "/story";
      var getPicsUrl = '/photos'
      var logOutUrl = "/logout"
      var weddingId = $routeParams.weddingId;
      var getStory = function(id){
        return $http.get(storyUrl+'/'+id);
      };
      var getDates = function(id){
        return $http.get(dateUrl+'/'+id);
      };
      var getUsers = function(id){
        return $http.get(getInvitees+'/'+id)
      };
      var getCurrentUser = function(id){
        return $http.get(currentUserUrl);
      }
      var logOut = function(){
        return $http.post(logOutUrl);
      }
      var getPhotos = function(id){
        return $http.get(getPicsUrl+'/'+id);
      }
    return{
      getPhotos:getPhotos,
      logOut:logOut,
      getStory:getStory,
      getUsers:getUsers,
      getCurrentUser:getCurrentUser,
      getDates : getDates,
    };
  });
})();
