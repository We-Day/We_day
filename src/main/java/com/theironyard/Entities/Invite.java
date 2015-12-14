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

    @ManyToOne
    public User user;

    @ManyToOne
    public Wedding wedding;
}
