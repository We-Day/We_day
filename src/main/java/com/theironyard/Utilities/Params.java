package com.theironyard.Utilities;

import com.theironyard.Entities.Invite;
import com.theironyard.Entities.User;
import com.theironyard.Entities.Wedding;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by benjamindrake on 12/18/15.
 */
public class Params {
    public User user;

    public Wedding wedding;

    public String email;

    public String password;

    public boolean isAdmin;

    public String username;

    public Integer weddingId;

    public String description;
}
