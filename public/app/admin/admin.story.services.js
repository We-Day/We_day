(function(){
  "use strict"
  angular
    .module('admin')
    .factory('StoryService',function($http,$routeParams){
      var storyUrl = "https://tiny-tiny.herokuapp.com/collections/ourStory";
      var getStory = function(){
        return $http.get(storyUrl);
      };
      var postStory = function(post){
        return $http.post(storyUrl,post)
      };
      var editStory = function(post){
        return $http.put(storyUrl,post)
      }
    return{
      getStory:getStory,
      postStory:postStory,
      editStory:editStory
    };
  });
})();
