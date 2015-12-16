(function(){
  "use strict"
  angular
    .module('main')
    .factory('LoginService',function($http){
      var urlCreateUser = '/create-user';
      var addNewUser = function(admin){
        $http.post(urlAdmin,admin).success(function(res){
          console.log(res);
        })
      };

    return{
      addNewUser:addNewUser,
    };
  });
})();
