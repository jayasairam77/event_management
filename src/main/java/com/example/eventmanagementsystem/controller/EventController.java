/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * 
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.eventmanagementsystem.controller;

import com.example.eventmanagementsystem.service.EventJpaService;
import com.example.eventmanagementsystem.model.Event;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

@RestController
public class EventController {
    @Autowired
    public EventJpaService eventJpaService;

    @DeleteMapping("/events/{eventId}")
    public void deleteEvent(@PathVariable("eventId") int eventId) {
        eventJpaService.deleteEvent(eventId);
    }

    @PutMapping("/events/{eventId}")
    public Event updateEvent(@PathVariable("eventId") int eventId, @RequestBody Event eobj) {
        return eventJpaService.updateEvent(eventId, eobj);
    }

    @GetMapping("/events/{eventId}")
    public Event getEventyId(@PathVariable("eventId") int eventId) {
        return eventJpaService.getEventById(eventId);
    }

    @GetMapping("/events")
    public List<Event> getEvents() {
        return eventJpaService.getEvents();
    }

    @PostMapping("/events")
    public Event addEvent(@RequestBody Event eobj) {
        return eventJpaService.addEvent(eobj);
    }

}