package com.theironyard.Contollers;
import com.theironyard.Entities.*;
import com.theironyard.Entities.Photo;
import com.theironyard.Entities.Post;
import com.theironyard.Entities.User;
import com.theironyard.Services.*;
import com.theironyard.Utilities.Params;
import com.theironyard.Utilities.PasswordHash;
import com.theironyard.WeDayConfig;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.social.facebook.api.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
    PostRepository posts;

    @Autowired
    PhotoRepository photos;

    @Autowired
    InviteRepository invites;

    @Autowired
    CalendarEventRepository events;

    @RequestMapping(path = "/create-wedding", method = RequestMethod.POST)
    public Wedding createWedding(@RequestBody Wedding wedding, HttpSession session) throws Exception {
        weddings.save(wedding);

        User user = users.findOneByEmail((String) session.getAttribute("email"));

        if (user == null) {
            throw new Exception("User does not exist");
        }
        Invite invite =  new Invite();
        invite.isAdmin= true;
        invite.wedding= wedding;
        invite.email = user.email;
        createInvite(invite);
        return wedding;
    }

    @RequestMapping(path = "/create-wedding", method = RequestMethod.GET)
    public List<Wedding> allWeddings() {
        return (List<Wedding>) weddings.findAll();
    }

    @RequestMapping("/create-invite")

    public void createInvite(@RequestBody Invite invitee) throws Exception {
        Wedding wedding = weddings.findOne(invitee.wedding.id);
        if (wedding == null) {
            throw new Exception("Wedding does not exist");
        }
        //something to do with passwords needs to be in here.
        invites.save(invitee);
        /*send email here*/
    }

    @RequestMapping("/invites")
    public List<Wedding> invitesList(HttpSession session) {
        String email = (String)session.getAttribute("email");
        return invites.findByEmail(email).stream()
                .map(invite -> {
                    return invite.wedding;
                }).collect(Collectors.toCollection(ArrayList<Wedding>::new));
    }

    @RequestMapping(path = "/create-wedding/{id}", method = RequestMethod.GET)
    public Wedding findOne(@PathVariable("id") int id) {
        return weddings.findOne(id);
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public User findUser(@PathVariable("id") int id) {
        User u = users.findOne(id);
        u.password = null;
        return u;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ArrayList <Object> userLogin(@RequestBody Params user,HttpSession session,
                             HttpServletResponse response) throws Exception {
        ArrayList <Object> userLogin = new ArrayList<>();
        boolean isUser;

        User u = users.findOneByEmail(user.email);

        if (u == null) {
            response.sendError(403);

        } else if (PasswordHash.validatePassword(user.password, u.password)) {
            if (invites.findByEmail(u.email)!=null) {
                session.setAttribute("email",u.email);
                session.setAttribute("id",u.id);
                isUser = true;
                userLogin.add(isUser);
                userLogin.add(user);
                return userLogin;
            } else {
                session.setAttribute("email",u.email);
                session.setAttribute("id",u.email);
            }

        } else if (!PasswordHash.validatePassword(user.password, u.password)) {
            isUser = false;
            userLogin.add(isUser);
            userLogin.add(user);
            return userLogin;

        }
        return null;
    }

    @RequestMapping("/create-user")
    public String createUser (@RequestBody User user) throws Exception {
        User u = users.findOneByEmail(user.email);
        if (u.email != null) {
            return "User already exists";
        }
        user.password = PasswordHash.createHash(user.password);
        users.save(user);
        return "Success";
    }

    @RequestMapping("/current-user")
    public User currentUser(HttpSession session){
        int id = (int)session.getAttribute("id");
        User user = users.findOne(id);
        user.password = null;
        return user;
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

    @RequestMapping (path = "/send-notification", method = RequestMethod.POST)
    public void sendNotification(String body) throws TwilioRestException, MessagingException {
        ArrayList <String> numbers = new ArrayList<>();
        Iterable<User> allUsers = users.findAll();
        for (User user : allUsers){
            String phone = user.phone;
            numbers.add(phone);
            for (String phoneDestination : numbers){
                sendText(phoneDestination, body);
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

        return p;
    }

    public static void sendText(String destination, String body) throws TwilioRestException, MessagingException {

    TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("Body", body));
    params.add(new BasicNameValuePair("To", destination));
    params.add(new BasicNameValuePair("From", "+18436405964"));

    MessageFactory messageFactory = client.getAccount().getMessageFactory();
    Message message = messageFactory.create(params);
    System.out.println(message.getSid());
}

    public static void sendEmail(String destination, String body, HttpSession session) throws MessagingException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(WeDayConfig.class);
        ctx.refresh();
        JavaMailSenderImpl mailSender = ctx.getBean(JavaMailSenderImpl.class);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);
        mailMsg.setFrom("weday22@gmail.com");
        mailMsg.setReplyTo(String.valueOf(session.getAttribute("email")));
        mailMsg.setTo(destination);
        mailMsg.setSubject("You've just been invited to their wedding!");
        mailMsg.setText("Hello World!");
        mailSender.send(mimeMessage);
    }
}