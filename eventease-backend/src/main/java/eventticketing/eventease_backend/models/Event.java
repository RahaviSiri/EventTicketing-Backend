package eventticketing.eventease_backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    private LocalDateTime dateTime;

    private BigDecimal ticketPrice;

    private Integer totalSeats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id")
    private Venue venue;

    @ManyToOne(fetch = FetchType.LAZY)
    // Don’t load the related entity (User) from the database until it is accessed for the first time in code.
    @JoinColumn(name = "organizer_id", referencedColumnName = "id")
    // In the database table for Event (let's say event), there will be a column called organizer_id.
    // That column will store the ID (primary key) of the User (organizer).
    private User organizer;
}
