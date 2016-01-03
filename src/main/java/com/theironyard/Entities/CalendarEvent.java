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

        public CalendarEvent(){

        }


        public Boolean emailBool;
        public String emailTime;

        public Boolean textBool;
        public String textTime;

        public Boolean notificationBool;
        public Boolean notificationTime;

        public Boolean getNotificationTime() {
            return notificationTime;
        }

        public void setNotificationTime(Boolean notificationTime) {
            this.notificationTime = notificationTime;
        }

        public Boolean getNotificationBool() {
            return notificationBool;
        }

        public void setNotificationBool(Boolean notificationBool) {
            this.notificationBool = notificationBool;
        }

        public String getTextTime() {
            return textTime;
        }

        public void setTextTime(String textTime) {
            this.textTime = textTime;
        }

        public Boolean getTextBool() {
            return textBool;
        }

        public void setTextBool(Boolean textBool) {
            this.textBool = textBool;
        }

        public String getEmailTime() {
            return emailTime;
        }

        public void setEmailTime(String emailTime) {
            this.emailTime = emailTime;
        }

        public Boolean getEmailBool() {
            return emailBool;
        }

        public void setEmailBool(Boolean emailBool) {
            this.emailBool = emailBool;
        }


    }