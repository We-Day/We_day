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
import java.util.ListResourceBundle;
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

    @RequestMapping(path = "/story", method = RequestMethod.POST)
    public Wedding story (@RequestBody Params param, HttpSession session) {
        Wedding wedding = weddings.findOne(param.weddingId);
//        Post post = new Post();
//        post.text = param.storyContent.replace("<p>", "").replace("</p>", "");
//        post.wedding = wedding;
//        posts.save(post);
        wedding.storyContent = param.storyContent.replace("<p>", "").replace("</p>", "");
        weddings.save(wedding);
        return wedding;
    }


    @RequestMapping(path = "/story/{id}", method = RequestMethod.GET)
    public String story (@PathVariable("id") Integer id) {
        Wedding wedding = weddings.findOne(id);
        return wedding.storyContent;
  //      return posts.findOneByWedding();
    }

    @RequestMapping(path = "/create-wedding", method = RequestMethod.POST)
    public Wedding createWedding(@RequestBody Wedding wedding, HttpSession session) throws Exception {
        weddings.save(wedding);
        User user = users.findOneByEmail((String) session.getAttribute("email"));
        user.wedding = wedding;

        if (user == null) {
            throw new Exception("User does not exist");
        }
        Invite invite = new Invite();
        invite.isAdmin = true;
        invite.wedding = wedding;
        invite.username = user.username;
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

    @RequestMapping(path = "/create-guest", method = RequestMethod.POST)
    public void addInvite(@RequestBody Params param, HttpSession session) throws MessagingException {
        Wedding wedding = weddings.findOne(param.weddingId);
        Invite invite = new Invite();
        invite.wedding = wedding;
        invite.email = param.email;
        invite.isAdmin = param.isAdmin;
        invite.username = param.username;
        invites.save(invite);
        sendEmail(invite.email, session);
    }

    @RequestMapping(path = "/display-invites/{id}", method = RequestMethod.GET)
    public List<Invite> display(@PathVariable("id") int id) {
        return invites.findByWeddingId(Integer.valueOf(id));
    }

    @RequestMapping(path = "/delete-invite/{id}", method = RequestMethod.DELETE)
    public void deleteInvite(@PathVariable("id") int id) {
        Invite invite = invites.findOne(Integer.valueOf(id));
        invites.delete(invite);
    }

    @RequestMapping("/invites")
    public List<Wedding> invitesList(HttpSession session) {
        String email = (String) session.getAttribute("email");
        return invites.findByEmail(email).stream()
                .map(invite -> {
                    return invite.wedding;
                }).collect(Collectors.toCollection(ArrayList<Wedding>::new));
    }

    @RequestMapping(path = "/photos/{id}", method = RequestMethod.GET)
    public List<Photo> photoList(@PathVariable("id") int id) {
        Wedding one = weddings.findOne(id);
        List<Photo> all = photos.findByWedding(one);
        return all;
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
    public ArrayList<Object> userLogin(@RequestBody Params user, HttpSession session,
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

    @RequestMapping(path = "/create-user", method = RequestMethod.POST)
    public ArrayList<Object> Login(@RequestBody User user)
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        ArrayList<Object> createUser = new ArrayList<>();
        boolean exists;

        List<Invite> allInvites = (List) invites.findAll();

        User user1 = users.findOneByEmail(user.email);

        if (allInvites.size() == 0) {
            exists = true;
            user.password = PasswordHash.createHash(user.password);
            user.isAdmin = true;
            users.save(user);
            createUser.add(user);
            createUser.add(exists);
            return createUser;
        }

        if (!allInvites.isEmpty()) {
            for (Invite invite : allInvites) {
                String inviteEmail = invite.email;
                if (user.email.equals(inviteEmail)) {
                    User transferUser = new User();
                    transferUser.isAdmin = invite.isAdmin;
                    transferUser.username = invite.username;
                    transferUser.phone = user.phone;
                    transferUser.email = invite.email;
                    transferUser.password = PasswordHash.createHash(user.password);
                    transferUser.wedding = invite.wedding;
                    users.save(transferUser);
                    createUser.add(transferUser);
                    exists = true;
                    createUser.add(exists);
                    //invites.delete(invite);
                    return createUser;
                }
            }
            if (user1 == null) {
                exists = true;
                user.password = PasswordHash.createHash(user.password);
                user.isAdmin = true;
                users.save(user);
                createUser.add(user);
                createUser.add(exists);
                return createUser;

            } else if (user1 != null) {
                exists = true;
                createUser.add(user);
                createUser.add(exists);
                return createUser;
            }
        }
        return null;
    }

    @RequestMapping("/current-user")
    public User currentUser(HttpSession session) {
        int id = (int) session.getAttribute("id");
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

    @RequestMapping(path = "/send-notification", method = RequestMethod.POST)
    public void sendNotification(@RequestBody Params params, HttpSession session) throws TwilioRestException, MessagingException {
        ArrayList<String> numbers = new ArrayList<>();
        ArrayList<String> emails = new ArrayList<>();
        List<User> weddingUsers = users.findByWeddingId(Integer.valueOf(params.wedId));
        for (User user : weddingUsers) {
            String phone = user.phone;
            String email = user.email;
            numbers.add(phone);
            emails.add(email);
        }

        for (String phoneDestination : numbers) {
            sendText(phoneDestination, params.title);
        }
        for (String notificationEmail : emails) {
            sendNotificationEmail(notificationEmail, params.title, session);
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

    @RequestMapping(path = "/create-event/{id}", method = RequestMethod.POST)
    public void createEvent(@RequestBody CalendarEvent event, @PathVariable("id") int id) {
        event.wedding = weddings.findOne(id);
        events.save(event);
    }

    @RequestMapping(path = "/display-events/{id}", method = RequestMethod.GET)
    public List<CalendarEvent> displayEvents(@PathVariable("id") int id) {
        return events.findByWeddingId(id);
    }

    @RequestMapping(path = "delete-event/{id}", method = RequestMethod.DELETE)
    public void deleteEvent(@PathVariable("id")int id) {
        CalendarEvent event = events.findOne(id);
        events.delete(event);
    }

    @RequestMapping(path = "edit-event/{id}", method = RequestMethod.PUT)
    public void editEvent(@RequestBody Params event, @PathVariable("id") int id) {

        Wedding wedding = weddings.findOne(id);

        CalendarEvent event1 = events.findOne(event._id);

        event1.title = event.title;
        event1.wedding = wedding;
        event1.start = event.start;
        event1.end = event.end;

        CalendarEvent editedEvent = (CalendarEvent)event1;
        events.save(editedEvent);
    }

    @RequestMapping(path = "/photo-upload", method = RequestMethod.POST)
    public void upload(HttpSession session, HttpServletResponse response, MultipartFile pic, int weddingId, String description, String userId) throws IOException {
        File photoFile = File.createTempFile("pic", pic.getOriginalFilename(), new File("public/pics"));
        FileOutputStream fos = new FileOutputStream(photoFile);
        fos.write(pic.getBytes());

        Photo p = new Photo();
        p.fileName = photoFile.getName();
        p.description = description;
        p.wedding = weddings.findOne(weddingId);
        p.wedding = weddings.findOne(weddingId); // does this need to be in here twice?

        photos.save(p);
        User user = users.findOne(Integer.valueOf(userId));
        if(user.isAdmin){
            response.sendRedirect("/#/admins/"+ weddingId);
        }
        else {
            response.sendRedirect(("/#/user/"+ weddingId));
        }
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
        mailMsg.setText("You've been invited to a wedding! Please visit We-Day.com to stay up to date on all your wedding info!");
        mailSender.send(mimeMessage);
    }

    public static void sendNotificationEmail(String notificationDestination, String title, HttpSession session) throws MessagingException {
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
        mailMsg.setText(title);
        mailSender.send(mimeMessage);
    }
}
