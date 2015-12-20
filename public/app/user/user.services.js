(function(){
  "use strict"
  angular
    .module('user')
    .factory('UserService',function($http,$location,$window){
      var dateUrl = "https://tiny-tiny.herokuapp.com/collections/dates";
      var getDates = function(item){
        return $http.get(dateUrl);
      };

    return{
      getDates : getDates,
    };
  });
})();
