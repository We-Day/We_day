(function(){
"use strict"
angular
  .module('user')
  .controller('UserController',function($scope,UserService){
    $scope.currentIndex = 1;
    $scope.currentUser = 'Charles'
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

//     $scope.events = [{
//   badgeClass: 'info',
//   badgeIconClass: 'glyphicon-check',
//   title: 'First heading',
//   content: 'Some awesome content.'
// }, {
//   badgeClass: 'warning',
//   badgeIconClass: 'glyphicon-credit-card',
//   title: 'Second heading',
//   content: 'More awesome content.'
// }];

  });
})();
