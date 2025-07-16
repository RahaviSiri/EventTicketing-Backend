package eventticketing.eventease_backend.models;

import jakarta.persistence.*;
import lombok.*;

// import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer rows;       // e.g., 10
    private Integer columns;    // e.g., 15

    @Column(columnDefinition = "jsonb")
    private String seatMapJson; // JSON structure of seat availability

    // @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    // private List<Event> events;
}