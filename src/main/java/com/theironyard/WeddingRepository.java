package com.theironyard;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by macbookair on 12/8/15.
 */
public interface WeddingRepository extends CrudRepository<Wedding,Integer>{
    Wedding findOneByName (String name);

}
