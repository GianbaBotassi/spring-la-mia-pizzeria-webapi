package com.experis.exercise.springLaMiaPizzeriaCrud.repository;

import com.experis.exercise.springLaMiaPizzeriaCrud.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
    boolean existsByName(String keyword);
}
