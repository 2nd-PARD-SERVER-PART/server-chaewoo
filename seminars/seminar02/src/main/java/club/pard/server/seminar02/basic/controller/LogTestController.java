package club.pard.server.seminar02.basic.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController = @Controller + @ResponseBody. Can send strings and json files without @Respondboy or without view.
@Slf4j
@RestController
public class LogTestController {
    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Hans";
        System.out.println("name = " + name);
        log.info("info log = {}", name);
        log.trace("Trace log = {}", name);
        log.debug("Debug log = {}", name);
        log.warn("Warn log = {}", name);
        log.error("Error log = {}", name);
        log.info("Wrong log = " + name); // Plus operator uses extra/extravagant memory!

        return name;
    }
}
