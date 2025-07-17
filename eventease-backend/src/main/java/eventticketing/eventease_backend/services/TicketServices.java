package eventticketing.eventease_backend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eventticketing.eventease_backend.dto.TicketRequestDTO;
import eventticketing.eventease_backend.models.Event;
import eventticketing.eventease_backend.models.Ticket;
import eventticketing.eventease_backend.models.User;
import eventticketing.eventease_backend.repositries.EventRepository;
import eventticketing.eventease_backend.repositries.TicketRepository;
import eventticketing.eventease_backend.repositries.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketServices {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    public List<User> getAttendees(Long userId, Long eventId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (!event.getOrganizer().getId().equals(userId)) {
            throw new RuntimeException("Access denied");
        }

        List<Ticket> tickets = ticketRepository.findByEventId(eventId);
        List<User> attendees = tickets.stream()
                .map(Ticket::getAttendee)
                .distinct() // Optional: in case of multiple tickets per user
                .collect(Collectors.toList());

        return attendees;
    }

    public void addTicket(TicketRequestDTO dto) {
        if (ticketRepository.existsByEventIdAndAttendeeId(dto.getEventId(), dto.getAttendeeId())) {
            throw new RuntimeException("User already has a ticket for this event.");
        }
        Event event = eventRepository.findById(dto.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        User user = userRepository.findById(dto.getAttendeeId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Ticket ticket = Ticket.builder()
                .event(event)
                .attendee(user)
                .issuedAt(LocalDateTime.now())
                .qrCode(generateQRCode()) // generate a unique string or hash
                .isScanned(false)
                .build();

        ticketRepository.save(ticket);
    }

    private String generateQRCode() {
        return java.util.UUID.randomUUID().toString(); // simple QR code token
    }

}
