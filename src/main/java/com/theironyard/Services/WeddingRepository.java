package com.theironyard.Services;
import com.theironyard.Entities.Wedding;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by macbookair on 12/8/15.
 */
public interface WeddingRepository extends CrudRepository<Wedding,Integer>{
    Wedding findOneByweddingName (String weddingName);

}
