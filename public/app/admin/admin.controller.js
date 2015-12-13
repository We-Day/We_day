(function(){
"use strict"
angular
  .module('admin')
  .controller('AdminController',function($scope,AdminService){

  $scope.myInterval = 5000;
    $scope.noWrapSlides = false;
    var slides = $scope.slides = [
      {image:"http://cdn-media-2.lifehack.org/wp-content/files/2015/02/Wedding06-main.jpg"},
      {image:"http://www.doraliveband.com/docs/upload/w1.jpg"}
    ];
    $scope.currentIndex = 1;
    $scope.myValue = true;
    $scope.setCurrentIndex = function(index){
      $scope.currentIndex = index;
      console.log($scope.currentIndex)
    }
    $scope.isCurrentIndex = function(index){
      if(index === $scope.currentIndex){
        return true;
      }else{
        return false;
      }
    };

//calendar bitch

  })
})();
