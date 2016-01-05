package com.theironyard.Utilities;

import com.theironyard.Entities.*;
import org.apache.tomcat.jni.Local;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by benjamindrake on 12/18/15.
 */
public class Params {

    public User user;

    public Wedding wedding;

    public Post post;

    public Photo photo;

    public Invite invite;

    public CalendarEvent calendarEvent;

    public String email;

    public String password;

    public boolean isAdmin;

    public String username;

    public Integer weddingId;

    public String description;

    public String title;

    public String wedId;

    public String storyContent;

    public int _id;

    public String start;

    public String end;

    public boolean notificationEmail = false;

    public boolean text = false;

    //public ArrayList<Object> emailDump;

    //public ArrayList<Object> textDump;

    //public ArrayList<Object> notifcationDump;

    public Boolean emailBool;
    public String emailTime;

    public Boolean textBool;
    public String textTime;

    public Boolean notificationBool;
    public String notificationTime;
}

