package club.pard.server.assignment04.controller.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.pard.server.assignment04.dto.request.task.TaskAddRequest;
import club.pard.server.assignment04.dto.request.task.TaskRemoveRequest;
import club.pard.server.assignment04.dto.request.task.TaskRetriveRequest;
import club.pard.server.assignment04.dto.request.task.TaskUpdateRequest;
import club.pard.server.assignment04.dto.response.Response;
import club.pard.server.assignment04.dto.response.task.TaskResponse;
import club.pard.server.assignment04.dto.response.task.TaskSimplifiiedResponse;
import club.pard.server.assignment04.service.task.TaskService;

// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;

// @Tag(name = "task", description = "Tasks API")
@RestController
@RequestMapping("/api/task")
public class TaskController {
    private final TaskService taskService;
    @Autowired public TaskController(TaskService taskService){ this.taskService = taskService; }

    //////////
    // Create

    @PostMapping Response<TaskResponse> add(@RequestBody TaskAddRequest request){ return taskService.addTask(request); }

    //////////
    // Read

    @GetMapping("/all/{userId}") Response<List<TaskSimplifiiedResponse>> listTasks(@PathVariable Long userId){ return taskService.listTasks(userId); }
    @GetMapping Response<TaskResponse> listTask(@RequestBody TaskRetriveRequest request){ return taskService.listTask(request); }

    //////////
    // Update

    @PatchMapping Response<TaskResponse> updateTask(@RequestBody TaskUpdateRequest request){ return taskService.updateTask(request); }

    //////////
    // Delete

    @DeleteMapping Response<?> removeTask(@RequestBody TaskRemoveRequest request){ return taskService.removeTask(request); }
}
