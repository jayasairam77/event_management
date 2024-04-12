/*
 * You can use the following import statements
 *
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.eventmanagementsystem.repository;

import java.util.*;
import com.example.eventmanagementsystem.model.Event;

public interface EventRepository {
    void deleteEvent(int eventId);

    Event updateEvent(int eventId, Event eobj);

    Event getEventById(int eventId);

    Event addEvent(Event eobj);

    List<Event> getEvents();
}