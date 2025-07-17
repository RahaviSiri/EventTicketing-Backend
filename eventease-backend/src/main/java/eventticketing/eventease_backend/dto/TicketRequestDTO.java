package eventticketing.eventease_backend.dto;

import lombok.Data;

@Data
public class TicketRequestDTO {
    private Long eventId;
    private Long attendeeId;
}

