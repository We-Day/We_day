package com.theironyard.Entities;

import javax.persistence.*;

/**
 * Created by macbookair on 12/9/15.
 */
@Entity
@Table (name = "posts")
public class Post {
    @Id
    @GeneratedValue
    public int id;

    @Column (nullable = false)
    public String text;

    @Column (nullable = false)
    public String sender;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}