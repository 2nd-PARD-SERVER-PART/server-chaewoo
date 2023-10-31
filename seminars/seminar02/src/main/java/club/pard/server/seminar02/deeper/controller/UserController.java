package club.pard.server.seminar02.deeper.controller;

import club.pard.server.seminar02.deeper.repository.UserRepository;
import club.pard.server.seminar02.deeper.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    @Autowired

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping("/add")
    public String userAdd(@RequestParam Integer stuNum, @RequestParam String stuName) {
        User user = new User();
        user.setStuNum(stuNum);
        user.setStuName(stuName);

        userRepository.save(user);

        return "Version 1";
    }

    @PostMapping("/add/{stuNum}/{stuName}")
    public String userAddByQuery(@PathVariable Integer stuNum, @PathVariable String stuName) {
        User user = new User();
        user.setStuNum(stuNum);
        user.setStuName(stuName);

        userRepository.save(user);

        return "Version 2";
    }

    @PostMapping("/add/body")
    public String userAdd(@RequestBody User user) {
        userRepository.save(user);
        return "Version 3";
    }

    @GetMapping("/findAll")
    public List<User> userFind() {
        List<User> students = userRepository.findAll();
        return students;
    }

    @GetMapping("/find/{stuNum}")
    public User userFind(@PathVariable Integer stuNum) {
        User user = userRepository.findById(stuNum);
        return user;
    }

    @PatchMapping("/update/{stuNum}")
    public String userUpdate(@PathVariable Integer stuNum, @RequestBody User user) {
        userRepository.update(stuNum, user);
        return "Update Completed";
    }

    @DeleteMapping("/delete/{stuNum}")
    public String userDelete(@PathVariable Integer stuNum) {
        userRepository.delete(stuNum);
        return "Delete Completed";
    }
}
