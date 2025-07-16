package eventticketing.eventease_backend.repositries;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import eventticketing.eventease_backend.models.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Integer> {
    Optional<Venue> findById(Long venueId);
}
