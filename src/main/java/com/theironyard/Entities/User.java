package com.theironyard.Entities;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by macbookair on 12/8/15.
 */
@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    public int id;

    @Column(nullable = false)
    public String username;

    @Column(nullable = false)
    public String phone;

    @Column(nullable = false)
    public String email;

    @Column(nullable = false)
    public String password;

    public ArrayList<Wedding>userWeddings;

    public User() {
    }

    public User(ArrayList<Wedding> userWeddings) {
        this.userWeddings = userWeddings;
    }

    public User(int id, String username, String phone, String email, String password) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;

    }

    public ArrayList<Wedding> getUserWeddings() {
        return userWeddings;
    }

    public void setUserWeddings(ArrayList<Wedding> userWeddings) {
        this.userWeddings = userWeddings;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
