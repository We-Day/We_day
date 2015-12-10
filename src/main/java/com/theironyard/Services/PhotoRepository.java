package com.theironyard.Services;

import com.theironyard.Entities.Photo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by benjamindrake on 12/9/15.
 */
public interface PhotoRepository extends CrudRepository <Photo, Integer> {
}
