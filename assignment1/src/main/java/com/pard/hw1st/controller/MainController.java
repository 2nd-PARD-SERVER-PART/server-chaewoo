package com.pard.hw1st.controller;


// TODO: MainController를 만들어주세요.

import com.pard.hw1st.dto.MyInfoDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/start")
    public String startPage() { return "start"; }


    @PostMapping("/showInfo")
    public String showForm(MyInfoDto dto, Model model) {
        model.addAttribute("major", dto.getMajor());
        model.addAttribute("grade", dto.getGrade());
        model.addAttribute("age", dto.getAge());
        model.addAttribute("hobby", dto.getHobby());
        model.addAttribute("name", dto.getName());
        model.addAttribute("hometown", dto.getHometown());
        model.addAttribute("introduction", dto.getIntroduction());

        return "showInfo";
    }

    @GetMapping("/submitInfo")
    public String newInfoForm() {
        return "submitInfo";
    }

}
