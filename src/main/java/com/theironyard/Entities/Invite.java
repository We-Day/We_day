package com.theironyard.Entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by benjamindrake on 12/14/15.
 */
@Entity
@Table(name = "invites")
public class Invite {
    @Id
    @GeneratedValue
    public int id;

    @Column (nullable = false)
    public Boolean isAdmin;

    @Column (nullable = false)
    public String email;

    @Column(nullable = true)
    public String username;

    @ManyToOne
    public Wedding wedding;

    }

