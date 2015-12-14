package com.theironyard.Entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by macbookair on 12/11/15.
 */
@Entity
public class Invitee{
    @Id
    @GeneratedValue
    public int id;

    @Column (nullable = false)
    public String name;

    @Column (nullable = false)
    public String email;

    @Column(nullable = false)
    public int weddingId;

    @Column (nullable = false)
    public Boolean isAdmin;
}
