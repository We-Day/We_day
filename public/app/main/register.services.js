(function(){
  "use strict"
  angular
    .module('main')
    .factory('RegisterService',function($http){
      var tinyTiny = 'https://tiny-tiny.herokuapp.com/collections/create-users'
      var urlCreateUser = '/create-user';
      var addNewUser = function(user){
        return $http.post(tinyTiny,user);
      };

    return{
      addNewUser:addNewUser
    }
  });
})();
