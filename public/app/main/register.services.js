(function(){
  "use strict"
  angular
    .module('main')
    .factory('RegisterService',function($http){
      var urlCreateUser = '/create-user';
      var addNewUser = function(admin){
        $http.post(urlCreateUser,admin).success(function(res){
          console.log(res);
        })
      };

    return{
      addNewUser:addNewUser,
    };
  });
})();
