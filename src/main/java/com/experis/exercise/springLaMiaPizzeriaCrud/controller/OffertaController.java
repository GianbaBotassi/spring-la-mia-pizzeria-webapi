package com.experis.exercise.springLaMiaPizzeriaCrud.controller;

import com.experis.exercise.springLaMiaPizzeriaCrud.exceptions.NotFoundOffertaException;
import com.experis.exercise.springLaMiaPizzeriaCrud.exceptions.NotFoundPizzaException;
import com.experis.exercise.springLaMiaPizzeriaCrud.model.Offerta;
import com.experis.exercise.springLaMiaPizzeriaCrud.service.OffertaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/offerte")
public class OffertaController {
    @Autowired
    private OffertaService offertaService;

    @GetMapping("/create")
    public String create(@RequestParam Integer id, Model model) {
        try {
            model.addAttribute("offerta", offertaService.createOffer(id));
            return "/offerte/form";
        } catch (NotFoundPizzaException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("offerta") Offerta formOfferta, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "offerte/form";
        }
        Offerta offerSaved = offertaService.saveOffer(formOfferta);
        return "redirect:/pizzas/show/" + offerSaved.getPizza().getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        try {
            model.addAttribute("offerta", offertaService.getOffer(id));
            return "offerte/form";
        } catch (NotFoundOffertaException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("offerta") Offerta formOfferta, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "offerte/form";
        }
        Offerta offerSaved = offertaService.saveOffer(formOfferta);
        return "redirect:/pizzas/show/" + offerSaved.getPizza().getId();
    }

}
