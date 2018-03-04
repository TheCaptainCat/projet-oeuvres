package com.epul.projetoeuvres.controllers;

import com.epul.projetoeuvres.models.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
    @RequestMapping("/members")
    public String greeting(Model model) {
        model.addAttribute("members", Member.queryMembers());
        return "pages/members/members-list";
    }
}
