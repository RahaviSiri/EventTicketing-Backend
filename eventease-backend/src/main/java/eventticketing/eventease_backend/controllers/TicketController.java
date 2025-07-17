package eventticketing.eventease_backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eventticketing.eventease_backend.dto.TicketRequestDTO;
import eventticketing.eventease_backend.services.TicketServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    TicketServices ticketServices;

    @PostMapping("/addTicket")
    public ResponseEntity<String> addTicket(@RequestBody TicketRequestDTO dto) {
        ticketServices.addTicket(dto);
        return ResponseEntity.ok("Ticket successfully added for user ID: " + dto.getAttendeeId());
    }
}



