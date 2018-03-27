package com.cgp.projetoeuvres.controller;

import com.cgp.projetoeuvres.entity.Adherent;
import com.cgp.projetoeuvres.entity.Owner;
import com.cgp.projetoeuvres.exception.ResourceNotFoundException;
import com.cgp.projetoeuvres.repository.AdherentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/adherents")
public class AdherentController {

    private AdherentRepository adherentRepository;

    @Autowired
    public AdherentController(AdherentRepository adherentRepository) {
        this.adherentRepository = adherentRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String findAllAdherents(Model model){
        model.addAttribute("adherents", adherentRepository.findAll());
        return "adherent/adherent-list";
    }

    @RequestMapping(method = RequestMethod.GET, value="/all")
    public @ResponseBody
    List<Adherent> getAll(){
        return adherentRepository.findAll();
    }

    @RequestMapping("/add")
    public String displayAddForm(){
        return "adherent/adherent-form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Adherent addAdherent(@RequestParam String firstname, @RequestParam String surname, @RequestParam String city){
        Adherent adherent = new Adherent();
        adherent.setSurname(surname);
        adherent.setFirstname(firstname);
        adherent.setCity(city);

        return adherentRepository.save(adherent);
    }

    @RequestMapping(method= RequestMethod.PUT, value="/{id}")
    public @ResponseBody Adherent updateAdherent(@PathVariable(value = "id") String id, @RequestParam String firstname, @RequestParam String surname, @RequestParam String city){
        Adherent adherent = adherentRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Adherent", "id", id));

        adherent.setSurname(surname);
        adherent.setFirstname(firstname);
        adherent.setCity(city);

        return adherentRepository.save(adherent);
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/{id}")
    public @ResponseBody Adherent deleteAdherent(@PathVariable(value = "id") String id){
        Adherent adherent = adherentRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Adherent", "id", id));
        adherentRepository.delete(adherent);

        return adherent;
    }
}