package club.pard.server.assignment05.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club.pard.server.assignment05.configuration.WebSecurityConfig;
import club.pard.server.assignment05.dto.request.user.UserSignInRequest;
import club.pard.server.assignment05.dto.request.user.UserSignUpRequest;
import club.pard.server.assignment05.dto.response.Response;
import club.pard.server.assignment05.dto.response.user.UserResponse;
import club.pard.server.assignment05.dto.response.user.UserSignInResponse;
import club.pard.server.assignment05.entity.user.User;
import club.pard.server.assignment05.repository.user.UserRepository;
import club.pard.server.assignment05.security.TokenProvider;

@Service
public class AuthService {
    private UserRepository userRepository;
    private TokenProvider tokenProvider;
    private WebSecurityConfig webSecurityConfig;

    @Autowired public AuthService(UserRepository userRepository, TokenProvider tokenProvider, WebSecurityConfig webSecurityConfig)
    {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.webSecurityConfig = webSecurityConfig;
    }

    public Response<?> signUp(UserSignUpRequest request)
    {
        String newEmail = request.getEmail();
        String newPassword = webSecurityConfig.getPasswordEncoder().encode(request.getPassword());
        String newName = request.getName();
        
        UserSignUpRequest encryptedRequest = new UserSignUpRequest(newEmail, newPassword, newName); // new DTO with encoded userPassword

        try
        {
            if(newEmail == null || newEmail.equals("")) throw new IllegalArgumentException("Email should not be empty");
            if(userRepository.existsByEmail(newEmail)) throw new IllegalArgumentException("User already existent with given email");

            if(newPassword == null || newEmail.isEmpty()) throw new IllegalArgumentException("Password should not be empty");
            if(newName == null || newName.isEmpty()) throw new IllegalArgumentException("Name should not be empty");

            User newUser = new User(encryptedRequest);
            userRepository.save(newUser);

            UserResponse response = new UserResponse(newUser);
            return Response.setSuccess("Sign-up success, successfully created user", response);
        }
        catch(IllegalArgumentException e)
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

    

    public Response<UserSignInResponse> signIn(UserSignInRequest request)
    {
        String userEmail = request.getEmail();
        User user = userRepository.findByEmail(userEmail).get();
        boolean passwordMatch = webSecurityConfig.getPasswordEncoder().matches(request.getPassword(), user.getPassword());
        boolean exists = userRepository.existsByEmail(userEmail) && passwordMatch;

        if(!exists) return Response.setFailure("Invalid email or password");

        user.setPassword(""); // Emptying hash value
        String token = tokenProvider.create(userEmail);
        int expirationTime = 60 * 60 * 24;

        UserSignInResponse response = new UserSignInResponse(token, expirationTime, user);
        return Response.setSuccess("Sign-in success", response);
    }
}
