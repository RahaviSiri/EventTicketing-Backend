package eventticketing.eventease_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeRoleRequestDTO {
    private Long userId;
    private String newRole;
}
