package com.theironyard.Services;

import com.theironyard.Entities.Post;
import com.theironyard.Entities.Wedding;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by macbookair on 12/9/15.
 */
public interface PostRepository extends CrudRepository <Post,Integer>{
    Post findOneByWedding (Wedding wedding);
}
