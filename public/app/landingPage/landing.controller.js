(function(){
"use strict"
angular
  .module('landing')
  .controller('LandingController',function($scope,$routeParams,LandingService){
    $scope.routeParams = $routeParams.userId;
    console.log($scope.routeParams);
    $scope.weddings = [
      {weddingId: 1,
      weddingName: "charles&susie",
      isadmin: 'user',
    },
    {weddingId:2,
    weddingName:'charles and his third wife',
    isadmin: 'admin'
    }
    ]

  })
})();
