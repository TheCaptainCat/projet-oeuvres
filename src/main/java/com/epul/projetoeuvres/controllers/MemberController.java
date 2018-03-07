package com.epul.projetoeuvres.controllers;

import com.epul.projetoeuvres.persistence.Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
    @RequestMapping("/members")
    public String greeting(Model model) {
        model.addAttribute("members", new Service().consulterListeAdherents());
        return "pages/members/members-list";
    }
}
