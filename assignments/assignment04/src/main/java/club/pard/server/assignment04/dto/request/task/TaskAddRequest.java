package club.pard.server.assignment04.dto.request.task;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskAddRequest {
    private Long userId;
    
    private String title;
    private String content;

    private Timestamp timeStarting;
    private Timestamp timeEnding;

    private Long taskIdHigher;
    // private List<Long> taskIdsLower;
}
