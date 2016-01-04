(function(){
"use strict"
angular
  .module('admin')
  .controller('AdminController',function($scope,AdminService,$location,$routeParams){
    $scope.weddingId = $routeParams.weddingId;
    var weddingId = $routeParams.weddingId;
    $scope.currentPage = $location.$$path;
    $scope.slides = [];
    $scope.slides.splice(0,$scope.slides.length);

    $scope.loading = true;

    setTimeout(function () {
      $scope.loading = false;
    }, 300);

    $scope.logOut = function(){
      AdminService.logOut().success(function(res){
        $location.path('/');
      });
    };

  AdminService.getCurrentUser().success(function(res){
    $scope.currentUser = res.username;
    $scope.userId = res.id;
  });


  AdminService.getWeddingObject($routeParams.weddingId).success(function(res){
    $scope.weddingName = res.weddingName;
  })

//myguests
$scope.viewInvitee = false;
  $scope.guests = [];

  AdminService.getUsers($routeParams.weddingId).success(function(res){
    console.log('invites',res)
    $scope.guests = res;
  })
  $scope.removeUser = function(id,index){
    $scope.guests.splice(index,1);
    AdminService.removeUser(id).success(function(res){
      console.log(res,'response remove user');
    })
  }
  $scope.inviteUser = function(name,email,bool){
    var currObject = {
      email: email,
      username: name,
      isAdmin: bool,
      weddingId: $routeParams.weddingId
    }
    $scope.guests.push(currObject);
    AdminService.inviteUser(currObject).success(function(res){
      console.log(res,'inviteed USer');
      $scope.lastInvitee = currObject.username;
      $scope.viewInvitee = true;
      setTimeout(function () {
        $scope.viewInvitee = false;
      }, 500);
    })
  }
//carousel

  $scope.reRoute = function(){
    $location.path($location.absUrl);
  }

  $scope.slides = [];
  $scope.myInterval = 5000;
    $scope.noWrapSlides = false;

    $scope.getPhotoSlides = function(){
      $scope.slides.splice(0,$scope.slides.length);
    AdminService.getPhotos($routeParams.weddingId).success(function(res){
      for (var i = 0; i < res.length; i++) {
        var currItem = {image:'../pics/'+res[i].fileName};
        $scope.slides.push(currItem);
      }
      console.log(res,'photos in file');
      $scope.slides.length > 0 ? $scope.photosExist = true: $scope.photosExist = false;
    })
  }
  $scope.getPhotoSlides();

    $scope.currentIndex = 7;
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



  })
})();
