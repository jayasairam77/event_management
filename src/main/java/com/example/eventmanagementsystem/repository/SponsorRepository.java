/*
 * You can use the following import statements
 *
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.eventmanagementsystem.repository;

// import com.example.eventmanagementsystem.repository;
import com.example.eventmanagementsystem.model.Sponsor;
import com.example.eventmanagementsystem.model.Event;
import java.util.*;

public interface SponsorRepository {
    List<Event> getSponsorEvents(int sponsorId);

    void deleteSponsor(int sponsorId);

    Sponsor updateSponsor(int sponsorId, Sponsor sobj);

    Sponsor getSponsorById(int sponsorId);

    Sponsor addSponsor(Sponsor sponsorobj);

    List<Sponsor> getSponsors();

    List<Sponsor> getSponsorsOfEventId(int eventId);
}