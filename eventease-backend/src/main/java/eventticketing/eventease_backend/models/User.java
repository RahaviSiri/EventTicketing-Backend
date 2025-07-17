package eventticketing.eventease_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import eventticketing.eventease_backend.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users") 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // e.g., "ATTENDEE", "ORGANIZER", "BOTH"
}

