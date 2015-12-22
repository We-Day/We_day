(function(){
"use strict"
angular
  .module('admin')
  .controller('AdminController',function($scope,AdminService){
  $scope.currentUser = "Charles"

  AdminService.getWeddingObject().success(function(res){
    $scope.weddingName = res.weddingName;
    console.log(res,'res');
  })

//myguests
$scope.viewInvitee = false;
  AdminService.getUsers().success(function(res){
    $scope.guests = res;
  })
  $scope.inviteUser = function(name,email){
    var currObject = {
      email: email,
      username: name
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
    $scope.currentIndex = 0;
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
