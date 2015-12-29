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
import java.time.LocalDate;
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
    public Wedding createWedding(@RequestBody Wedding wedding, HttpSession session, MultipartFile file) throws Exception {
        weddings.save(wedding);
        User user = users.findOneByEmail((String) session.getAttribute("email"));

        if (user == null) {
            throw new Exception("User does not exist");
        }
        Invite invite =  new Invite();
        invite.isAdmin= true;
        invite.wedding= wedding;
        invite.email = user.email;
        createInvite(invite, invite.email, session);
        return wedding;
    }

    @RequestMapping(path = "/create-wedding", method = RequestMethod.GET)
    public List<Wedding> allWeddings() {
        return (List<Wedding>) weddings.findAll();
    }

    @RequestMapping("/create-invite")
    public void createInvite(@RequestBody Invite invitee, String emailDestination, HttpSession session) throws Exception {
        Wedding wedding = weddings.findOne(invitee.wedding.id);
        if (wedding == null) {
            throw new Exception("Wedding does not exist");
        }
        invites.save(invitee);
        sendEmail(emailDestination, session);
    }

    //this is for people who have been invited, coming to the site and becoming guests.
    @RequestMapping(path = "/confirmed-guest", method = RequestMethod.POST)
    public User confirmedUser (String email, String password, String phone) throws Exception {
        Invite invite = invites.findOneByEmail(email);
        if (invite == null){
            throw new Exception("no email found");
        }else {
            User user = new User();
            user.email = invite.email;
            user.username = invite.username;
            user.phone = phone;
            user.password = password;
            users.save(user);
            return user;
            // what about transferring the isAdmin variable?
            // Do we want to delete the invite after we make them a user?
        }
    }

    @RequestMapping(path = "/create-guest", method = RequestMethod.POST)
    public List <Invite> guestList(@RequestBody Params param){
        Wedding wedding = weddings.findOne(param.weddingId);
        Invite invite = new Invite();
        invite.wedding = wedding;
        invite.email = param.email;
        invite.isAdmin = param.isAdmin;
        invite.username = param.username;
        invites.save(invite);
        return invites.findByWeddingId(param.weddingId);
    }

    @RequestMapping("/invites")
    public List<Wedding> invitesList(HttpSession session) {
        String email = (String) session.getAttribute("email");
        return invites.findByEmail(email).stream()
                .map(invite -> {
                    return invite.wedding;
                }).collect(Collectors.toCollection(ArrayList<Wedding>::new));
    }

    @RequestMapping(path="/photos", method = RequestMethod.GET)
    public List<Photo> photoList(HttpSession session, @RequestBody Params param) {
        return photos.findByWedding(param.weddingId);
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
        ArrayList<Object> userLogin = new ArrayList<>();
        boolean isUser;

        User u = users.findOneByEmail(user.email);

        if (u == null) {
            response.sendError(403);

        } else if (PasswordHash.validatePassword(user.password, u.password)) {
            if (invites.findByEmail(u.email) != null) {
                session.setAttribute("email", u.email);
                session.setAttribute("id", u.id);
                isUser = true;
                userLogin.add(isUser);
                userLogin.add(user);
                return userLogin;
            }

            } else if (!PasswordHash.validatePassword(user.password, u.password)) {
                isUser = false;
                userLogin.add(isUser);
                userLogin.add(user);
                return userLogin;
            }
            return null;
        }

    @RequestMapping ("/join-wedding")
    public List <Wedding> joinWeddings(String email, String password, String phone) throws Exception {

        Invite invite = invites.findOneByEmail(email);

        if (invite == null){
            throw new Exception("No Guest Found by that Email");
        }

        if (invite != null) {
            User user = new User();
            user.isAdmin = invite.isAdmin;
            user.username = invite.username;
            user.phone = phone;
            user.email = email;
            user.password = password;
            users.save(user);
            invites.delete(invite);
        }
        return weddings.findById(invite.wedding.id);
    }


    @RequestMapping("/create-user")
    public ArrayList<Object> Login(@RequestBody User user)
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        ArrayList<Object> createUser = new ArrayList<>();
        boolean exists;

        User user1 = users.findOneByEmail(user.email);

        if (user1 == null) {
            exists = true;
            user.password = PasswordHash.createHash(user.password);
            user.isAdmin = true;
            users.save(user);
            createUser.add(user);
            createUser.add(exists);
            return createUser;

        } else if (user1 != null) {
            exists = false;
            createUser.add(user);
            createUser.add(exists);
            return createUser;
        }
        return null;
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
    public void sendNotification(String body, HttpSession session) throws TwilioRestException, MessagingException {
        ArrayList <String> numbers = new ArrayList<>();
        String id = (String)session.getAttribute("id");
        Wedding wedding = weddings.findOne(Integer.valueOf(id));
        Iterable<User> allUsers = (Iterable)wedding.user; // change to wedding-specific users
        for (User user : allUsers){                       // maybe send weddingId through Params again & loop over users
            String phone = user.phone;
            String email = user.email;
            numbers.add(phone);
            numbers.add(email);
            for (String phoneDestination : numbers){
                sendText(phoneDestination, body);
            }
            for (String notificationEmail : numbers) {
                sendNotificationEmail(notificationEmail, session);
            }
        }
    }
    // THIS HAS TO BE ALTERED TO MAKE IT WEDDING SPECIFIC INSTEAD OF ALL USERS.

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

    @RequestMapping(path = "/photo-upload", method = RequestMethod.POST)
    public Photo upload(HttpSession session, HttpServletResponse response, MultipartFile pic,@RequestBody Params param) throws IOException {
        File photoFile = File.createTempFile("pic", pic.getOriginalFilename(), new File("public/pics"));
        FileOutputStream fos = new FileOutputStream(photoFile);
        fos.write(pic.getBytes());

        Photo p = new Photo();
        p.fileName = photoFile.getName();
        p.description = param.description;
        p.wedding = param.wedding;

        photos.save(p);

        return p;
    }

    @RequestMapping(path="/logout", method = RequestMethod.POST)
    public void logout(HttpSession session){
        session.invalidate();
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

    public static void sendEmail(String emailDestination, HttpSession session) throws MessagingException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(WeDayConfig.class);
        ctx.refresh();
        JavaMailSenderImpl mailSender = ctx.getBean(JavaMailSenderImpl.class);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);
        mailMsg.setFrom("weday22@gmail.com");
        mailMsg.setReplyTo(String.valueOf(session.getAttribute("email")));
        mailMsg.setTo(emailDestination);
        mailMsg.setSubject("You've just been invited to their wedding!");
        mailMsg.setText("You've been invited to a wedding!");
        mailSender.send(mimeMessage);
    }

    public static void sendNotificationEmail(String notificationDestination, HttpSession session) throws MessagingException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(WeDayConfig.class);
        ctx.refresh();
        JavaMailSenderImpl mailSender = ctx.getBean(JavaMailSenderImpl.class);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);
        mailMsg.setFrom("weday22@gmail.com");
        mailMsg.setReplyTo(String.valueOf(session.getAttribute("email")));
        mailMsg.setTo(notificationDestination);
        mailMsg.setSubject("Wedding Notification");
        mailMsg.setText("This is a test notification for a wedding!");
        mailSender.send(mimeMessage);
    }
}