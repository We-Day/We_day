package com.theironyard.Services;

import com.theironyard.Entities.Invite;
import com.theironyard.Entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by benjamindrake on 12/14/15.
 */
public interface InviteRepository extends CrudRepository<Invite, Integer> {
     List<Invite> findByUser(User user);
}
