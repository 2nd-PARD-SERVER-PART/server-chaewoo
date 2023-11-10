package club.pard.server.assignment04.dto.request.task;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpdateRequest {
    private Long targetTaskId;
    private Long userId; // Only used for validity check
    // userId non-existent since the *same* user will change the task, not changing the owner of it.
    
    private String title;
    private String content;
    private Boolean isDone;

    private Timestamp timeStarting;
    private Timestamp timeEnding;

    private Long taskIdHigher;
    // private List<Long> taskIdsLower;
}
