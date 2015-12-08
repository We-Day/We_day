package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


/**
 * Created by macbookair on 12/8/15.
 */

@RestController
public class WeDayController {

    @Autowired
    UserRepository users;
    @Autowired
    WeddingRepository weddings;

    @RequestMapping("/create-wedding")
    public void createWedding(String date, String name,
                              String location, String userName,
                              String phone, String zip,
                              String address, String password,
                              @RequestParam(defaultValue = "true") boolean isAdmin, String email) {


        Wedding wedding = new Wedding();
        wedding.date = date;
        wedding.location = location;
        wedding.name = name;
        weddings.save(wedding);

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
}
