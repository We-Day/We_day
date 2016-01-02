    package com.theironyard.Entities;

    import javax.persistence.*;
    import java.time.LocalDate;
    import java.time.LocalDateTime;

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

        public class Email {
            boolean send;
            int time; // hours before sending email
        }
        @Transient
        public Email email;

        public class Text {
            boolean send;
            int time;
        }

        @Transient
        public Text text;


        public class Notification {
            boolean send;
            int time;
        }

        @Transient
        public Notification notification;

    }