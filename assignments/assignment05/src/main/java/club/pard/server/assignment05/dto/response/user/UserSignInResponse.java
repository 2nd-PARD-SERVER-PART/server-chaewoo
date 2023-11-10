package club.pard.server.assignment05.dto.response.user;

import club.pard.server.assignment05.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignInResponse {
    private String token;
    private int expirationTime;
    private User user;
}
