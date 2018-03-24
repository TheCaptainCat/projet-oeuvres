package com.cgp.projetoeuvres.controller;

import com.cgp.projetoeuvres.entity.Adherent;
import com.cgp.projetoeuvres.entity.Owner;
import com.cgp.projetoeuvres.entity.WorkForLending;
import com.cgp.projetoeuvres.exception.ResourceNotFoundException;
import com.cgp.projetoeuvres.repository.AdherentRepository;
import com.cgp.projetoeuvres.repository.OwnerRepository;
import com.cgp.projetoeuvres.repository.WorkForLendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/worksforlending")
public class WorkForLendingController {

    private WorkForLendingRepository workForLendingRepository;
    private OwnerRepository ownerRepository;

    @Autowired
    public WorkForLendingController(WorkForLendingRepository workForLendingRepository, OwnerRepository ownerRepository) {
        this.workForLendingRepository = workForLendingRepository;
        this.ownerRepository = ownerRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String findAllWorksForLending(Model model){
        model.addAttribute("worksforlending", workForLendingRepository.findAll());
        return "workforlending/workforlending-list";
    }

    @RequestMapping("/add")
    public String displayAddForm(Model model){
        model.addAttribute("owners", ownerRepository.findAll());
        return "workforlending/workforlending-form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody WorkForLending addWorkForLending(@RequestParam String title, @RequestParam String ownerId){
        Owner owner = ownerRepository.findById(Integer.valueOf(ownerId)) .orElseThrow(() -> new ResourceNotFoundException("Owner", "id", ownerId));

        WorkForLending workForLending = new WorkForLending();
        workForLending.setTitle(title);
        workForLending.setOwner(owner);

        return workForLendingRepository.save(workForLending);
    }

    @RequestMapping(method= RequestMethod.PUT, value="/{id}")
    public @ResponseBody WorkForLending updateWorkForLending(@PathVariable(value = "id") String id, @RequestParam String title, @RequestParam String ownerId){
        WorkForLending workForLending = workForLendingRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("WorkForLending", "id", id));

        Owner owner = ownerRepository.findById(Integer.valueOf(ownerId)) .orElseThrow(() -> new ResourceNotFoundException("Owner", "id", ownerId));

        workForLending.setTitle(title);
        workForLending.setOwner(owner);

        return workForLendingRepository.save(workForLending);
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/{id}")
    public @ResponseBody WorkForLending deleteWorkForLending(@PathVariable(value = "id") String id){
        WorkForLending workForLending = workForLendingRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("WorkForLending", "id", id));
        workForLendingRepository.delete(workForLending);

        return workForLending;
    }
}