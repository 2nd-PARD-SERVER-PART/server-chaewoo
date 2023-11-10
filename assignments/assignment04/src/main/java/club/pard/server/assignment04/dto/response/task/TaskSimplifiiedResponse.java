package club.pard.server.assignment04.dto.response.task;

import java.sql.Timestamp;

import club.pard.server.assignment04.entity.task.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskSimplifiiedResponse {
    private long id;
    private String title;
    private String content;
    private boolean isDone;

    private Timestamp timeCreated;
    private Timestamp timeUpdated;

    private Timestamp timeStarting;
    private Timestamp timeEnding;

    public TaskSimplifiiedResponse(Task task)
    {
        this.id = task.getId();
        this.title = task.getTitle();
        this.content = task.getContent();
        this.isDone = task.isDone();
        this.timeStarting = task.getTimeStarting();
        this.timeEnding = task.getTimeEnding();
        this.timeCreated = task.getTimeCreated();
        this.timeUpdated = task.getTimeUpdated();
    }
}
