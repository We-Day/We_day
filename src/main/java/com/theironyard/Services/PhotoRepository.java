package com.theironyard.Services;

import com.theironyard.Entities.Photo;
import com.theironyard.Entities.Wedding;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by benjamindrake on 12/9/15.
 */
public interface PhotoRepository extends CrudRepository <Photo, Integer> {
     List<Photo> findByWedding(Wedding wedding);
}
