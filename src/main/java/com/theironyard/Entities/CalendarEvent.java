    package com.theironyard.Entities;

    import javax.persistence.*;
    import java.time.LocalDate;
    import java.time.LocalDateTime;
    import java.util.ArrayList;

    /**
     * Created by macbookair on 12/16/15.
     */

    @Entity
    @Table (name = "events")
    public class CalendarEvent {
        @Id
        @GeneratedValue
        public int _id;

        @Column(nullable = false)
        public String start;

        @Column(nullable = false)
        public String end;

        @Column(nullable = false)
        public String title;

        @ManyToOne
        public Wedding wedding;

        public CalendarEvent(){

        }

        public class Email {
            boolean bool;
            String time; // hours before sending email
        }
//        @Transient
        public ArrayList<Object> email;

        public class Text {
            boolean bool;
            String time;
        }

//        @Transient
        public ArrayList<Object> text;

        public ArrayList<Object> getEmail() {
            return email;
        }

        public ArrayList<Object> getText() {
            return text;
        }

        public class Notification {
            boolean bool;
            String time;
        }

//        @Transient
        public ArrayList<Object> notification;

    }