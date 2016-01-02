(function(){
  "use strict"
  angular
    .module('admin')
    .factory('StoryService',function($http,$routeParams){
      var storyUrl = "/story";
      var getStory = function(){
        return $http.get(storyUrl+'/'+$routeParams.weddingId);
      };
      var postStory = function(post){
        return $http.post(storyUrl,post)
      };
      var editStory = function(post){
        return $http.put(storyUrl+'/'+$routeParams.weddingId,post)
      }
    return{
      getStory:getStory,
      postStory:postStory,
      editStory:editStory
    };
  });
})();
