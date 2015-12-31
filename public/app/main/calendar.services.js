(function(){
  "use strict"
  angular
    .module('main')
    .factory('MainCalendarService',function($http,$routeParams){
      var dateUrl = "https://tiny-tiny.herokuapp.com/collections/dates";

      var getDates = function(item){
        return $http.get(dateUrl);
      };
      var addDate = function(item){
        return $http.post(dateUrl,item)
      };
      var editDate = function(item){
        return $http.put(dateUrl+'/'+item._id,item)
      }

      var deleteDate = function(item){
        var pObject = parseItem(item);
        return $http.delete(dateUrl +'/' + pObject._id);
      };


    return{
      getDates:getDates,
      addDate:addDate,
      editDate:editDate,
      deleteDate:deleteDate
    };
  });
})();
