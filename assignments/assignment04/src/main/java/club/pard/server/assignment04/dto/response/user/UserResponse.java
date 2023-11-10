package club.pard.server.assignment04.dto.response.user;

import java.util.ArrayList;
import java.util.List;

import club.pard.server.assignment04.entity.task.Task;
import club.pard.server.assignment04.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String name;
    private List<Long> tasks = new ArrayList<>();

    public UserResponse(User user)
    {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();

        for(Task task : user.getTasks()) tasks.add(task.getId());
    }
}
