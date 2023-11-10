package club.pard.server.assignment05.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.pard.server.assignment05.dto.request.user.UserUpdateRequest;
import club.pard.server.assignment05.dto.response.Response;
import club.pard.server.assignment05.dto.response.user.UserResponse;
import club.pard.server.assignment05.dto.response.user.UserSimplifiedResponse;
import club.pard.server.assignment05.filter.JwtAuthenticationFilter;
import club.pard.server.assignment05.security.TokenProvider;
import club.pard.server.assignment05.service.user.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserService userService;

    private final int TOKEN_STARTING_INDEX = 7;

    @Autowired public UserController(TokenProvider tokenProvider, JwtAuthenticationFilter jwtAuthenticationFilter, UserService userService)
    {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userService = userService;
    }

    //////////
    // Create

    // // Commented out since this will be handled in AuthController.
    // @PostMapping Response<UserResponse> add(@RequestBody UserSignUpRequest request){ return userService.addUser(request); }

    //////////
    // Read
    
    @GetMapping("/all") Response<List<UserSimplifiedResponse>> getUsers(@RequestHeader(value = "Authorization") String token)
    {
        String userId = tokenProvider.validate(token.substring(TOKEN_STARTING_INDEX));
        
        if(userId == null) return Response.setFailure("Authorization failed");
        else return userService.getUsers();
    }
    @GetMapping("/{userId}") Response<UserResponse> getUser(@RequestHeader(value = "Authorization") String token, @PathVariable Long userId)
    {
        String decodedUserId = tokenProvider.validate(token.substring(TOKEN_STARTING_INDEX));
        String requestUserId = Long.toString(userId);

        if(decodedUserId == null) return Response.setFailure("Authorization failed");
        // if(!decodedUserId.equals(requestUserId)) return Response.setFailure("Authorization failed");
        return userService.getUser(userId);
    }

    //////////
    // Update
    
    @PatchMapping Response<UserSimplifiedResponse> updateUser(@RequestHeader(value = "Authorization") String token, @RequestBody UserUpdateRequest request)
    {
        String decodedUserId = tokenProvider.validate(token.substring(TOKEN_STARTING_INDEX));
        String requestUserId = Long.toString(request.getId());

        if(decodedUserId == null) return Response.setFailure("Authorization failed");
        // if(!decodedUserId.equals(requestUserId)) return Response.setFailure("Authorization failed");
        return userService.updateUser(request);
    }

    //////////
    // Delete
    
    // // NOT Commented out since this should be run by user itself.
    @DeleteMapping Response<?> removeUser(@RequestHeader(value = "Authorization") String token, @RequestBody Long userId)
    {
        String decodedUserId = tokenProvider.validate(token.substring(TOKEN_STARTING_INDEX));
        String requestUserId = Long.toString(userId);

        if(decodedUserId == null) return Response.setFailure("Authorization failed");
        // if(!decodedUserId.equals(requestUserId)) return Response.setFailure("Authorization failed");
        return userService.removeUser(userId);
    }
}
