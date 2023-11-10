package com.experis.exercise.springLaMiaPizzeriaCrud.repository;

import com.experis.exercise.springLaMiaPizzeriaCrud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    List<Pizza> findByNameContainingIgnoreCase(String keyword);
}
