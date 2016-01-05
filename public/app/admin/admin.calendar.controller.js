

(function(){
"use strict"
angular
  .module('admin')
  .controller('CalendarController',function($scope,$routeParams,CalendarService,$compile,uiCalendarConfig,$window){

    //reload route
    $scope.reloadRoute = function() {
      $window.location.reload();
    }
    $scope.events = [];
    // console.log($scope.events,'scope events')
    $scope.updateEvents = function(){
      $scope.events.splice(0,$scope.events.length)

    CalendarService.getDates($routeParams.weddingId).success(function(el){
      var eventArray = el.map(function(newEl,idx) {
        return {
          _id: newEl._id,
          start: new Date(newEl.start),
          end: new Date(newEl.end),
          title: newEl.title,
            emailBool:newEl.emailBool,
            emailTime: newEl.emailTime,
            textBool:newEl.textBool,
            textTime: newEl.textTime,
            notificationBool:newEl.notificationBool,
            notificationTime:newEl.notificationTime,
          }
      })
      $scope.events = eventArray;
      console.log('events',eventArray);

      $scope.eventSources = [$scope.events, $scope.eventSource, $scope.eventsF];

    })
  };
  $scope.updateEvents();

  //add CurrentWedding Name
  CalendarService.getWeddingObject($scope.weddingId).success(function(res){
    $scope.weddingName = res.weddingName;
  });
  //calendar bitch
    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();

    $scope.changeTo = 'Hungarian';
    /* event source that pulls from google.com */
    $scope.eventSource = {
            url: "http://www.google.com/calendar/feeds/usa__en%40holiday.calendar.google.com/public/basic",
            className: 'gcal-event',           // an option!
            currentTimezone: 'America/New York' // an option!
    };
    /* event source that contains custom events on the scope */


    // $scope.events = [
    //   {_id:'1',title: 'Tester',start: new Date(2016, 0, 5),end: new Date(2016, 0, 8)},
    //   {_id:2,title: 'Long Event',start: new Date(y, m, d - 5),end: new Date(y, m, d - 2)},
    //   {_id:5,title: 'Repeating Event',start: new Date(y, m, d - 3, 16, 0),end: new Date(y, m, d - 2)},
    //   {_id:6,title: 'Repeating Event',start: new Date(y, m, d + 4, 16, 0),end: new Date(y, m, d - 2)},
    //   {_id:3,title: 'Birthday Party',start: new Date(y, m, d + 1, 19, 0),end: new Date(y, m, d + 1, 22, 30),allDay: false},
    //   {_id:4,title: 'Click for Google',start: new Date(y, m, 28),end: new Date(y, m, 29),url: 'http://google.com/'}
    // ];
    // console.log($scope.events);
    /* event source that calls a function on every view switch */
    $scope.eventsF = function (start, end, timezone, callback) {
      var s = new Date(start).getTime() / 1000;
      var e = new Date(end).getTime() / 1000;
      var m = new Date(start).getMonth();
      var events = [{title: 'Feed Me ' + m,start: s ,end: s ,allDay: false, className: ['customFeed']}];
      callback(events);
    };

    $scope.calEventsExt = {
       color: '#f00',
       textColor: 'green',
       events: [
          {type:'party',title: 'Lunch',start: new Date(y, m, d, 12, 0),end: new Date(y, m, d, 14, 0),allDay: false},
          {type:'party',title: 'Lunch 2',start: new Date(y, m, d, 12, 0),end: new Date(y, m, d, 14, 0),allDay: false},
          {type:'party',title: 'Click for Google',start: new Date(y, m, 28),end: new Date(y, m, 29)}
        ]
    };
    $scope.editTitleDate = function(event){
      $scope.updateEvents();
      CalendarService.editDate(event,$routeParams.weddingId).success(function(res){
        $scope.updateEvents();
      });
    };
    $scope.editDate = function(event){

      console.log(event,'event');
      var currObject = {
        _id: event._id,
        start: event.start,
        end: event.end,
        title: event.title,
          textBool:event.textBool,
          textTime: event.textTime,
          emailBool:event.emailBool,
          emailTime: event.emailTime,
          notificationBool:event.notificationBool,
          notificationTime:event.notificationTime,
        }
        console.log(typeof(start));
        CalendarService.editDate(currObject,$routeParams.weddingId).success(function(res){
          console.log(event)

      });
    };

    $scope.alertOnEventClick = function( date, jsEvent, view){
        $scope.alertMessage = (date.title);
    };

    /* alert on Drop */
     $scope.alertOnDrop = function(event, delta, revertFunc, jsEvent, ui, view){
       console.log(event,'event');
       var startDate = new Date(event.start._d);
       startDate.setHours(startDate.getHours()+5);
       var endDate = new Date(event.end._d);
       endDate.setHours(endDate.getHours()+5);
       console.log(event,'event');
       var currObject = {
         _id: event._id,
         start: startDate,
         end: endDate,
         title: event.title,
           textBool:event.textBool,
           textTime: event.textTime,
           emailBool:event.emailBool,
           emailTime: event.emailTime,
           notificationBool:event.notificationBool,
           notificationTime:event.notificationTime,
         }
       console.log('currObject',currObject);
       CalendarService.editDate(currObject,$routeParams.weddingId).success(function(el){
         console.log(el,'event drop object')
       })
    };
    /* alert on Resize */
    $scope.alertOnResize = function(event, delta, revertFunc, jsEvent, ui, view ){
      var startDate = new Date(event.start._d);
      startDate.setHours(startDate.getHours()+5);
      var endDate = new Date(event.end._d);
      endDate.setHours(endDate.getHours()+5);
      var currObject = {
        _id: event._id,
        start: startDate,
        end: endDate,
        title: event.title,
          emailBool:event.emailBool,
          emailTime: event.emailTime,
          textBool:event.textBool,
          textTime: event.textTime,
          notificationBool:event.notificationBool,
          notificationTime:event.notificationTime,
        }
      CalendarService.editDate(currObject,$routeParams.weddingId).success(function(el){
        console.log('event resize object',el);
      })
      // $scope.alertMessage = ('Event Resized to make dayDelta ' + event.end._d);
    };

    /* add and removes an event source of choice */
    // $scope.addRemoveEventSource = function(sources,source) {
    //   var canAdd = 0;
    //   angular.forEach(sources,function(value, key){
    //     if(sources[key] === source){
    //       sources.splice(key,1);
    //       canAdd = 1;
    //     }
    //   });
    //   if(canAdd === 0){
    //     sources.push(source);
    //   }
    // };
    /* add custom event*/
    $scope.addEvent = function() {
      var newEvent = {
        title: 'Open Sesame',
        start: new Date(y, m, 22,5,10),
        end: new Date(y, m, 22,6,15),
          emailBool:false,
          emailTime: "3600000",
          textBool:false,
          textTime: "1800000",
          notificationBool:false,
          notificationTime:"1800000",
      };
      CalendarService.addDate(newEvent,$routeParams.weddingId).success(function(res){
        console.log('addDate',res);
        $scope.events.splice(0, $scope.events.length);
        $scope.updateEvents();
      });

    };
    /* remove event */
    $scope.remove = function(index,event) {
      CalendarService.deleteDate(event,$routeParams.weddingId).success(function(res){
        $scope.events.splice(0, $scope.events.length);
        $scope.updateEvents();
      });
    };
    /* Change View */
    $scope.changeView = function(view,calendar) {
      uiCalendarConfig.calendars[calendar].fullCalendar('changeView',view);
    };
    /* Change View */
    $scope.renderCalender = function(calendar) {
      if(uiCalendarConfig.calendars[calendar]){
        uiCalendarConfig.calendars[calendar].fullCalendar('render');
      }
    };
     /* Render Tooltip */
    // $scope.eventRender = function(event, element, view ) {
    //     element.attr({'tooltip': event.title,
    //                  'tooltip-append-to-body': true});
    //     $compile(element)($scope);
    // };
    /* config object */
    $scope.uiConfig = {
      calendar:{
        height: 450,
        editable: true,
        header:{
          left: 'title',
          center: '',
          right: 'today prev,next'
        },
        eventClick: $scope.alertOnEventClick,
        eventDrop: $scope.alertOnDrop,
        eventResize: $scope.alertOnResize,
        eventRender: $scope.eventRender
      }
    };

    // $scope.changeLang = function() {
    //   if($scope.changeTo === 'Hungarian'){
    //     $scope.uiConfig.calendar.dayNames = ["Vasárnap", "Hétfő", "Kedd", "Szerda", "Csütörtök", "Péntek", "Szombat"];
    //     $scope.uiConfig.calendar.dayNamesShort = ["Vas", "Hét", "Kedd", "Sze", "Csüt", "Pén", "Szo"];
    //     $scope.changeTo= 'English';
    //   } else {
    //     $scope.uiConfig.calendar.dayNames = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
    //     $scope.uiConfig.calendar.dayNamesShort = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
    //     $scope.changeTo = 'Hungarian';
    //   }
    // };
    /* event sources array*/
    // $scope.eventSources = [$scope.events, $scope.eventSource, $scope.eventsF];


  })
})();
