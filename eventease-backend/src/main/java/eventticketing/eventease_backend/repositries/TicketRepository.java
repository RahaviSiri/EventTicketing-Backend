package eventticketing.eventease_backend.repositries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eventticketing.eventease_backend.models.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByEventId(Long eventId);
    boolean existsByEventIdAndAttendeeId(Long eventId, Long attendeeId);
}

