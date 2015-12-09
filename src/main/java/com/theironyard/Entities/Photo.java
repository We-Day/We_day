package com.theironyard.Entities;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;

/**
 * Created by benjamindrake on 12/9/15.
 */
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue
    int id;

    @Column (nullable= false)
    public String fileName;

    public String description;
}
