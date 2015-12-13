package com.theironyard.Contollers;
import com.theironyard.Entities.*;
import com.theironyard.Services.*;
import com.theironyard.Utilities.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.social.facebook.api.Facebook;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by macbookair on 12/8/15.
 */

@RestController
public class WeDayController {

    Facebook facebook;

    @Inject
    public WeDayController(Facebook facebook) {
        this.facebook = facebook;
    }

    @Autowired
    UserRepository users;

    @Autowired
    WeddingRepository weddings;

    @Autowired
    InviteeRepository invitees;

    @Autowired
    PostRepository posts;

    @Autowired
    PhotoRepository photos;

    @RequestMapping(path = "/create-wedding", method = RequestMethod.POST)
    public void createWedding(@RequestBody Wedding wedding,
                              HttpServletResponse response) throws IOException {
        weddings.save(wedding);
        response.sendRedirect("/#/admins/{id}");
    }

    @RequestMapping(path = "/create-wedding", method = RequestMethod.GET)
    public List<Wedding> AllWeddings() {
        return (List<Wedding>) weddings.findAll();
    }

    @RequestMapping("/invitees")
    public void invitees(@RequestBody Invitee invitee){
        invitees.save(invitee);
    }

    @RequestMapping(path = "/create-wedding/{id}", method = RequestMethod.GET)
    public Wedding findOne(Wedding wedding) {
        return weddings.findOne(wedding.id);
    }

    @RequestMapping("/login")
    public void userLogin(HttpSession session, String email,
                          String password, HttpServletResponse response) throws Exception {

        session.setAttribute("email", email);

        User user = users.findOneByEmail(email);

        if (user == null) {
            throw new Exception("User does not exist. Please create an account");
        } else if (PasswordHash.validatePassword(password, user.password)) {
            response.sendRedirect("/landing/{id}");
        } else if (!PasswordHash.validatePassword(password, user.password)) {
            throw new Exception("Password is incorrect");
        }
    }

    @RequestMapping("/create-admin")
    public void createAdmin(@RequestBody User user) {
        if (user.isAdmin == null) {
            user.isAdmin = true;
        }
        users.save(user);
    }

    @RequestMapping("/create-user")
    public void createUser(@RequestBody User user) {
        if (user.isAdmin == null) {
            user.isAdmin = false;
            users.save(user);
        }
    }

    @RequestMapping("/profile")
    public org.springframework.social.facebook.api.User getUser(HttpServletResponse response) throws IOException {
        try {
            org.springframework.social.facebook.api.User user = facebook.userOperations().getUserProfile();
            return user;
        } catch (Exception e) {
            response.sendRedirect("/create-weddings");
        }
        return null;
    }

    @RequestMapping("/create-post")
    public Iterable createPost(@RequestBody Post post, HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("Not Logged in");
        }
        posts.save(post);
        return posts.findAll();
    }

    @RequestMapping("/photo-upload")
    public Photo upload(HttpSession session, HttpServletResponse response, MultipartFile file, String fileName, String description) throws IOException {
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
