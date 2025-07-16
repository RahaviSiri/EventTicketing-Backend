package eventticketing.eventease_backend.models;

import java.util.Map;

import eventticketing.eventease_backend.converters.MapToJsonConverter;
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

    @Convert(converter = MapToJsonConverter.class)
    // The Map<String, Object> field seatMapJson in the Venue entity cannot be stored directly in a SQL database.
    @Column(name = "seat_map_json")
    private Map<String, Object> seatMapJson;
    // JSON structure of seat availability

    // @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    // private List<Event> events;
}