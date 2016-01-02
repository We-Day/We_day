(function(){
"use strict"
angular
  .module('user')
  .controller('UserController',function($scope,UserService,$location,$routeParams){
    $scope.weddingId = $routeParams.weddingId;
    $scope.loading = true;
    setTimeout(function () {
      $scope.loading = false;
    }, 400);
    $scope.currentIndex = 1;
    $scope.logOut = function(){
      UserService.logOut().success(function(res){
        $location.path('/');
      });
    };
    $scope.setCurrentIndex = function(index){
      $scope.currentIndex = index;
    }
    $scope.isCurrentIndex = function(index){
      if(index === $scope.currentIndex){
        return true;
      }else{
        return false;
      }
    };
    UserService.getDates().success(function(res){
      console.log('dates',res)
      var localArray= [];
      localArray = res;
      console.log(localArray,'localArray')
      localArray.sort(function (a, b) {
        return a.start > b.start ? 1 : a.start < b.start ? -1 :0;
    });
    console.log('dates',localArray)
    $scope.events = localArray;
  });
  $scope.formatDate = function(date){
    return moment(date).calendar()
  }

//story information////////////////////
  UserService.getStory().success(function(res){
    $scope.htmlVariable = res[0].storyContent;
  });
//currentUser//////////////////
UserService.getCurrentUser().success(function(res){
  $scope.currentUserName = res.username;
  $scope.userId = res.id;
});
//all users to wedding////////////
UserService.getUsers().success(function(res){
  $scope.guests = res;
})
//carousel
  $scope.myInterval = 5000;
    $scope.noWrapSlides = false;
    $scope.slides = []
    $scope.getPhotoSlides = function(){
      $scope.slides.splice(0,$scope.slides.length);
    UserService.getPhotos().success(function(res){
      for (var i = 0; i < res.length; i++) {
        var currItem = {image:'../pics/'+res[i].fileName};
        $scope.slides.push(currItem);
      }
    })
  }
  $scope.getPhotoSlides();
    $scope.currentIndex = 2;
    $scope.myValue = true;

  });
})();
