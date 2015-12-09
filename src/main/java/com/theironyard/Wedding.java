package com.theironyard;

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
    int Id;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public String location;

    @Column(nullable = false)
    public String date;
}
