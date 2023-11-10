package club.pard.server.assignment05.entity.task;

import java.sql.Timestamp;
import java.util.List;

import java.util.ArrayList;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import club.pard.server.assignment05.entity.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Task {
    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id = null;
    @ManyToOne(fetch = FetchType.LAZY) private User user;
    
    @Getter @Column(nullable = false) private String title;
    @Getter @Column(nullable = false, columnDefinition = "TEXT") private String content;
    @Getter private boolean isDone;

    @CreationTimestamp private Timestamp timeCreated;
    @UpdateTimestamp private Timestamp timeUpdated;

    private Timestamp timeStarting;
    private Timestamp timeEnding;

    @ManyToOne(fetch = FetchType.LAZY) private Task taskHigher;
    @OneToMany(mappedBy = "taskHigher") private List<Task> tasksLower = new ArrayList<>();

    // // Customized constructors
    public Task(User user, String title, String content){ this.user = user; this.title = title; this.content = content; this.isDone = false; }
    public Task(User user, String title, String content, Timestamp timeStarting, Timestamp timeEnding)
    {
        this(user, title, content);
        this.timeStarting = timeStarting; this.timeEnding = timeEnding;
    }
    public Task(User user, String title, String content, Timestamp timeStarting, Timestamp timeEnding, Task taskHigher)
    {
        this(user, title, content, timeStarting, timeEnding);
        this.taskHigher = taskHigher;
        if(this.taskHigher != null) this.taskHigher.getTasksLower().add(this);
    }

    // // Customized setters
    public void update(String newTitle, String newContent, boolean newIsDone, Timestamp newTimeStarting, Timestamp newTimeEnding, Task newTaskHigher)
    {
        this.title = newTitle; this.content = newContent; this.isDone = newIsDone;
        this.timeStarting = newTimeStarting; this.timeEnding = newTimeEnding;

         // (old, new) / strats
        // {null, null} / No additional work
        // (A, A) / No additional work
        // {A, null) / Remove targetTask from A.tasksLower
        // (null, B) / Add targetTask from B.tasksLower
        // (A, B) / Remove targetTask from A.tasksLower and Add targetTAsk from B.tasksLower

        if(this.taskHigher != newTaskHigher)
        {
            if(this.taskHigher != null) this.taskHigher.getTasksLower().remove(this);
            if(newTaskHigher != null) newTaskHigher.getTasksLower().add(this);
            this.taskHigher = newTaskHigher;
        }
    }

    public void cleanUp() // Cleaning up hierachy before removing Task
    {
        if(!this.tasksLower.isEmpty())
        {
            for(Task task : this.tasksLower) task.taskHigher = null;
            this.tasksLower.clear();
        }
        if(this.taskHigher != null)
        {
            this.taskHigher.getTasksLower().remove(this);
            this.taskHigher = null;
        }
    }
}
