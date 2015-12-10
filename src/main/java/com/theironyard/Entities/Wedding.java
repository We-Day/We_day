package com.theironyard.Entities;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by macbookair on 12/8/15.
 */

@Entity
@Table(name = "weddings")
public class Wedding {
    @Id
    @GeneratedValue
    @Column(nullable = false)
     int id;

    @Column(nullable = false)
    public String weddingName;

    @Column(nullable = false)
    public String location;

    @Column(nullable = false)
    public LocalDate date;

    public Wedding(){
    }

    public Wedding(LocalDate date, String location, String weddingName, int id) {
        this.date = date;
        this.location = location;
        this.weddingName = weddingName;
        this.id = id;
    }
}
