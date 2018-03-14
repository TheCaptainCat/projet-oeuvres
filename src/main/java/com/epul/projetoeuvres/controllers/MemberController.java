package com.epul.projetoeuvres.controllers;

import com.epul.projetoeuvres.persistence.Service;
import com.epul.projetoeuvres.persistence.entities.AdherentEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {
    @RequestMapping("/members")
    public String getAll(Model model) {
        model.addAttribute("members", new Service().consulterListeAdherents());
        return "pages/members/members-list";
    }

    @RequestMapping("/members-create")
    public String add(Model model) {
        return "pages/members/members-form";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/members-store")
    public String store(@RequestParam String firstname, @RequestParam String lastname, @RequestParam String city) {

        new Service().insertAdherent(new AdherentEntity(firstname, lastname, city));
        return "pages/members/members-list";
    }
}
