(function(){
  "use strict"
  angular
    .module('user')
    .factory('UserService',function($http,$location,$window){
      var dateUrl = "https://tiny-tiny.herokuapp.com/collections/dates";
      var currentUserUrl = "/current-user"
      var guestsUrl = "https://tiny-tiny.herokuapp.com/collections/sweetUser/";
      var storyUrl = "https://tiny-tiny.herokuapp.com/collections/ourStory";
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
    return{
      getStory:getStory,
      getUsers:getUsers,
      getCurrentUser:getCurrentUser,
      getDates : getDates,
    };
  });
})();
