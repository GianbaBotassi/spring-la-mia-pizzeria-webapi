package com.experis.exercise.springLaMiaPizzeriaCrud.controller;

import com.experis.exercise.springLaMiaPizzeriaCrud.exceptions.NotFoundPizzaException;
import com.experis.exercise.springLaMiaPizzeriaCrud.model.Pizza;
import com.experis.exercise.springLaMiaPizzeriaCrud.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/pizzas")
public class RestPizzaController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public List<Pizza> getPizzasList(@RequestParam Optional<String> search) {
        return pizzaService.getList(search);
    }

    @GetMapping("/{id}")
    public Pizza getPizza(@PathVariable Integer id) {
        try {
            return pizzaService.getPizzaFromId(id);
        } catch (NotFoundPizzaException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public void createPizza(@Valid @RequestBody Pizza formPizza) {
        pizzaService.savePizza(formPizza);
    }

    @PutMapping("/{id}")
    public Pizza editPizza(@PathVariable Integer id, @Valid @RequestBody Pizza formPizza) {
        formPizza.setId(id);
        try {
            return pizzaService.pizzaEdit(formPizza);
        } catch (NotFoundPizzaException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletePizza(@PathVariable Integer id) {
        try {
            Pizza pizzaToDelete = pizzaService.getPizzaFromId(id);
            pizzaService.deletePizza(id);
        } catch (NotFoundPizzaException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
