(function(){
  "use strict"
  angular
    .module('create-wedding')
    .factory('CreateWeddingService',function($http){
      var urlWedding = 'http://tiny-tiny.herokuapp.com/collections/create-wedding';
      var urlUser = 'http://tiny-tiny.herokuapp.com/collections/create-user';
      var urlAdmin = 'http://tiny-tiny.herokuapp.com/collections/create-admin';

      var getExistingWeddings = function(item){
          return $http.get(urlWedding);
      };
      var addNewWedding = function (wedding) {
          $http.post(urlWedding, wedding).success(function (res) {
            console.log(res);
            // $rootScope.$broadcast('like:added');
     });
        };
     var inviteUser = function(user){
       $http.post(urlUser,user).success(function(res){
         console.log(res);
       })
     };
    var addNewAdmin = function(admin){
      $http.post(urlAdmin,admin).success(function(res){
        console.log(res);
      })
    }


    return{
      getExistingWeddings:getExistingWeddings,
      addNewWedding:addNewWedding,
      inviteUser:inviteUser,
      addNewAdmin:addNewAdmin
    };
  });
})();
