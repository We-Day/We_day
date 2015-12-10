package com.theironyard.Entities;

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
    public boolean isAdmin;

    public User (){}

    public User(int id, String userName, String phone, String zip, String address, String email, String password, boolean isAdmin) {
        this.id = id;
        this.userName = userName;
        this.phone = phone;
        this.zip = zip;
        this.address = address;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }
}
