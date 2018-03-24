package com.cgp.projetoeuvres.controller;

import com.cgp.projetoeuvres.entity.Adherent;
import com.cgp.projetoeuvres.entity.Owner;
import com.cgp.projetoeuvres.exception.ResourceNotFoundException;
import com.cgp.projetoeuvres.repository.AdherentRepository;
import com.cgp.projetoeuvres.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private OwnerRepository ownerRepository;

    @Autowired
    public OwnerController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String displayOwnerList(Model model){
        model.addAttribute("owners", ownerRepository.findAll());
        return "owner/owner-list";
    }

    @RequestMapping(method = RequestMethod.GET, value="/all")
    public @ResponseBody List<Owner> getAll(){
        return ownerRepository.findAll();
    }

    @RequestMapping("/add")
    public String displayAddForm(){
        return "owner/owner-form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Owner addOwner(@RequestParam String firstname, @RequestParam String surname){
        Owner owner = new Owner();
        owner.setSurname(surname);
        owner.setFirstname(firstname);

        return ownerRepository.save(owner);
    }

    @RequestMapping(method= RequestMethod.PUT, value="/{id}")
    public @ResponseBody Owner updateOwner(@PathVariable(value = "id") String id, @RequestParam String firstname, @RequestParam String surname){
        Owner owner = ownerRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Owner", "id", id));

        owner.setSurname(surname);
        owner.setFirstname(firstname);

        return ownerRepository.save(owner);
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/{id}")
    public @ResponseBody Owner deleteOwner(@PathVariable(value = "id") String id){
        Owner owner = ownerRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Owner", "id", id));
        ownerRepository.delete(owner);

        return owner;
    }
}