package club.pard.server.assignment04.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.pard.server.assignment04.dto.request.user.UserSignUpRequest;
import club.pard.server.assignment04.dto.request.user.UserUpdateRequest;
import club.pard.server.assignment04.dto.response.Response;
import club.pard.server.assignment04.dto.response.user.UserResponse;
import club.pard.server.assignment04.dto.response.user.UserSimplifiedResponse;
import club.pard.server.assignment04.service.user.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    @Autowired public UserController(UserService userService){ this.userService = userService; }    

    //////////
    // Create

    @PostMapping Response<UserResponse> add(@RequestBody UserSignUpRequest request){ return userService.addUser(request); }

    //////////
    // Read
    
    @GetMapping("/all") List<UserSimplifiedResponse> getUsers(){ return userService.getUsers(); }
    @GetMapping("/{userId}") Response<UserResponse> getUser(Long userId){ return userService.getUser(userId); }

    //////////
    // Update
    
    @PatchMapping Response<UserSimplifiedResponse> updateUser(@RequestBody UserUpdateRequest request){ return userService.updateUser(request); }

    //////////
    // Delete
    
    @DeleteMapping Response<?> removeUser(@RequestBody Long userId){ return userService.removeUser(userId); }
}
