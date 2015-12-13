package com.theironyard.Contollers;
import com.theironyard.Entities.Photo;
import com.theironyard.Entities.Post;
import com.theironyard.Entities.Wedding;
import com.theironyard.Services.*;
import com.theironyard.Entities.User;
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
    public WeDayController (Facebook facebook){
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
                              HttpServletResponse response,
                              User user,
                              HttpSession session) throws IOException {
        session.setAttribute("User", user);
        weddings.save(wedding);

        response.sendRedirect("admin/{id}");
    }

    @RequestMapping(path = "/create-wedding", method = RequestMethod.GET)
    public List<Wedding> AllWeddings (){
        return (List<Wedding>) weddings.findAll();
    }

    @RequestMapping(path = "/create-wedding/{id}", method = RequestMethod.GET)
    public Wedding findOne(@RequestBody Wedding wedding){
        return weddings.findOne(wedding.id);
    }

    public void login(@RequestBody User user, String username, HttpSession session){
        session.setAttribute("username",username);
    }

    @RequestMapping ("/create-admin")
    public void createAdmin(@RequestBody User user){
        if (user.isAdmin == null){
            user.isAdmin = true;
        }
        users.save(user);
    }

    @RequestMapping ("/create-user")
    public void createUser(@RequestBody User user){
        if (user.isAdmin ==null){
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
            response.sendRedirect("/connect/facebook");
        }
        return null;
    }


    @RequestMapping("/create-post")
    public Iterable  createPost(@RequestBody Post post, HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("Not Logged in");
        }
        posts.save(post);
        return posts.findAll();
    }

    @RequestMapping("/photo-upload")
    public Photo upload (@RequestBody Photo photo, HttpSession session, MultipartFile file) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null){
            throw new Exception("Not Logged in");
        }
        File photoFile = File.createTempFile("file", file.getOriginalFilename(), new File("public"));
        FileOutputStream fos = new FileOutputStream(photoFile);
        fos.write(file.getBytes());
        photos.save(photo);

        return photo;
    }

}
