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

import com.example.eventmanagementsystem.repository.SponsorRepository;
import com.example.eventmanagementsystem.model.Sponsor;
import com.example.eventmanagementsystem.model.Event;
import com.example.eventmanagementsystem.repository.SponsorJpaRepository;
import com.example.eventmanagementsystem.repository.EventJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class SponsorJpaService implements SponsorRepository {
    @Autowired
    private EventJpaRepository eventJpaRepository;
    @Autowired
    private SponsorJpaRepository sponsorJpaRepository;

    @Override
    public List<Event> getSponsorEvents(int sponsorId) {
        Sponsor sponsor = sponsorJpaRepository.findById(sponsorId).get();
        List<Event> events = sponsor.getEvents();
        return events;
    }

    @Override
    public void deleteSponsor(int sponsorId) {
        try {
            Sponsor sponsor = sponsorJpaRepository.findById(sponsorId).get();
            List<Event> events = sponsor.getEvents();
            for (Event event : events) {
                event.getSponsors().remove(sponsor);
            }
            eventJpaRepository.saveAll(events);
            sponsorJpaRepository.deleteById(sponsorId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Sponsor updateSponsor(int sponsorId, Sponsor sobj) {
        try {
            Sponsor sponsor = sponsorJpaRepository.findById(sponsorId).get();
            if (sobj.getSponsorName() != null)
                sponsor.setSponsorName(sobj.getSponsorName());
            if (sobj.getIndustry() != null)
                sponsor.setIndustry(sobj.getIndustry());
            if (sobj.getEvents() != null) {
                List<Integer> eids = new ArrayList<>();
                for (Event event : sobj.getEvents()) {
                    eids.add(event.getEventId());
                }
                List<Event> events = eventJpaRepository.findAllById(eids);
                if (eids.size() != events.size()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }
                List<Event> pevents = sponsor.getEvents();
                for (Event event : pevents) {
                    event.getSponsors().remove(sponsor);
                }
                sponsor.setEvents(events);
                for (Event event : events) {
                    event.getSponsors().add(sponsor);
                }
                eventJpaRepository.saveAll(events);
                sponsorJpaRepository.save(sponsor);
            }
            return sponsor;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public Sponsor getSponsorById(int sponsorId) {
        try {
            return sponsorJpaRepository.findById(sponsorId).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Sponsor addSponsor(Sponsor sponsorobj) {
        try {
            // List<Event> events = sponsorobj.getEvents();
            List<Integer> eventIds = new ArrayList<>();
            for (Event event : sponsorobj.getEvents()) {
                eventIds.add(event.getEventId());
            }
            List<Event> events = eventJpaRepository.findAllById(eventIds);
            if (eventIds.size() != events.size()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            sponsorobj.setEvents(events);
            for (Event event : events) {
                event.getSponsors().add(sponsorobj);
            }
            Sponsor sponsors = sponsorJpaRepository.save(sponsorobj);
            eventJpaRepository.saveAll(events);
            return sponsors;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Sponsor> getSponsors() {
        return sponsorJpaRepository.findAll();
    }

    @Override
    public List<Sponsor> getSponsorsOfEventId(int eventId) {
        try {
            Event givenEvent = eventJpaRepository.findById(eventId).get();
            List<Sponsor> fsponsors = sponsorJpaRepository.findByEvents(givenEvent);
            return fsponsors;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}