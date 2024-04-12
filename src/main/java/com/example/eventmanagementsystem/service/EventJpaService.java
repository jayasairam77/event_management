/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * 
 * import java.util.*;
 *
 */

// Write your code here
package com.example.eventmanagementsystem.service;

import com.example.eventmanagementsystem.model.Event;
import com.example.eventmanagementsystem.model.Sponsor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.example.eventmanagementsystem.repository.EventRepository;
import com.example.eventmanagementsystem.repository.EventJpaRepository;
import com.example.eventmanagementsystem.repository.SponsorJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EventJpaService implements EventRepository {
    @Autowired
    private EventJpaRepository eventJpaRepository;
    @Autowired
    private SponsorJpaRepository sponsorJpaRepository;

    @Override
    public void deleteEvent(int eventId) {
        try {
            Event givenEvent = eventJpaRepository.findById(eventId).get();
            List<Sponsor> sponsors = sponsorJpaRepository.findAll();
            for (Sponsor sponsor : sponsors) {
                sponsor.getEvents().remove(givenEvent);
            }
            sponsorJpaRepository.saveAll(sponsors);
            eventJpaRepository.deleteById(eventId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public Event updateEvent(int eventId, Event eobj) {
        try {
            Event cevent = eventJpaRepository.findById(eventId).get();
            if (eobj.getEventName() != null)
                cevent.setEventName(eobj.getEventName());
            if (eobj.getDate() != null)
                cevent.setDate(eobj.getDate());
            if (eobj.getSponsors() != null) {
                List<Sponsor> givenSponsors = eobj.getSponsors();
                List<Integer> spids = new ArrayList<>();
                for (Sponsor sp : givenSponsors) {
                    spids.add(sp.getSponsorId());
                }
                List<Sponsor> sponsors = new ArrayList<>();
                for (int x : spids) {
                    sponsors.add(sponsorJpaRepository.findById(x).get());
                }
                cevent.setSponsors(sponsors);
                eventJpaRepository.save(cevent);
                return cevent;
            }
            return cevent;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Event getEventById(int eventId) {
        try {
            return eventJpaRepository.findById(eventId).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Event> getEvents() {
        List<Event> eventslist = eventJpaRepository.findAll();
        return eventslist;
    }

    @Override
    public Event addEvent(Event eobj) {
        List<Integer> sponsorIds = new ArrayList<>();
        List<Sponsor> sponsors = new ArrayList<>();
        for (Sponsor tempobj : eobj.getSponsors()) {
            sponsorIds.add(tempobj.getSponsorId());
        }
        for (int id : sponsorIds) {
            sponsors.add(sponsorJpaRepository.findById(id).get());
        }
        eobj.setSponsors(sponsors);
        eventJpaRepository.save(eobj);
        return eobj;
    }

}