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
        public int id;

        @Column(nullable = false)
        public LocalDateTime start;

        @Column(nullable = false)
        public LocalDateTime end;

        @Column(nullable = false)
        public String title;

        public Object email() {
            boolean send;
            int time; // hours before sending email
            return email();
        }

        public Object text() {
            boolean send;
            int time;
            return text();
        }

        public Object notification() {
            boolean send;
            int time;
            return notification();
        }

        public CalendarEvent(int id, LocalDateTime start, LocalDateTime end, String title) {
            this.id = id;
            this.start = start;
            this.end = end;
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public LocalDateTime getStart() {
            return start;
        }

        public void setStart(LocalDateTime start) {
            this.start = start;
        }

        public LocalDateTime getEnd() {
            return end;
        }

        public void setEnd(LocalDateTime end) {
            this.end = end;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }