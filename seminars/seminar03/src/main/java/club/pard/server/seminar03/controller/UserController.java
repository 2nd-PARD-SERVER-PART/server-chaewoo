package club.pard.server.seminar03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import club.pard.server.seminar03.dto.ResponseDto;
import club.pard.server.seminar03.dto.SignInDto;
import club.pard.server.seminar03.dto.SignUpDto;
import club.pard.server.seminar03.entity.UserEntity;
import club.pard.server.seminar03.service.UserService;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired public UserController(UserService userService){ this.userService = userService; }

    @PostMapping("/signUp")
    public ResponseDto<UserEntity> signUp(@RequestBody SignUpDto dto)
    {
            ResponseDto<UserEntity> result = userService.signUp(dto);
            return result;
    }

    @GetMapping("/findAll")
    public ResponseDto<List<UserEntity>> findAll()
    {
        ResponseDto<List<UserEntity>> result = userService.findAll();
        return result;
    }

    @GetMapping("/findOne/{id}")
    public ResponseDto<UserEntity> findOne(@PathVariable int id)
    {
        ResponseDto<UserEntity> result = userService.findOne(id);
        return result;
    }

    @PatchMapping("/update/{userNum}")
    public ResponseDto<UserEntity> update(@PathVariable int userNum, @RequestBody SignUpDto dto)
    {
        ResponseDto<UserEntity> result = userService.update(userNum, dto);
        return result;
    }

    @DeleteMapping("/delete/{userNum}")
    public ResponseDto<?> delete(@PathVariable int userNum)
    {
        ResponseDto<?> result = userService.delete(userNum);
        return result;
    }

    @GetMapping("/signIn")
    public ResponseDto<?> signIn(@RequestBody SignInDto dto)
    {
        ResponseDto<?> result = userService.signIn(dto);
        return result;
    }
}
