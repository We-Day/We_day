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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column
    public String text;

    @ManyToOne
    public Wedding wedding;


}