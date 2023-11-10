package club.pard.server.assignment05.service.user;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import club.pard.server.assignment05.dto.request.user.UserUpdateRequest;
import club.pard.server.assignment05.dto.response.Response;
import club.pard.server.assignment05.dto.response.user.UserResponse;
import club.pard.server.assignment05.dto.response.user.UserSimplifiedResponse;
import club.pard.server.assignment05.entity.user.User;
import club.pard.server.assignment05.repository.user.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired public UserService(UserRepository userRepository){ this.userRepository = userRepository; }

    //////////
    // Create

    // // Commented out since this will be handled in AuthService.
    // @Transactional public Response<UserResponse> addUser(UserSignUpRequest request)
    // {
    //     String newEmail = request.getEmail();
    //     String newPassword = request.getPassword();
    //     String newName = request.getName();

    //     try
    //     {
    //         if(newEmail == null || newEmail.equals("")) throw new IllegalArgumentException("Email should not be empty");
    //         if(userRepository.existsByEmail(newEmail)) throw new IllegalArgumentException("User already existent with given email");

    //         if(newPassword == null || newEmail.isEmpty()) throw new IllegalArgumentException("Password should not be empty");
    //         if(newName == null || newName.isEmpty()) throw new IllegalArgumentException("Name should not be empty");

    //         User newUser = new User(newEmail, newPassword, newName);
    //         userRepository.save(newUser);

    //         UserResponse response = new UserResponse(newUser);
    //         return Response.setSuccess("Successfully created user", response);
    //     }
    //     catch(IllegalArgumentException e)
    //     {
    //         e.printStackTrace();
    //         return Response.setFailure(e.getMessage());
    //     }
    //     catch(Exception e)
    //     {
    //         e.printStackTrace();
    //         return Response.setFailure("Internal DB Error");
    //     }
    // }

    //////////
    // Read

    @Transactional(readOnly = true) public Response<List<UserSimplifiedResponse>> getUsers()
    {
        try
        {
            return Response.setSuccess("Retrieved list of users", (userRepository.findAll().stream().map(UserSimplifiedResponse::new).collect(Collectors.toList())));
        }
        catch(Exception e)
        {
            return Response.setFailure("Internal DB/Java error");
        }
    }

    @Transactional(readOnly = true) public Response<UserResponse> getUser(Long userId)
    {
        try
        {
            User targetUser = userRepository.findById(userId).orElse(null);
            if(targetUser == null) throw new NoSuchElementException("User not existent");
            
            UserResponse response = new UserResponse(targetUser);
            return Response.setSuccess("Successfully retrieved user", response);
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
    // Update

    @Transactional public Response<UserSimplifiedResponse> updateUser(UserUpdateRequest request)
    {
        try
        {
            User targetUser = userRepository.findById(request.getId()).orElse(null);
            if(targetUser == null) throw new NoSuchElementException("User non-existent");

            targetUser.update(request.getNewPassword(), request.getNewName());

            UserSimplifiedResponse response = new UserSimplifiedResponse(targetUser);
            return Response.setSuccess("Successfully updated user", response);
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

    @Transactional public Response<?> removeUser(Long userId)
    {
        User targetUser = userRepository.findById(userId).orElse(null);

        if(targetUser == null) return Response.setFailure("User not existent");

        userRepository.delete(targetUser);
        return Response.setSuccess("Successfully removed user", null);
    }
}
