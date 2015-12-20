(function(){
"use strict"
angular
  .module('landing')
  .controller('LandingController',function($scope,$routeParams,LandingService){
    $scope.routeParams = $routeParams.userId;
    LandingService.getWeddings().success(function(res){
      console.log(res);
      $scope.weddings = res;
    })
    

  })
})();
