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

    @Column(nullable = true)
    public String title;

    @ManyToOne
    public Wedding wedding;

    public Boolean emailBool = false;
    public String emailTime;

    public Boolean textBool = false;
    public String textTime;

    public Boolean notificationBool = false;
    public String notificationTime;

    public CalendarEvent(){
    }


    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Wedding getWedding() {
        return wedding;
    }

    public void setWedding(Wedding wedding) {
        this.wedding = wedding;
    }

    public Boolean getEmailBool() {
        return emailBool;
    }

    public void setEmailBool(Boolean emailBool) {
        this.emailBool = emailBool;
    }

    public String getEmailTime() {
        return emailTime;
    }

    public void setEmailTime(String emailTime) {
        this.emailTime = emailTime;
    }

    public Boolean getTextBool() {
        return textBool;
    }

    public void setTextBool(Boolean textBool) {
        this.textBool = textBool;
    }

    public String getTextTime() {
        return textTime;
    }

    public void setTextTime(String textTime) {
        this.textTime = textTime;
    }

    public Boolean getNotificationBool() {
        return notificationBool;
    }

    public void setNotificationBool(Boolean notificationBool) {
        this.notificationBool = notificationBool;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }
}