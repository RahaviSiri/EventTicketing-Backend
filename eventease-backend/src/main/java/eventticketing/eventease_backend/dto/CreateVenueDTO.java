package eventticketing.eventease_backend.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateVenueDTO {
    private String name;
    private Integer rows;
    private Integer columns;
    private Map<String, Object> seatMapJson;
}
