package club.pard.server.assignment04.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    private Long id;
    private String email;
    private String newPassword;
    private String newName;
}
