package eventticketing.eventease_backend.repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import eventticketing.eventease_backend.models.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByOrganizer_Id(Long userId);
}

