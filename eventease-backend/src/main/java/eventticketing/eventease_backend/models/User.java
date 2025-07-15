package eventticketing.eventease_backend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users") 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String password;
    private String role; // e.g., "ATTENDEE", "ORGANIZER"
}

