package eventticketing.eventease_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eventticketing.eventease_backend.models.Event;
import eventticketing.eventease_backend.repositries.EventRepository;

@Service
public class EventSevices {

    @Autowired
    EventRepository eventRepository;

    public List<Event> getEventUserById(Long userId) {
        return eventRepository.findByOrganizer_Id(userId);
    }

}
