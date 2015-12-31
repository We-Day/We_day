(function(){
"use strict"
angular
  .module('admin')
  .controller('StoryController',function($scope,StoryService,$routeParams){
    var currentId = '';
    StoryService.getStory().success(function(res){
      console.log('res',res[0].storyContent.length);
      $scope.isEmpty = $scope.storyEmpty(res[0]);
      $scope.htmlVariable = res[0].storyContent;
    })
    $scope.storyEmpty = function(obj){
      return obj.storyContent.length < 1 ? true : false
    };
    $scope.postStory = function(story){
      var storyContent = {
        storyContent: story,
        weddingId:$routeParams.weddingId
      }
      if(currentId.length != 0){
        $scope.editStory(storyContent);
      }else{
      StoryService.postStory(storyContent).success(function(res){
        console.log(res,'response post');
        currentId = res._id;
      })
    };
    };
    $scope.editStory = function(storyContent){
      StoryService.editStory(storyContent).success(function(){
        console.log('story edited');
      })
    }

  })
})();
