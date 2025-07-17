package eventticketing.eventease_backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Each ticket belongs to one event
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id")
    private Event event;

    // Each ticket is for one attendee
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attendee_id")
    private User attendee;

    private LocalDateTime issuedAt;

    private String qrCode; // Store encoded data or token

    private boolean isScanned; // Mark attendance when QR is scanned
}
