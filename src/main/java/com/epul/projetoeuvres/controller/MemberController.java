package com.epul.projetoeuvres.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
    @RequestMapping("/members")
    public String greeting() {
        return "pages/members/members-list";
    }
}
