(function(){
"use strict"
angular
  .module('admin')
  .controller('AdminController',function($scope,AdminService,$location,$routeParams){
    $scope.logOut = function(){
      AdminService.logOut().success(function(res){
        $location.path('/');
      });
    };
  AdminService.getCurrentUser().success(function(res){
    console.log(res,'currentUser');
    $scope.currentUser = res.username;
    $scope.userId = res.id;
  });

  AdminService.getWeddingObject().success(function(res){
    $scope.weddingName = res.weddingName;
  })

//myguests
$scope.viewInvitee = false;
  $scope.guests = [];
  AdminService.getUsers().success(function(res){
    console.log('invites',res)
    $scope.guests = res;
  })
  $scope.removeUser = function(id,index){
    $scope.guests.splice(index,1);
    AdminService.removeUser(id).success(function(res){
      console.log(res,'response remove user');
    })
  }
  console.log($routeParams.weddingId,'weddingId');
  $scope.inviteUser = function(name,email,bool){
    var currObject = {
      email: email,
      username: name,
      isAdmin: bool,
      weddingId: $routeParams.weddingId
    }
    $scope.guests.push(currObject);
    AdminService.inviteUser(currObject).success(function(res){
      console.log(res);
      $scope.lastInvitee = res.username;
      $scope.viewInvitee = true;
    })
  }
//carousel
$scope.weddingId = $routeParams.weddingId;

  $scope.myInterval = 5000;
    $scope.noWrapSlides = false;
    $scope.slides = []
    $scope.getPhotoSlides = function(){
      $scope.slides.splice(0,$scope.slides.length);
    AdminService.getPhotos().success(function(res){
      for (var i = 0; i < res.length; i++) {
        var currItem = {image:'../pics/'+res[i].fileName};
        console.log(currItem,'currItem')
        $scope.slides.push(currItem);
      }

      console.log(res,'photos in file');
    })
  }
  $scope.getPhotoSlides();

    $scope.currentIndex = 6;
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
