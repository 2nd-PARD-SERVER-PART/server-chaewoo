package club.pard.server.assignment04.dto.request.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRetriveRequest {
    private Long taskId;
    private Long userId;
}
