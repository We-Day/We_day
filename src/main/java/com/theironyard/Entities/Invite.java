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
    int Id;

    @Column (nullable = false)
    public Boolean isAdmin;

    @Column (nullable = false)
    public String email;

    @ManyToOne
    public Wedding wedding;

    }

