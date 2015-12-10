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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public void createWedding(String date, String weddingName,
                              String location, String userName,
                              String phone, String zip,
                              String address, String password,
                              @RequestParam(defaultValue = "true") boolean isAdmin, String email) throws Exception{

        Wedding wedding = new Wedding();
        wedding.date = LocalDate.parse(date);
        wedding.location = location;
        wedding.weddingName = weddingName;
        weddings.save(wedding);

        User user = new User();
        user.userName = userName;
        user.zip = zip;
        user.address = address;
        user.password = PasswordHash.createHash(password);
        user.email = email;
        user.phone = phone;
        user.isAdmin = isAdmin;
        users.save(user);
    }

    @RequestMapping ("/create-admin")
    public void createAdmin(String userName, String zip,
                            String address, String password,
                            String email, String phone,
                            @RequestParam(defaultValue = "true") boolean isAdmin){

        User user = new User();
        user.userName = userName;
        user.zip = zip;
        user.address = address;
        user.password = password;
        user.email = email;
        user.phone = phone;
        user.isAdmin = isAdmin;
        users.save(user);
    }

    @RequestMapping("/create-post")
    public Iterable  createPost(HttpSession session, String text) throws Exception {
        String userName = (String)session.getAttribute("userName");
        if (userName ==null){
            throw new Exception("Not Logged in");
        }

        Post post = new Post();
        post.text = text;
        post.sender = userName;
        posts.save(post);

        return posts.findAll();
    }

    @RequestMapping("/photo-upload")
    public Photo upload (HttpSession session, HttpServletResponse response, MultipartFile file, String fileName, String description) throws IOException {
        String username = (String) session.getAttribute("username");

        File photoFile = File.createTempFile("file", file.getOriginalFilename(), new File("public"));
        FileOutputStream fos = new FileOutputStream(photoFile);
        fos.write(file.getBytes());

        Photo p = new Photo();
        p.fileName = photoFile.getName();
        p.description = description;
        photos.save(p);


        response.sendRedirect("/");

        return p;
    }

}
