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
  });

  AdminService.getWeddingObject().success(function(res){
    $scope.weddingName = res.weddingName;
  })

//myguests
$scope.viewInvitee = false;
  $scope.guests = [];
  AdminService.getUsers().success(function(res){
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
  $scope.myInterval = 5000;
    $scope.noWrapSlides = false;
    var slides = $scope.slides = [
      {image:"http://cdn-media-2.lifehack.org/wp-content/files/2015/02/Wedding06-main.jpg"},
      {image:"http://www.doraliveband.com/docs/upload/w1.jpg"}
    ];
    $scope.currentIndex = 2;
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
