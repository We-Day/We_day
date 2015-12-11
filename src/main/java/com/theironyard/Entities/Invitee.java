package com.theironyard.Entities;

import org.springframework.data.repository.CrudRepository;

import javax.annotation.Generated;
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
    int id;

    @Column (nullable = false)
    String name;

    @Column (nullable = false)
    String email;

}
