package eventticketing.eventease_backend.dto;

public class UpdateEventVenueDTO {
    private Long eventId;
    private Long venueId;
    public Long getEventId() {
        return eventId;
    }
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
    public Long getVenueId() {
        return venueId;
    }
    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }
}
