package com.theironyard.Entities;

import javax.persistence.*;

/**
 * Created by benjamindrake on 12/14/15.
 */
@Entity
@Table(name = "invites")
public class Invite {
    @Id
    @GeneratedValue
    int Id;

    @Column (nullable = false)
    public Boolean isAdmin;

    @ManyToOne
    public User user;

    @ManyToOne
    public Wedding wedding;
}
