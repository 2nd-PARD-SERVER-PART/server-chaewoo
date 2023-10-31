package club.pard.server.seminar02.basic.controller;

import club.pard.server.seminar02.basic.dto.HelloDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class Path {
    @GetMapping("mapping/{userId}")
    public String mappingPath(@PathVariable String userId) { // same as `@PathVariable("userId") String data`
        log.info("mapping userId = {}", userId);
        return "userId = " + userId;
    }

    @GetMapping("mapping/users/{userId}/age/{age}")
    public String mappingPath(@PathVariable String userId, @PathVariable(required = false) Integer age) {
        if(age != null)  log.info("mapping userId = {}, age = {}", userId, age);
        else log.info("mapping userId = {}, age = null", userId);
        return "userId = " + userId + ", age = " + age;
    }
    @GetMapping("requestParam/users")
    public String mappingParam(@RequestParam(defaultValue = "NAME") String name, @RequestParam Integer age) {
        log.info("mapping name = {}, age = {}", name, age);
        return "name = "+ name + ", age = " + age;

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST) // Default value set as 200 (OK)
    @PostMapping("requestBody")
    public String requestBody(@RequestBody HelloDto helloDTO) {
        log.info("RequestBody name = {}, age = {}", helloDTO.getUserName(), helloDTO.getAge());
        return "name = " + helloDTO.getUserName() +", age = " + helloDTO.getAge();
    }
}
