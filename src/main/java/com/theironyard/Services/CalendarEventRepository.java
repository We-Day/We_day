package com.theironyard.Services;

import com.theironyard.Entities.CalendarEvent;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by macbookair on 12/16/15.
 */
public interface CalendarEventRepository extends CrudRepository <CalendarEvent, Integer>{
}
