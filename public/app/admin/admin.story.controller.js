(function(){
"use strict"
angular
  .module('admin')
  .controller('StoryController',function($scope,StoryService,$routeParams){
    var currentId = '';
    $scope.isEmpty = true;
    StoryService.getStory().success(function(res){
      console.log('storyService',res);
      $scope.isEmpty = $scope.storyEmpty(res);

    })
    $scope.storyEmpty = function(obj){
      if(obj.storyContent.length < 1){
        $scope.htmlVariable = "Write a story";
        return true;
      }else{
        $scope.htmlVariable = obj.storyContent;
        return false;
      }
    };
    $scope.postStory = function(story){
      var storyContent = {
        storyContent: story,
        weddingId:$routeParams.weddingId
      }

      StoryService.postStory(storyContent).success(function(res){
        console.log(res,'response post');
      })
    };
    $scope.editStory = function(storyContent){
      StoryService.editStory(storyContent).success(function(){
        console.log('story edited');
      })
    }

  })
})();
