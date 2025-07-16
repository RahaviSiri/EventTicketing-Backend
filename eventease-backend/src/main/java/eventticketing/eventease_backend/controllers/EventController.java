package eventticketing.eventease_backend.controllers;

import org.springframework.web.bind.annotation.RestController;

import eventticketing.eventease_backend.dto.CreateEventDTO;
import eventticketing.eventease_backend.dto.UpdateEventVenueDTO;
import eventticketing.eventease_backend.models.Event;
import eventticketing.eventease_backend.services.EventServices;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/organizers/event")
public class EventController {

    @Autowired
    EventServices eventServices;

    @GetMapping("/getEvent")
    public ResponseEntity<List<Event>> getEventByUserId(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<Event> events = eventServices.getEventUserById(userId);
        return new ResponseEntity<>(events,HttpStatus.OK);
    }

    @PostMapping("/addEvent")
    public ResponseEntity<Event> addEvent(HttpServletRequest request,@RequestBody CreateEventDTO dto) throws Throwable {
        Long userId = (Long) request.getAttribute("userId");
        Event newEvent = eventServices.addEvent(dto, userId);
        return new ResponseEntity<>(newEvent, HttpStatus.CREATED);
    }

    @PostMapping("/assignVenue")
    public ResponseEntity<String> assignVenueToEvent(@RequestBody UpdateEventVenueDTO dto) {
        eventServices.assignVenue(dto.getEventId(), dto.getVenueId());
        return ResponseEntity.ok("Venue assigned successfully");
    }

    
}
