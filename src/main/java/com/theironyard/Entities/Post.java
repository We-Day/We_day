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
    public Integer id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column
    public String title;

    @ManyToOne
    public Wedding wedding;


}