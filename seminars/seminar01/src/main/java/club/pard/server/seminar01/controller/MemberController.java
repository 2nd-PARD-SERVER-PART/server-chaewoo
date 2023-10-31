package club.pard.server.seminar01.controller;

import club.pard.server.seminar01.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // 이 클래스는 컨트롤러의 역할을 할거야! 라고 스프링에게 명시!
// (스프링이 이 클래스를 컨트롤러로 인식할 수 있게 한다.)
@Slf4j
public class MemberController {

    @GetMapping("/members/new")
    public String newMemberForm() {
        return "members/new";
    }

    @PostMapping("/members/create")
    public String createMember(MemberDto dto, Model model) {
        log.info(dto.toString());

        model.addAttribute("myName", dto.getMyName());
        model.addAttribute("myTeam", dto.getTeamName());

        return "members/create";
    }
}