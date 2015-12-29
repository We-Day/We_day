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
    public int id;

    @Column (nullable= false)
    public String fileName;

    public String description;

    @ManyToOne
    public Wedding wedding;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
