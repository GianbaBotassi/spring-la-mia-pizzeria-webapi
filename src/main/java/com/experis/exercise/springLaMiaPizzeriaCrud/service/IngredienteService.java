package com.experis.exercise.springLaMiaPizzeriaCrud.service;

import com.experis.exercise.springLaMiaPizzeriaCrud.exceptions.UniqueNameIngrediente;
import com.experis.exercise.springLaMiaPizzeriaCrud.model.Ingrediente;
import com.experis.exercise.springLaMiaPizzeriaCrud.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    public List<Ingrediente> getIngrList() {
        return ingredienteRepository.findAll();
    }

    public Ingrediente saveIngr(Ingrediente ingrediente) {

        if (ingredienteRepository.existsByName(ingrediente.getName())) {
            throw new UniqueNameIngrediente("Ingrediente già presente");
        }
        return ingredienteRepository.save(ingrediente);
    }

    public void deleteIngr(Integer id) {
        ingredienteRepository.deleteById(id);
    }
}
