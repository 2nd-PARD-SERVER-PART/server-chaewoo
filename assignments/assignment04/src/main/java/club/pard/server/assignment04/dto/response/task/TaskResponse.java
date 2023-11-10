package club.pard.server.assignment04.dto.response.task;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import club.pard.server.assignment04.entity.task.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {
    private long id;
    private String title;
    private String content;
    private boolean isDone;

    private Timestamp timeStarting;
    private Timestamp timeEnding;

    private Timestamp timeCreated;
    private Timestamp timeUpdated;

    private Long taskIdHigher;
    private List<Long> taskIdsLower = new ArrayList<>();

    public TaskResponse(Task task)
    {
        this.id = task.getId();
        this.title = task.getTitle();
        this.content = task.getContent();
        this.isDone = task.isDone();
        this.timeStarting = task.getTimeStarting();
        this.timeEnding = task.getTimeEnding();
        this.timeCreated = task.getTimeCreated();
        this.timeUpdated = task.getTimeUpdated();

        Task taskHigher = task.getTaskHigher();
        this.taskIdHigher = (taskHigher != null) ? taskHigher.getId() : null;

        for(Task taskLower : task.getTasksLower())
            taskIdsLower.add(taskLower.getId());
    }
}
