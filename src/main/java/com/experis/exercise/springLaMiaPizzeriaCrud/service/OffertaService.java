package com.experis.exercise.springLaMiaPizzeriaCrud.service;

import com.experis.exercise.springLaMiaPizzeriaCrud.exceptions.NotFoundOffertaException;
import com.experis.exercise.springLaMiaPizzeriaCrud.exceptions.NotFoundPizzaException;
import com.experis.exercise.springLaMiaPizzeriaCrud.model.Offerta;
import com.experis.exercise.springLaMiaPizzeriaCrud.model.Pizza;
import com.experis.exercise.springLaMiaPizzeriaCrud.repository.OffertaRepository;
import com.experis.exercise.springLaMiaPizzeriaCrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OffertaService {

    @Autowired
    private OffertaRepository offertaRepository;
    @Autowired
    private PizzaRepository pizzaRepository;

    public Offerta createOffer(Integer id) {
        Pizza pizza = pizzaRepository.findById(id).orElseThrow(() -> new NotFoundPizzaException("Pizza non trovata"));
        Offerta offerta = new Offerta();
        offerta.setStartDate(LocalDate.now());
        offerta.setPizza(pizza);
        return offerta;
    }

    public Offerta saveOffer(Offerta offerta) {
        return offertaRepository.save(offerta);
    }

    public Offerta getOffer(Integer id) {
        return offertaRepository.findById(id).orElseThrow(() -> new NotFoundOffertaException("Offerta non trovata"));
    }

    public void deleteOffer(Integer id) {
        offertaRepository.deleteById(id);
    }


}
