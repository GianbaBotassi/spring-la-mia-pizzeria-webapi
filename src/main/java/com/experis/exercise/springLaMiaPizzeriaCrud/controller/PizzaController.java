package com.experis.exercise.springLaMiaPizzeriaCrud.controller;

import com.experis.exercise.springLaMiaPizzeriaCrud.model.Pizza;
import com.experis.exercise.springLaMiaPizzeriaCrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
}
