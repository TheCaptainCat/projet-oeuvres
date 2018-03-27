package com.cgp.projetoeuvres.controller;

import com.cgp.projetoeuvres.entity.Owner;
import com.cgp.projetoeuvres.entity.WorkForSale;
import com.cgp.projetoeuvres.exception.ResourceNotFoundException;
import com.cgp.projetoeuvres.repository.OwnerRepository;
import com.cgp.projetoeuvres.repository.WorkForSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/worksforsale")
public class WorkForSaleController {

    private WorkForSaleRepository workForSaleRepository;
    private OwnerRepository ownerRepository;

    @Autowired
    public WorkForSaleController(WorkForSaleRepository workForSaleRepository, OwnerRepository ownerRepository) {
        this.workForSaleRepository = workForSaleRepository;
        this.ownerRepository = ownerRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String findAllWorksForSale(Model model){
        model.addAttribute("worksforsale", workForSaleRepository.findAll());
        return "workforsale/workforsale-list";
    }

    @RequestMapping(method = RequestMethod.GET, value="/all")
    public @ResponseBody
    List<WorkForSale> getAll(){
        return workForSaleRepository.findAll();
    }

    @RequestMapping("/add")
    public String displayAddForm(Model model){
        model.addAttribute("owners", ownerRepository.findAll());
        return "workforsale/workforsale-form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody WorkForSale addWorkForSale(@RequestParam String title, @RequestParam double price, @RequestParam String state, @RequestParam String ownerId){
        Owner owner = ownerRepository.findById(Integer.valueOf(ownerId)) .orElseThrow(() -> new ResourceNotFoundException("Owner", "id", ownerId));

        WorkForSale workForSale = new WorkForSale();
        workForSale.setTitle(title);
        workForSale.setPrice(price);
        workForSale.setState(state);
        workForSale.setOwner(owner);

        return workForSaleRepository.save(workForSale);
    }

    @RequestMapping(method= RequestMethod.PUT, value="/{id}")
    public @ResponseBody WorkForSale updateWorkForSale(@PathVariable(value = "id") String id, @RequestParam String title, @RequestParam double price, @RequestParam String state, @RequestParam String ownerId){
        WorkForSale workForSale = workForSaleRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("WorkForSale", "id", id));

        Owner owner = ownerRepository.findById(Integer.valueOf(ownerId)) .orElseThrow(() -> new ResourceNotFoundException("Owner", "id", ownerId));

        workForSale.setTitle(title);
        workForSale.setPrice(price);
        workForSale.setState(state);
        workForSale.setOwner(owner);

        return workForSaleRepository.save(workForSale);
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/{id}")
    public @ResponseBody WorkForSale deleteWorkForSale(@PathVariable(value = "id") String id){
        WorkForSale workForSale = workForSaleRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("WorkForSale", "id", id));
        workForSaleRepository.delete(workForSale);

        return workForSale;
    }
}