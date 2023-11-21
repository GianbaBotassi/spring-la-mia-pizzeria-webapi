package com.experis.exercise.springLaMiaPizzeriaCrud.controller;

import com.experis.exercise.springLaMiaPizzeriaCrud.exceptions.NotFoundPizzaException;
import com.experis.exercise.springLaMiaPizzeriaCrud.model.Pizza;
import com.experis.exercise.springLaMiaPizzeriaCrud.service.IngredienteService;
import com.experis.exercise.springLaMiaPizzeriaCrud.service.OffertaService;
import com.experis.exercise.springLaMiaPizzeriaCrud.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    //Injection del repository

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private OffertaService offertaService;

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping("/menu")
    public String index(@RequestParam Optional<String> search, Model model, Authentication authentication) {
        //prendo l'oggetto optional dalla barra search
        model.addAttribute("menu", pizzaService.getList(search));
        model.addAttribute("utente", authentication.getPrincipal());
        return "pizzas/menu";
    }

    @GetMapping("show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        //prendo id da url con PathVariable e poi controllo se pizza esiste
        try {
            model.addAttribute("pizza", pizzaService.getPizzaFromId(id));
        } catch (NotFoundPizzaException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return "pizzas/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        //Creo un'istanza di pizza vuota e la metto nel model
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredienti", ingredienteService.getIngrList());
        return "pizzas/form";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        //Recupero tramite ModelAttribute la pizza e controllo validazione
        //Ricorda di mettere nell'ordine corretto le annotation e BindingResult
        if (bindingResult.hasErrors()) {
            return "/pizzas/form";
        }
        //Se non ha errori la salvo nel DB
        pizzaService.savePizza(formPizza);
        redirectAttributes.addFlashAttribute("message", "Hai aggiunto la pizza " + formPizza.getName());
        return "redirect:/pizzas/menu";
    }

    //Edit
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        //Recupero l'id con PathVariable e se l'id è presente lo inserisco nel model
        try {
            model.addAttribute("pizza", pizzaService.getPizzaFromId(id));
            model.addAttribute("ingredienti", ingredienteService.getIngrList());
        } catch (NotFoundPizzaException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return "/pizzas/form";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        //Recupero l'id e i dati inviati per la modifica e controllo la validazione
        if (bindingResult.hasErrors()) {
            return "/pizzas/form";
        }
        pizzaService.savePizza(formPizza);
        redirectAttributes.addFlashAttribute("message", "Hai modificato la pizza " + formPizza.getName());
        return "redirect:/pizzas/menu";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {

        try {
            Pizza pizzaMessage = pizzaService.getPizzaFromId(id);
            pizzaService.deletePizza(id);
            redirectAttributes.addFlashAttribute("message", "Hai eliminato la pizza " + pizzaMessage.getName());
        } catch (NotFoundPizzaException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return "redirect:/pizzas/menu";
    }
}
