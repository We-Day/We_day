package com.theironyard.Entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by macbookair on 12/8/15.
 */

@Entity
@Table(name = "weddings")
public class Wedding {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    public int id;

    @Column(nullable = false)
    public String weddingName;

    @Column(nullable = false)
    public String location;

    @Column(nullable = false)
    public String date;

    public Wedding() {

    }

    public Wedding(int id, String weddingName, String location, String date) {
        this.id = id;
        this.weddingName = weddingName;
        this.location = location;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeddingName() {
        return weddingName;
    }

    public void setWeddingName(String weddingName) {
        this.weddingName = weddingName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}

