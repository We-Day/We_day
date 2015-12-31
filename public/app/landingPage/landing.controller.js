(function(){
"use strict"
angular
  .module('landing')
  .controller('LandingController',function($scope,$routeParams,LandingService){
    $scope.loading = false;

    LandingService.getWeddings().success(function(res){
      console.log(res,'user');
      $scope.weddings = res;
    })


  })
})();
