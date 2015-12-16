(function(){
  "use strict"
  angular
    .module('main')
    .factory('RegisterService',function($http){
      var urlCreateUser = '/create-user';
      var addNewUser = function(user){
        return $http.post(urlCreateUser,user)
      };

    return{
      addNewUser:addNewUser,
    }
  });
})();
