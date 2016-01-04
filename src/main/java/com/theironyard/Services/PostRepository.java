package com.theironyard.Services;

import com.theironyard.Entities.Post;
import com.theironyard.Entities.Wedding;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by macbookair on 12/9/15.
 */
public interface PostRepository extends CrudRepository <Post,Integer>{
    List<Post> findByWedding (Wedding wedding);
}
