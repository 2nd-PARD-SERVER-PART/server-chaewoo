package club.pard.server.seminar02.basic.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mapping/users")
public class HttpMethodController {
    @GetMapping
    public String userAll(){ return "GET users"; }
    @PostMapping
    public String adduser(){ return "POST user"; }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId){
        return "GET user with userId = " + userId;
    }
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId) {
        return "PATCH user with userId = " + userId;
    }
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "DELETE user with userId = " + userId;
    }
}
