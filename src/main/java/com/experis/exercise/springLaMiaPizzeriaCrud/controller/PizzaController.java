package com.experis.exercise.springLaMiaPizzeriaCrud.controller;

import com.experis.exercise.springLaMiaPizzeriaCrud.model.Pizza;
import com.experis.exercise.springLaMiaPizzeriaCrud.repository.PizzaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("/menu")
    public String index(@RequestParam Optional<String> search, Model model) {
        List<Pizza> result;
        if (search.isPresent()) {
            result = pizzaRepository.findByNameContainingIgnoreCase(search.get());
        } else {
            result = pizzaRepository.findAll();
        }
        model.addAttribute("menu", result);
        return "pizzas/menu";
    }

    @GetMapping("show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Pizza> pizza = pizzaRepository.findById(id);
        if (pizza.isPresent()) {
            model.addAttribute("pizza", pizza.get());
            return "pizzas/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza non trovata");
        }
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/pizzas/create";
        }

        pizzaRepository.save(formPizza);

        return "redirect:/pizzas/menu";
    }


    //Edit
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("pizza", result.get());
            return "/pizzas/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza non trovata");
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/pizzas/edit";
        }
        pizzaRepository.save(formPizza);
        return "redirect:/pizzas/menu";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        pizzaRepository.deleteById(id);
        return "redirect:/pizzas/menu";
    }

}
