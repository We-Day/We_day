package com.theironyard.Services;

import com.theironyard.Entities.Invitee;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by macbookair on 12/11/15.
 */
public interface InviteeRepository extends CrudRepository<Invitee,Integer> {
    Invitee findOneById (Invitee invitee);
}
