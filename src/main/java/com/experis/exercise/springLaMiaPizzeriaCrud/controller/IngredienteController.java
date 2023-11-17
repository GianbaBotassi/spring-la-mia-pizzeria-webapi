package com.experis.exercise.springLaMiaPizzeriaCrud.controller;

import com.experis.exercise.springLaMiaPizzeriaCrud.exceptions.UniqueNameIngrediente;
import com.experis.exercise.springLaMiaPizzeriaCrud.model.Ingrediente;
import com.experis.exercise.springLaMiaPizzeriaCrud.service.IngredienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ingredienti")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("ingredienti", ingredienteService.getIngrList());
        model.addAttribute("ingrediente", new Ingrediente());
        return "ingredienti/index";
    }

    @PostMapping
    public String createIng(@Valid @ModelAttribute("ingrediente") Ingrediente formIngrediente, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredienti", ingredienteService.getIngrList());
            return "ingredienti/index";
        }
        try {
            ingredienteService.saveIngr(formIngrediente);
            return "redirect:/ingredienti";
        } catch (UniqueNameIngrediente e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        ingredienteService.deleteIngr(id);
        model.addAttribute("ingredienti", ingredienteService.getIngrList());
        return "redirect:/ingredienti";
    }


}
