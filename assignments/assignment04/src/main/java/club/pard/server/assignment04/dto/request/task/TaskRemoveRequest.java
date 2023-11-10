package club.pard.server.assignment04.dto.request.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRemoveRequest {
    private Long taskId;
    private Long userId;
}
