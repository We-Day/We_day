(function(){
  "use strict"
  angular
    .module('create-wedding')
    .factory('CreateWeddingService',function($http,$location,$window){
      var urlWedding = '/create-wedding';
      var urlInviteUser = 'create-guest';
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
       return $http.post(urlInviteUser,user)
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
