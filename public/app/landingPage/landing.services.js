(function(){
  "use strict"
  angular
    .module('landing')
    .factory('LandingService',function(){
      var charles = function(){
        console.log('charles');
      };


    return{
      charles:charles
    };
  });
})();
