package com.experis.exercise.springLaMiaPizzeriaCrud.controller;

import com.experis.exercise.springLaMiaPizzeriaCrud.model.Offerta;
import com.experis.exercise.springLaMiaPizzeriaCrud.repository.OffertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/offerte")
public class OffertaController {
    @Autowired
    private OffertaRepository offertaRepository;

    @GetMapping("/create")
    public String create(@RequestParam Integer id, Model model) {
        Offerta offerta = new Offerta();
        model.addAttribute("offerta", offerta);
        return "/offerte/form";
    }


}
