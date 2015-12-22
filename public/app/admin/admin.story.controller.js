(function(){
"use strict"
angular
  .module('admin')
  .controller('StoryController',function($scope,StoryService){

    StoryService.getStory().success(function(res){
      $scope.story = res;
      $scope.storyEmpty();
    })
    $scope.console = function(item){
      console.log(item);
    }
    $scope.storyEmpty = function(){
      return $scope.story.length < 1 ? true : false
    };
    $scope.postStory = function(){
      $scope.story = story;
      StoryService.postStory(story).success(function(){
        console.log('story posted')
      })
    };
    $scope.editStory = function(story){
      $scope.story = story;
      StoryService.editStory(story).success(function(){
        console.log('story edited');
      })
    }

  })
})();
