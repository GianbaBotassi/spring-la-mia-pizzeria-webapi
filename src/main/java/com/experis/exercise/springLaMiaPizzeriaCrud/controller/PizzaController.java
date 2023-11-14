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

    //Injection del repository
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("/menu")
    public String index(@RequestParam Optional<String> search, Model model) {
        //prendo l'oggetto optional dalla barra search
        List<Pizza> result;
        //Condizione se vuoto search filtro elementi altrimenti mostro tutti
        if (search.isPresent()) {
            result = pizzaRepository.findByNameContainingIgnoreCase(search.get());
        } else {
            result = pizzaRepository.findAll();
        }
        //Passo in model i risultati
        model.addAttribute("menu", result);
        return "pizzas/menu";
    }

    @GetMapping("show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        //prendo id da url con PathVariable e poi controllo se pizza esiste
        Optional<Pizza> pizza = pizzaRepository.findById(id);
        if (pizza.isPresent()) {
            //Se esiste mostro i suoi dati passandoli in un model
            model.addAttribute("pizza", pizza.get());
            return "pizzas/show";
        } else {
            //Altrimenti lancio un'eccezione pizza non trovata
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza non trovata");
        }
    }

    @GetMapping("/create")
    public String create(Model model) {
        //Creo un'istanza di pizza vuota e la metto nel model
        model.addAttribute("pizza", new Pizza());
        return "pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult) {
        //Recupero tramite ModelAttribute la pizza e controllo validazione
        //Ricorda di mettere nell'ordine corretto le annotation e BindingResult
        if (bindingResult.hasErrors()) {
            return "/pizzas/create";
        }
        //Se non ha errori la salvo nel DB
        pizzaRepository.save(formPizza);
        return "redirect:/pizzas/menu";
    }

    //Edit
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        //Recupero l'id con PathVariable e se l'id è presente lo inserisco nel model
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
        //Recupero l'id e i dati inviati per la modifica e controllo la validazione
        if (bindingResult.hasErrors()) {
            return "/pizzas/edit";
        }
        pizzaRepository.save(formPizza);
        return "redirect:/pizzas/menu";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        //Recupero id e tramite repository lo elimino
        pizzaRepository.deleteById(id);
        return "redirect:/pizzas/menu";
    }

}
