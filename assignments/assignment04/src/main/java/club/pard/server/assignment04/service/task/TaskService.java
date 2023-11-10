package club.pard.server.assignment04.service.task;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import club.pard.server.assignment04.dto.request.task.TaskAddRequest;
import club.pard.server.assignment04.dto.request.task.TaskRemoveRequest;
import club.pard.server.assignment04.dto.request.task.TaskRetriveRequest;
import club.pard.server.assignment04.dto.request.task.TaskUpdateRequest;
import club.pard.server.assignment04.dto.response.Response;
import club.pard.server.assignment04.dto.response.task.TaskResponse;
import club.pard.server.assignment04.dto.response.task.TaskSimplifiiedResponse;
import club.pard.server.assignment04.entity.task.Task;
import club.pard.server.assignment04.entity.user.User;
import club.pard.server.assignment04.repository.task.TaskRepository;
import club.pard.server.assignment04.repository.user.UserRepository;

@Service
public class TaskService {
    private final TaskRepository taskRepository; // Why not make those per user?
    private final UserRepository userRepository;
    @Autowired public TaskService(TaskRepository taskRepository, UserRepository userRepository){ this.taskRepository = taskRepository; this.userRepository = userRepository; }

    //////////
    // Create

    @Transactional public Response<TaskResponse> addTask(TaskAddRequest request)
    {
        // // Retrieving fields from request
        Long userId = request.getUserId();
        Long taskIdHigher = request.getTaskIdHigher();

        // // Creating empty instances before instanciating Task
        User user = userRepository.findById(userId).orElse(null);
        Task taskHigher = null;

        try
        {
            // // Existence checks
            if(user == null) throw new NoSuchElementException("User not existent");
            if(taskIdHigher != null) taskHigher = taskRepository.findById(taskIdHigher).orElseThrow(() -> new NoSuchElementException("Higher-rank Task not existent"));

            Task newTask = new Task(user, request.getTitle(), request.getContent(), request.getTimeStarting(), request.getTimeEnding(), taskHigher);
            
            taskRepository.save(newTask);

            TaskResponse response = new TaskResponse(newTask);
            return Response.setSuccess("Successfully added task", response);
        }
        catch(NoSuchElementException e)
        {
            e.printStackTrace();
            return Response.setFailure(e.getMessage());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return Response.setFailure("Internal DB Error");
        }
    }

    //////////
    // Read

    @Transactional(readOnly = true) public Response<List<TaskSimplifiiedResponse>> listTasks(Long userId)
    {
        List<TaskSimplifiiedResponse> tasksSimplified = new ArrayList<>();
        
        if(userRepository.findById(userId) == null) return Response.setFailure("User not existent");
        else
        {
            for(Task task: taskRepository.findAllByUserId(userId).get())
                tasksSimplified.add(new TaskSimplifiiedResponse(task));
            return Response.setSuccess("Retrieved tasks from user", tasksSimplified);
        }
    }

    @Transactional(readOnly = true) public Response<TaskResponse> listTask(TaskRetriveRequest request)
    {
        Long taskId = request.getTaskId();
        Long userId = request.getUserId();

        Task targetTask = taskRepository.findById(taskId).orElse(null);

        if(userId == null || !userRepository.existsById(userId)) return Response.setFailure("User not existent");
        if(targetTask == null) return Response.setFailure("Task not existent");
        if(userId != targetTask.getUser().getId()) return Response.setFailure("User is not the owner of the task");

        TaskResponse response = new TaskResponse(targetTask);
        return Response.setSuccess("Task retrieved", response);
    }

    //////////
    // Update

    @Transactional public Response<TaskResponse> updateTask(TaskUpdateRequest request)
    {
        Long targetTaskId = request.getTargetTaskId();
        Long userId = request.getUserId();

        Task targetTask = taskRepository.findById(targetTaskId).orElse(null);

        // // Validity check
        if(userId == null || !userRepository.existsById(userId)) return Response.setFailure("User is null or not existent");
        if(targetTask == null) return Response.setFailure("Task not existent");
        if(userId != targetTask.getUser().getId()) return Response.setFailure("User is not the owner of the task");

        
        // Retrieving fields from request
        Long newTaskIdHigher = request.getTaskIdHigher();

        // Creating empty instances before instanciating Task
        Task newTaskHigher = null;

        try
        {
            if(newTaskIdHigher != null) newTaskHigher = taskRepository.findById(newTaskIdHigher).orElseThrow(() -> new NoSuchElementException("Higher-rank Task not existent"));

            targetTask.update(request.getTitle(), request.getContent(), request.getIsDone(), request.getTimeStarting(), request.getTimeEnding(), newTaskHigher);
            return Response.setSuccess("Successfully updated task", new TaskResponse(targetTask));
        }
        catch(NoSuchElementException e)
        {
            e.printStackTrace();
            return Response.setFailure(e.getMessage());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return Response.setFailure("Internal DB Error");
        }
    }

    //////////
    // Delete

    @Transactional public Response<?> removeTask(TaskRemoveRequest request)
    {
        Long taskId = request.getTaskId();
        Long userId = request.getUserId();

        Task targetTask = taskRepository.findById(taskId).orElse(null);

        if(userId == null || !userRepository.existsById(userId)) return Response.setFailure("User not existent");
        if(targetTask == null) return Response.setFailure("Task not existent");
        if(userId != targetTask.getUser().getId()) return Response.setFailure("user is not the owner of the task");

        targetTask.cleanUp();

        taskRepository.delete(targetTask);
        return Response.setSuccess("Successfully removed task", null);
    }
}

