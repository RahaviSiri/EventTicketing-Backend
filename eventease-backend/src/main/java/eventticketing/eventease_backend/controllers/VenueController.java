// controllers/VenueController.java
package eventticketing.eventease_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import eventticketing.eventease_backend.dto.CreateVenueDTO;
import eventticketing.eventease_backend.models.Venue;
import eventticketing.eventease_backend.services.VenueServices;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/organizers/venues")
public class VenueController {

    @Autowired
    private VenueServices venueServices;

    @PostMapping("/create")
    public ResponseEntity<Venue> createVenue(HttpServletRequest request,@RequestBody CreateVenueDTO dto) {
        Venue createdVenue = venueServices.createVenue(dto);
        return ResponseEntity.ok(createdVenue);
    }
}
