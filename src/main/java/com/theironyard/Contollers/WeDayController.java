package com.theironyard.Contollers;
import com.theironyard.Entities.*;
import com.theironyard.Entities.Photo;
import com.theironyard.Entities.Post;
import com.theironyard.Entities.User;
import com.theironyard.Services.*;
import com.theironyard.Utilities.PasswordHash;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by macbookair on 12/8/15.
 */



@RestController
public class WeDayController {


    public static final String ACCOUNT_SID = "ACccdbc98b4c34f1609bd410b42ea63155";
    public static final String AUTH_TOKEN = "7523c186f7b532e10dac3f764d5c0ece";

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

    @Autowired
    InviteRepository invites;

    @Autowired
    CalendarEventRepository events;

    @RequestMapping(path = "/create-wedding", method = RequestMethod.POST)
    public Wedding createWedding(@RequestBody Wedding wedding){
        return weddings.save(wedding);
    }

    @RequestMapping(path = "/create-wedding", method = RequestMethod.GET)
    public List<Wedding> AllWeddings() {
        return (List<Wedding>) weddings.findAll();
    }

    @RequestMapping("/create-invite")
    public void createInvite(@RequestBody Invitee invitee) throws Exception {
        User user = users.findOneByEmail(invitee.email);
        if (user == null) {
            user= new User();
            user.email = invitee.email;
            user.username = invitee.name;
            users.save(user);

            //something to do with passwords needs to be in here.
        }
        Wedding wedding = weddings.findOne(invitee.weddingId);
        if (wedding == null) {
            throw new Exception("Wedding does not exist");
        }
        Invite invite= new Invite();
        invite.user = user;
        invite.isAdmin = invitee.isAdmin;
        invite.wedding = wedding;
        invites.save(invite);
        /*send email here*/
    }

    @RequestMapping("/invites")
    public List<Wedding> invitesList(@RequestBody User user) {
        return invites.findByUser(user).stream()
                .map(invite -> {
                    return invite.wedding;
                }).collect(Collectors.toCollection(ArrayList<Wedding>::new));
    }

    @RequestMapping(path = "/create-wedding/{id}", method = RequestMethod.GET)
    public Wedding findOne(Wedding wedding) {
        return weddings.findOne(wedding.id);
    }

    @RequestMapping("/login")
    public void userLogin(String email,HttpSession session,
                          String password, HttpServletResponse response) throws Exception {

        session.setAttribute("email",email);

        User user = users.findOneByEmail(email);

        if (user == null) {
            throw new Exception("User does not exist. Please create an account");

        } else if (PasswordHash.validatePassword(password, user.password)) {
            response.sendRedirect("/landing/" + user.id);

        } else if (!PasswordHash.validatePassword(password, user.password)) {
            throw new Exception("Password is incorrect");
        }
    }

    @RequestMapping("/create-user")
    public User createUser (@RequestBody User user) {
        User u = new User();
        users.save(u);
        return u;
    }

    @RequestMapping("/profile")
    public org.springframework.social.facebook.api.User getUser(HttpServletResponse response) throws IOException {

        try {
            org.springframework.social.facebook.api.User user = facebook.userOperations().getUserProfile();
            return user;
        } catch (Exception e) {
            response.sendRedirect("http://localhost8080/#/create-weddings");
        }
        return null;
    }

    @RequestMapping ("/send-notification")
    public void sendNotification(String body) throws TwilioRestException {
        ArrayList <String> numbers = new ArrayList<>();
        Iterable<User> allUsers = users.findAll();
        for (User user : allUsers){
            String phone = user.phone;
            numbers.add(phone);
                for (String destination : numbers){
                    sendText(destination, body);
            }
        }
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

    @RequestMapping("/create-event")
    public CalendarEvent createEvent(@RequestBody CalendarEvent event){
        return events.save(event);
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

        // not sure where to redirect here...response.sendRedirect("/?");

        return p;
    }

    public static void sendText(String destination, String body) throws TwilioRestException {

        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Body", body));
        params.add(new BasicNameValuePair("To", destination));
        params.add(new BasicNameValuePair("From", "+18436405964"));

        MessageFactory messageFactory = client.getAccount().getMessageFactory();
        Message message = messageFactory.create(params);
        System.out.println(message.getSid());
    }
}
