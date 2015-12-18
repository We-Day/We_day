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

      var isInvitedToWedding = function(email,password){
        var currObj = {
          email: email,
          password: password
        }
        return $http.post(urlInvited,currObj);
      }
    return{
      isInvitedToWedding:isInvitedToWedding,
      getUsers:getUsers,
    };
  });
})();
