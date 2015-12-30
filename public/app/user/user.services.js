(function(){
  "use strict"
  angular
    .module('user')
    .factory('UserService',function($http,$location,$window,$routeParams){
      var dateUrl = "/create-event";
      var currentUserUrl = "/current-user"
      var guestsUrl = "https://tiny-tiny.herokuapp.com/collections/sweetUser/";
      var storyUrl = "https://tiny-tiny.herokuapp.com/collections/ourStory";
      var getPicsUrl = '/photos/'+$routeParams.weddingId;
      var logOutUrl = "/logout"
      var getStory = function(){
        return $http.get(storyUrl);
      };
      var getDates = function(item){
        return $http.get(dateUrl);
      };
      var getUsers = function(){
        return $http.get(guestsUrl)
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
