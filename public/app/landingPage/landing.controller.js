(function(){
"use strict"
angular
  .module('landing')
  .controller('LandingController',function($scope,$routeParams,LandingService){
    LandingService.getWeddings().success(function(res){
      console.log(res,'user');
      $scope.user = res;
    })


  })
})();
