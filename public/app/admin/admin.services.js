(function(){
  "use strict"
  angular
    .module('admin')
    .factory('AdminService',function($http){
      var guestsUrl = "https://tiny-tiny.herokuapp.com/collections/sweetUser/";
      var getUsers = function(){
        return $http.get(guestsUrl)
      };
      var inviteUser = function(obj){
        return $http.post(guestsUrl,obj);
      }
    return{
      getUsers:getUsers,
      inviteUser:inviteUser
    };
  });
})();
