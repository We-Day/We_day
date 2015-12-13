package com.theironyard.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbookair on 12/8/15.
 */
@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column (nullable = false)
    public int id;

    @Column (nullable = false)
    public String username;

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
    public Boolean isAdmin;

    /*@ManyToMany (mappedBy = "users")
    public List<Wedding> weddings = new ArrayList<>();*/

    public User (){}

    public User(int id, String username, String phone, String zip, String address, String email, String password, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.zip = zip;
        this.address = address;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }
}
