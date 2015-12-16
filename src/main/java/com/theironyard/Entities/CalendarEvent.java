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
    public String id;

    @Column (nullable = false)
    public LocalDateTime start;

    @Column (nullable = false)
    public LocalDateTime end;

    @Column (nullable = false)
    public String title;


    public class email{
        boolean send; // default false;
        int time; // hours before sending email
    }

    public class text{

        boolean send;
        int time;

    }
}
