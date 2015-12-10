package com.theironyard.Contollers;
import com.theironyard.Entities.Photo;
import com.theironyard.Entities.Post;
import com.theironyard.Entities.Wedding;
import com.theironyard.Services.PhotoRepository;
import com.theironyard.Services.PostRepository;
import com.theironyard.Utilities.PasswordHash;
import com.theironyard.Services.WeddingRepository;
import com.theironyard.Entities.User;
import com.theironyard.Services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by macbookair on 12/8/15.
 */

@RestController
public class WeDayController {

    @Autowired
    UserRepository users;
    @Autowired
    WeddingRepository weddings;
    @Autowired
    PostRepository posts;
    @Autowired
    PhotoRepository photos;

    @RequestMapping("/create-wedding")
    public void createWedding(@RequestBody Wedding wedding){
        weddings.save(wedding);
    }

    @RequestMapping ("/login")
    public void login(@RequestBody User user, String username, HttpSession session){
        session.setAttribute("username",username);

        user = users.findOneByusername(username);
        if (user == null){
        }
    }

    @RequestMapping ("/create-admin")
    public void createAdmin(@RequestBody User user){
        if (user.isAdmin == null){
            user.isAdmin = true;
        }
        users.save(user);
    }

    @RequestMapping("/create-post")
    public Iterable  createPost(@RequestBody Post post,  HttpSession session) throws Exception {
        String userName = (String)session.getAttribute("username");
        if (userName ==null){
            throw new Exception("Not Logged in");
        }
        posts.save(post);
        return posts.findAll();
    }

    @RequestMapping("/photo-upload")
    public Photo upload (@RequestBody Photo photo, HttpSession session, MultipartFile file) throws Exception {
        String userName = (String) session.getAttribute("username");
        if (userName == null){
            throw new Exception("Not Logged in");
        }
        File photoFile = File.createTempFile("file", file.getOriginalFilename(), new File("public"));
        FileOutputStream fos = new FileOutputStream(photoFile);
        fos.write(file.getBytes());
        photos.save(photo);

        return photo;
    }

}
