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

import com.example.eventmanagementsystem.model.Sponsor;
import com.example.eventmanagementsystem.model.Event;
import com.example.eventmanagementsystem.service.SponsorJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class SponsorController {

    @Autowired
    public SponsorJpaService sponsorJpaService;

    @GetMapping("/sponsors/{sponsorId}/events")
    public List<Event> getSponsorEvents(@PathVariable("sponsorId") int sponsorId) {
        return sponsorJpaService.getSponsorEvents(sponsorId);
    }

    @DeleteMapping("/events/sponsors/{sponsorId}")
    public void deleteSponsor(@PathVariable("sponsorId") int sponsorId) {
        sponsorJpaService.deleteSponsor(sponsorId);
    }

    @PutMapping("/events/sponsors/{sponsorId}")
    public Sponsor updateSponsorById(@PathVariable("sponsorId") int sponsorId, @RequestBody Sponsor sobj) {
        return sponsorJpaService.updateSponsor(sponsorId, sobj);
    }

    @GetMapping("/events/sponsors/{sponsorId}")
    public Sponsor getSponsorById(@PathVariable("sponsorId") int sponsorId) {
        return sponsorJpaService.getSponsorById(sponsorId);
    }

    @PostMapping("/events/sponsors")
    public Sponsor addSponsors(@RequestBody Sponsor sponsorobj) {
        return sponsorJpaService.addSponsor(sponsorobj);
    }

    @GetMapping("/events/sponsors")
    public List<Sponsor> getSponsors() {
        return sponsorJpaService.getSponsors();
    }

    @GetMapping("/events/{eventId}/sponsors")
    public List<Sponsor> getSponsorsOfEventId(@PathVariable("eventId") int eventId) {
        return sponsorJpaService.getSponsorsOfEventId(eventId);
    }

}