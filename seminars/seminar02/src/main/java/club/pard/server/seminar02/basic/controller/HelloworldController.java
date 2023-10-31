package club.pard.server.seminar02.basic.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloworldController {
    @RequestMapping({"/hello-world", "/helloworld", "/hello"})
    public String helloWorld() {
        log.info("Hello-world!");
        return "Hello World!";
    }

}
