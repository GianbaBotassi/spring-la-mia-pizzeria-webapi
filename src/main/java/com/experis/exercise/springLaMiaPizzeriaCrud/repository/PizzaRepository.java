package com.experis.exercise.springLaMiaPizzeriaCrud.repository;

import com.experis.exercise.springLaMiaPizzeriaCrud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
}
