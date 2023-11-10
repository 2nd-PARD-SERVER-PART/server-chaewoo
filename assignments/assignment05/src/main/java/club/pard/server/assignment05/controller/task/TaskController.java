package club.pard.server.assignment05.controller.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.pard.server.assignment05.dto.request.task.TaskAddRequest;
import club.pard.server.assignment05.dto.request.task.TaskRemoveRequest;
import club.pard.server.assignment05.dto.request.task.TaskRetriveRequest;
import club.pard.server.assignment05.dto.request.task.TaskUpdateRequest;
import club.pard.server.assignment05.dto.response.Response;
import club.pard.server.assignment05.dto.response.task.TaskResponse;
import club.pard.server.assignment05.dto.response.task.TaskSimplifiiedResponse;
import club.pard.server.assignment05.filter.JwtAuthenticationFilter;
import club.pard.server.assignment05.security.TokenProvider;
import club.pard.server.assignment05.service.task.TaskService;

// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;

// @Tag(name = "task", description = "Tasks API")
@RestController
@RequestMapping("/api/task")
public class TaskController {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final TaskService taskService;

    private final int TOKEN_STARTING_INDEX = 7;

    @Autowired public TaskController(TokenProvider tokenProvider, JwtAuthenticationFilter jwtAuthenticationFilter, TaskService taskService)
    {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.taskService = taskService;
    }

    //////////
    // Create

    @PostMapping Response<TaskResponse> add(@RequestHeader(value = "Authorization") String token, @RequestBody TaskAddRequest request)
    {
        String decodedUserId = tokenProvider.validate(token.substring(TOKEN_STARTING_INDEX));
        String requestUserId = Long.toString(request.getUserId());
    
        if(decodedUserId == null) return Response.setFailure("Authorization failed");
        // if(!decodedUserId.equals(requestUserId)) return Response.setFailure("Authorization failed");
        return taskService.addTask(request);
    }

    //////////
    // Read

    @GetMapping("/all/{userId}") Response<List<TaskSimplifiiedResponse>> listTasks(@RequestHeader(value = "Authorization") String token, @PathVariable Long userId)
    {
        String decodedUserId = tokenProvider.validate(token.substring(TOKEN_STARTING_INDEX));
        String requestUserId = Long.toString(userId);

        if(decodedUserId == null) return Response.setFailure("Authorization failed");
        // if(!decodedUserId.equals(requestUserId)) return Response.setFailure("Authorization failed");
        return taskService.listTasks(userId);
    }
    @GetMapping Response<TaskResponse> listTask(@RequestHeader(value = "Authorization") String token, @RequestBody TaskRetriveRequest request)
    {
        String decodedUserId = tokenProvider.validate(token.substring(TOKEN_STARTING_INDEX));
        String requestUserId = Long.toString(request.getUserId());

        if(decodedUserId == null) return Response.setFailure("Authorization failed");
        // if(!decodedUserId.equals(requestUserId)) return Response.setFailure("Authorization failed");
        return taskService.listTask(request);
    }

    //////////
    // Update

    @PatchMapping Response<TaskResponse> updateTask(@RequestHeader(value = "Authorization") String token, @RequestBody TaskUpdateRequest request)
    {
        String decodedUserId = tokenProvider.validate(token.substring(TOKEN_STARTING_INDEX));
        String requestUserId = Long.toString(request.getUserId());

        if(decodedUserId == null) return Response.setFailure("Authorization failed");
        // if(!decodedUserId.equals(requestUserId)) return Response.setFailure("Authorization failed");
        return taskService.updateTask(request);
    }

    //////////
    // Delete

    @DeleteMapping Response<?> removeTask(@RequestHeader(value = "Authorization") String token, @RequestBody TaskRemoveRequest request)
    {
        String decodedUserId = tokenProvider.validate(token.substring(TOKEN_STARTING_INDEX));
        String requestUserId = Long.toString(request.getUserId());

        if(decodedUserId == null) return Response.setFailure("Authorization failed");
        // if(!decodedUserId.equals(requestUserId)) return Response.setFailure("Authorization failed");
        return taskService.removeTask(request);
    }
}
