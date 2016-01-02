(function(){
  "use strict"
  angular
    .module('user')
    .factory('UserService',function($http,$location,$window,$routeParams){
      var dateUrl = "/display-events";
      var currentUserUrl = "/current-user"
      var guestsUrl = "https://tiny-tiny.herokuapp.com/collections/sweetUser/";
      var getInvitees = "/display-invites/"+$routeParams.weddingId;
      var storyUrl = "/story";
      var getPicsUrl = '/photos/'+$routeParams.weddingId;
      var logOutUrl = "/logout"
      var weddingId = $routeParams.weddingId;
      var getStory = function(){
        return $http.get(storyUrl+'/'+weddingId);
      };
      var getDates = function(){
        return $http.get(dateUrl+'/'+weddingId);
      };
      var getUsers = function(){
        return $http.get(getInvitees)
      };
      var getCurrentUser = function(){
        return $http.get(currentUserUrl);
      }
      var logOut = function(){
        return $http.post(logOutUrl);
      }
      var getPhotos = function(){
        return $http.get(getPicsUrl);
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
