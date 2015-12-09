package com.theironyard;

import javax.persistence.*;

/**
 * Created by macbookair on 12/8/15.
 */
@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column (nullable = false)
    int id;

    @Column (nullable = false)
    public String userName;

    @Column (nullable = false)
    public String phone;

    @Column (nullable = false)
    public String zip;

    @Column (nullable = false)
    public String address;

    @Column (nullable = false)
    public String email;

    @Column (nullable = false)
    public String password;

    @Column (nullable = false)
    boolean isAdmin;



}
