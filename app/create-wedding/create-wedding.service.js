(function(){
  "use strict"
  angular
    .module('create-wedding')
    .factory('CreateWeddingService',function(){
      var charles = function(){
        console.log('charles');
      };


    return{
      charles:charles
    };
  });
})();
