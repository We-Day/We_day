(function(){
  "use strict"
  angular
    .module('create-wedding')
    .factory('CreateWeddingService',function($http,$location,$window){
      var urlWedding = '/create-wedding';
      var urlInviteUser = 'http://tiny-tiny.herokuapp.com/collections/invite-user';
      var urlAdmin = '/create-user';
      var urlFacebook = '/profile'
      var getFacebookObject = function(item){
        return $http.get(urlFacebook);
      }
      var getExistingWeddings = function(item){
          return $http.get(urlWedding);
      };
      var getInvitedUsers = function(item){
        return $http.get(urlInviteUser);
      };
      var addNewWedding = function (wedding) {
          $http.post(urlWedding, wedding).success(function (res,status,headers,other) {

            $window.location.href = '#/admins';
            console.log(res,'headers');
            // $rootScope.$broadcast('like:added');
     });
        };
     var inviteUser = function(user){
       $http.post(urlInviteUser,user).success(function(res){
         console.log(res);
       })
     };
    var addNewAdmin = function(admin){
      $http.post(urlAdmin,admin).success(function(res){
        console.log(res);
      })
    };


    return{
      getFacebookObject:getFacebookObject,
      getInvitedUsers:getInvitedUsers,
      getExistingWeddings:getExistingWeddings,
      addNewWedding:addNewWedding,
      inviteUser:inviteUser,
      addNewAdmin:addNewAdmin
    };
  });
})();
