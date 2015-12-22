(function(){
  "use strict"
  angular
    .module('create-wedding')
    .factory('CreateWeddingService',function($http,$location,$window){
      var urlWedding = 'create-wedding';
      var urlInviteUser = 'http://tiny-tiny.herokuapp.com/collections/invite-user';
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
          return $http.post(urlWedding, wedding);
        };
     var inviteUser = function(user){
       $http.post(urlInviteUser,user).success(function(res){
         console.log('Invite User',res);
       })
     };



    return{
      getFacebookObject:getFacebookObject,
      getInvitedUsers:getInvitedUsers,
      getExistingWeddings:getExistingWeddings,
      addNewWedding:addNewWedding,
      inviteUser:inviteUser,
    };
  });
})();
