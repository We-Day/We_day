(function(){
  "use strict"
  angular
    .module('admin')
    .factory('AdminService',function(){
      var charles = function(){
        console.log('charles');
      };


    return{
      charles:charles
    };
  });
})();
