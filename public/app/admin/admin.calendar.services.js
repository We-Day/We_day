(function(){
  "use strict"
  angular
    .module('admin')
    .factory('CalendarService',function($http,$routeParams){
      var dateUrl = "/create-event";
      var currWedding = '/create-wedding/'+$routeParams.weddingId;

      var parseItem = function(item){
        console.log('parsItem',item);
        var object = {
          $$hashKey: item.$$hashKey,
          _id: item._id,
          start: new Date(item.start),
          end: new Date(item.end),
          title: item.title,
          email:{
            bool:item.email.bool,
            time: item.email.time
          },
          text:{
            bool:item.text.bool,
            time: item.text.time
          },
          notification:{
            bool:item.notification.bool,
            time:item.notification.time,
          }
        }
        return object;
      }
      var getDates = function(item){
        return $http.get(dateUrl+'/'+$routeParams.weddingId);
      };
      var addDate = function(item){
        // console.log('add Date item', item)
        // var pObject = parseItem(item);
        return $http.post(dateUrl+'/'+$routeParams.weddingId,item)
      };
      var editDate = function(item){
        return $http.put(dateUrl+'/'+item._id,item)
      }

      var deleteDate = function(item){
        var pObject = parseItem(item);
        return $http.delete(dateUrl +'/' + pObject._id);
      };
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
