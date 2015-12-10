(function(){
  "use strict"
  angular
    .module('main')
    .factory('MainService',function(){
      var charles = function(){
        console.log('charles');
      };


    return{
      charles:charles
    };
  });
})();
