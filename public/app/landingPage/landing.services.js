(function(){
  "use strict"
  angular
    .module('landing')
    .factory('LandingService',function($http){
      var urlWeddings = '/create-wedding';
      var getWeddings = function(){

      }
      var charles = function(){
        console.log('charles');
      };


    return{
      charles:charles
    };
  });
})();
