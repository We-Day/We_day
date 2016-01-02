(function(){
  "use strict"
  angular
    .module('admin')
    .factory('CalendarService',function($http,$routeParams){
      var weddingId = $routeParams.weddingId;
      var dateUrl = "/create-event";
      var displayEventsUrl = "/display-events"
      var currWedding = '/create-wedding/'+weddingId;
      var editEventUrl = 'edit-event';
      var deleteEventUrl = 'delete-event';

      var getDates = function(item){
        return $http.get(displayEventsUrl+'/'+weddingId);
      };
      var addDate = function(item){
        // console.log('add Date item', item)
        // var pObject = parseItem(item);
        return $http.post(dateUrl+'/'+weddingId,item)
      };
      var editDate = function(item){
        return $http.put(editEventUrl+'/'+item._id,item)
      }

      var deleteDate = function(item){
        return $http.delete(deleteEventUrl +'/'+item._id);
      }
      var getWeddingObject = function(){
        return $http.get(currWedding);
      }
    return{
      getWeddingObject:getWeddingObject,
      getDates:getDates,
      addDate:addDate,
      editDate:editDate,
      deleteDate:deleteDate
    };
  });
})();
