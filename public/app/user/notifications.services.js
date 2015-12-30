(function(){
  "use strict"
  angular
    .module('user')
    .factory('NotificationServices',function($http){
      var urlN = "send-notification";
        var getNot = function(el){
          return $http.get(urlN);
        };
        var postNot = function(el){
          return $http.post(urlN,el);
        }
        var deleteNot = function(el){
          return $http.delete(urlN+'/'+el._id)
        }
    return{
      deleteNot:deleteNot,
      getNot:getNot,
      postNot:postNot,
    };
  });
})();
