(function(){
"use strict"
angular
  .module('create-wedding')
  .controller('CreateWeddingController',function($scope,CreateWeddingService,$anchorScroll,$location,$window){
    //current Index page for wedding controller
    $scope.currentIndex = 0;

    //check if person already invited
    var invitedUsers = [];
    $scope.personAlreadyInvited = false;
    $scope.isAlreadyInvited = function(item){
        if(_.contains(invitedUsers,item)){
          $scope.personAlreadyInvited = true;
          return true;
        }else{
          invitedUsers.push(item);
          $scope.personAlreadyInvited = false;
          return false;
        }

    };
    //show if wedding name already exists
    $scope.weddingNameExists = false;
    var weddings = [];
    CreateWeddingService.getExistingWeddings().success(function(res){
      weddings = res;
      console.log('weddings pulled');
    });
    $scope.weddingAlreadyExists = function(item){
        _.each(weddings,function(el){
          if(el.weddingName === item){
            $scope.weddingNameExists = true;
            return true;
          }else{
            $scope.weddingNameExists = false;
            return false;
          }
        });
  };
    //check if weddingName already exists


    //check if passwords the same
    $scope.passwordsAreSame = true;

    $scope.addNewWedding = function(){
      var wedding = {
        weddingName:$scope.weddingName,
        location:$scope.location,
        date: $scope.date
      }
      CreateWeddingService.addNewWedding(wedding).success(function(res){
        console.log(res);
        $location.path('/');
      });
    };
    $scope.addAdmin = function(){
      var user = {
        userName: $scope.userName,
        email:$scope.email,
        phone:$scope.phone,
        password:$scope.password,
      }
      if($scope.password == $scope.passwordAuth){
        CreateWeddingService.addNewAdmin(user);
      }
      else{alert('wrong password')};
    };
    $scope.inviteUser = function(){
      var user = {
        name: $scope.inviteName,
        email:$scope.inviteEmail,
        weddingName: $scope.weddingName
      }
      CreateWeddingService.inviteUser(user);
    }
    $scope.createAdminInputsEmpty = function(){
      if($scope.userName && $scope.email && $scope.phone && $scope.zip && $scope.password){
        return true;
      }else{
        return false;
      }
    };
    $scope.passwordsSame = function(){
      if($scope.password == $scope.passwordAuth){
        return true;
      }else{
        $scope.passwordsAreSame = false;
        return false
      }
    }
    //currentIndex for ng-show function tabs
    $scope.isCurrentIndex = function (index){
      if($scope.currentIndex === index){
        return true
      }else{
        return false;
      }
    };
    $scope.setCurrentIndex = function(index){
      $scope.currentIndex = index;
      console.log('currentIndex',$scope.currentIndex)
    };

    $scope.scrollTo = function(id) {
      $location.hash(id);
      $anchorScroll();
   }
//calendar controlls
  $scope.today = function() {
    $scope.date = new Date();
  };
  $scope.today();

  $scope.clear = function () {
    $scope.date = null;
  };

  // Disable weekend selection
  $scope.disabled = function(date, mode) {
    return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
  };

  $scope.toggleMin = function() {
    $scope.minDate = $scope.minDate ? null : new Date();
  };
  $scope.toggleMin();
  $scope.maxDate = new Date(2020, 5, 22);

  $scope.open = function($event) {
    $scope.status.opened = true;
  };

  $scope.setDate = function(year, month, day) {
    $scope.date = new Date(year, month, day);
  };

  $scope.dateOptions = {
    formatYear: 'yy',
    startingDay: 1
  };

  $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
  $scope.format = $scope.formats[0];

  $scope.status = {
    opened: false
  };

  var tomorrow = new Date();
  tomorrow.setDate(tomorrow.getDate() + 1);
  var afterTomorrow = new Date();
  afterTomorrow.setDate(tomorrow.getDate() + 2);
  $scope.events =
    [
      {
        date: tomorrow,
        status: 'full'
      },
      {
        date: afterTomorrow,
        status: 'partially'
      }
    ];

  $scope.getDayClass = function(date, mode) {
    if (mode === 'day') {
      var dayToCheck = new Date(date).setHours(0,0,0,0);

      for (var i=0;i<$scope.events.length;i++){
        var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

        if (dayToCheck === currentDay) {
          return $scope.events[i].status;
        }
      }
    }

    return '';
  };
  });
})();
