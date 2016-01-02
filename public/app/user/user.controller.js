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
      console.log($scope.currentIndex)
    }
    $scope.isCurrentIndex = function(index){
      if(index === $scope.currentIndex){
        return true;
      }else{
        return false;
      }
    };
    UserService.getDates().success(function(res){
      var localArray= [];
      localArray = res;
      console.log(localArray,'localArray')
      localArray.sort(function (a, b) {
        return a.start > b.start ? 1 : a.start < b.start ? -1 :0;
    });
    $scope.events = localArray;
  });
  $scope.formatDate = function(date){
    return moment(date).calendar()
  }

//story information////////////////////
  UserService.getStory().success(function(res){
    $scope.htmlVariable = res[0].storyContent;
    console.log('res story ',res[0]);
  });
//currentUser//////////////////
UserService.getCurrentUser().success(function(res){
  console.log('res currentUser',res);
  $scope.currentUserName = res.username;
  $scope.userId = res.id;
});
//all users to wedding////////////
UserService.getUsers().success(function(res){
  $scope.guests = res;
  console.log('res users',res);
})
//carousel
  $scope.myInterval = 5000;
    $scope.noWrapSlides = false;
    $scope.slides = []
    console.log('slides',$scope.slides);
    $scope.getPhotoSlides = function(){
      $scope.slides.splice(0,$scope.slides.length);
    UserService.getPhotos().success(function(res){
      for (var i = 0; i < res.length; i++) {
        var currItem = {image:'../pics/'+res[i].fileName};
        console.log(currItem,'currItem')
        $scope.slides.push(currItem);
      }

      console.log(res,'photos in file');
    })
  }
  $scope.getPhotoSlides();
    $scope.currentIndex = 2;
    $scope.myValue = true;

  });
})();
