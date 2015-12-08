package com.theironyard;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.ManyToOne;

/**
 * Created by macbookair on 12/8/15.
 */
public interface UserRepository extends CrudRepository<User,Integer>{

}
