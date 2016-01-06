(function(){
  "use strict"
  angular
    .module('admin')
    .factory('NotServices',function($http){
      var urlN = "/send-notification";
        var getNot = function(id){
          return $http.get(urlN+'/'+id);
        };
        var postNot = function(el){
          return $http.post(urlN,el);
        }
        var deleteNot = function(el){
          return $http.delete(urlN+'/'+el.id)
        }
    return{
      deleteNot:deleteNot,
      getNot:getNot,
      postNot:postNot,
    };
  });
})();
