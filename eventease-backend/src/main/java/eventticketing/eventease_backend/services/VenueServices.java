// services/VenueService.java
package eventticketing.eventease_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import eventticketing.eventease_backend.models.Venue;
import eventticketing.eventease_backend.repositries.VenueRepository;
import eventticketing.eventease_backend.dto.CreateVenueDTO;

@Service
public class VenueServices {

    @Autowired
    private VenueRepository venueRepository;

    public Venue createVenue(CreateVenueDTO dto) {
    Venue venue = Venue.builder()
            .name(dto.getName())
            .rows(dto.getRows())
            .columns(dto.getColumns())
            .seatMapJson(dto.getSeatMapJson()) // this is now a Map
            .build();
    // Builder used here for Avoids errors with argument order , Supports optional fields
    return venueRepository.save(venue);
}

}
