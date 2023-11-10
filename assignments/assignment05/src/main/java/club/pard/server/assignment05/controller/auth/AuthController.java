package club.pard.server.assignment05.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.pard.server.assignment05.dto.request.user.UserSignInRequest;
import club.pard.server.assignment05.dto.request.user.UserSignUpRequest;
import club.pard.server.assignment05.dto.response.Response;
import club.pard.server.assignment05.dto.response.user.UserSignInResponse;
import club.pard.server.assignment05.service.auth.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;
    @Autowired public AuthController(AuthService authService){ this.authService = authService; }

    @PostMapping("/signUp")
    public Response<?> signUp(@RequestBody UserSignUpRequest request){ return  authService.signUp(request); }

    @PostMapping("/signIn")
    public Response<UserSignInResponse> signIn(@RequestBody UserSignInRequest request){ return authService.signIn(request); }
}
