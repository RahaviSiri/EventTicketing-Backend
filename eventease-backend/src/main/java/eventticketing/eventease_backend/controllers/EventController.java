package eventticketing.eventease_backend.controllers;

import org.springframework.web.bind.annotation.RestController;

import eventticketing.eventease_backend.models.Event;
import eventticketing.eventease_backend.services.EventSevices;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/organizers")
public class EventController {

    @Autowired
    EventSevices eventSevices;

    @GetMapping("/getEvent")
    public ResponseEntity<List<Event>> getEventByUserId(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<Event> events = eventSevices.getEventUserById(userId);
        return new ResponseEntity<>(events,HttpStatus.OK);
    }
    
}
