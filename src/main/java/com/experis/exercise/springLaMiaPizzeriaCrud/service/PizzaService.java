package com.experis.exercise.springLaMiaPizzeriaCrud.service;

import com.experis.exercise.springLaMiaPizzeriaCrud.exceptions.NotFoundPizzaException;
import com.experis.exercise.springLaMiaPizzeriaCrud.model.Pizza;
import com.experis.exercise.springLaMiaPizzeriaCrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    public List<Pizza> getList(Optional<String> search) {
        if (search.isPresent()) {
            return pizzaRepository.findByNameContainingIgnoreCase(search.get());
        }
        return pizzaRepository.findAll();
    }

    public List<Pizza> getPizzasList() {
        return pizzaRepository.findAll();
    }

    public Pizza getPizzaFromId(Integer id) throws NotFoundPizzaException {
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundPizzaException("Pizza non trovata");
        }
        return result.get();
    }

    public Pizza pizzaEdit(Pizza pizza) {
        Pizza pizzaEdit = getPizzaFromId(pizza.getId());
        pizzaEdit.setName(pizza.getName());
        pizzaEdit.setDescription(pizza.getDescription());
        pizzaEdit.setPicture(pizza.getPicture());
        pizzaEdit.setPrice(pizza.getPrice());
        pizzaEdit.setIngredList(pizza.getIngredList());

        return pizzaRepository.save(pizzaEdit);
    }

    public void savePizza(Pizza pizza) {
        pizzaRepository.save(pizza);
    }

    public void deletePizza(Integer id) {
        pizzaRepository.deleteById(id);
    }
}
