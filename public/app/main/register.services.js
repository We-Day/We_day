(function(){
  "use strict"
  angular
    .module('main')
    .factory('RegisterService',function($http){
      var tinyTiny = 'https://tiny-tiny.herokuapp.com/collections/create-users'
      var urlCreateUser = '/create-user';
      var urlUsers = '/user'
      var addNewUser = function(user){
        return $http.post(urlCreateUser,user);
      };
      var getAllUsers = function(){
        return $http.get(urlUsers);
      }
    return{
      addNewUser:addNewUser,
      getAllUsers:getAllUsers
    }
  });
})();
