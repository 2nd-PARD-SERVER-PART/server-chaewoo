package club.pard.server.assignment04.dto.response.user;

import club.pard.server.assignment04.entity.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSimplifiedResponse {
    @Schema(description = "User ID") private Long id;
    @Schema(description = "User Email address") private String email;

    public UserSimplifiedResponse(User user)
    {
        this.id = user.getId();
        this.email = user.getEmail();
    }
}
