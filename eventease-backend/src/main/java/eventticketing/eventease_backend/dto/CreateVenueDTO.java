// dto/CreateVenueDTO.java
package eventticketing.eventease_backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateVenueDTO {
    private String name;
    private Integer rows;
    private Integer columns;
    private String seatMapJson; // JSON string sent from frontend
}
