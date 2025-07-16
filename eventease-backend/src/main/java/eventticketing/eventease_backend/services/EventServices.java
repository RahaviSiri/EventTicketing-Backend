package eventticketing.eventease_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eventticketing.eventease_backend.dto.CreateEventDTO;
import eventticketing.eventease_backend.models.Event;
import eventticketing.eventease_backend.models.User;
import eventticketing.eventease_backend.models.Venue;
import eventticketing.eventease_backend.repositries.EventRepository;
import eventticketing.eventease_backend.repositries.UserRepository;
import eventticketing.eventease_backend.repositries.VenueRepository;

@Service
public class EventServices {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    VenueRepository venueRepository;

    public List<Event> getEventUserById(Long userId) {
        return eventRepository.findByOrganizer_Id(userId);
    }

    public Event addEvent(CreateEventDTO dto, Long userId) throws Throwable {
        User organizer = (User) userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found"));

        Event event = Event.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .dateTime(dto.getDateTime())
                .ticketPrice(dto.getTicketPrice())
                .totalSeats(dto.getTotalSeats())
                .organizer(organizer)
                .build();

        return eventRepository.save(event);
    }

    public void assignVenue(Long eventId, Long venueId) {
        Event event = eventRepository.findById(eventId)
                  .orElseThrow(() -> new RuntimeException("Event not found"));

        Venue venue = venueRepository.findById(venueId)
                    .orElseThrow(() -> new RuntimeException("Venue not found"));

        event.setVenue(venue);
        eventRepository.save(event);
    }

}
