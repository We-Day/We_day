(function(){
  "use strict"
  angular
    .module('admin')
    .factory('CalendarService',function($http,$routeParams){
      var weddingId = $routeParams.weddingId;
      var dateUrl = "/create-event";
      var displayEventsUrl = "/display-events"
      var currWedding = '/create-wedding';
      var editEventUrl = 'edit-event';
      var deleteEventUrl = 'delete-event';

      var getDates = function(id){
        return $http.get(displayEventsUrl+'/'+id);
      };
      var addDate = function(item,id){
        // console.log('add Date item', item)
        // var pObject = parseItem(item);
        return $http.post(dateUrl+'/'+id,item)
      };
      var editDate = function(item,id){
        return $http.put(editEventUrl+'/'+id,item)
      }

      var deleteDate = function(item){
        return $http.delete(deleteEventUrl +'/'+item._id);
      }
      var getWeddingObject = function(id){
        return $http.get(currWedding+'/'+id);
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
