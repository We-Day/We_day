package com.theironyard.Services;

import com.theironyard.Entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by macbookair on 12/8/15.
 */
public interface UserRepository extends CrudRepository<User,Integer>{
    User findOneByEmail (String email);
    List<User> findByWeddingId(Integer Id);
}
