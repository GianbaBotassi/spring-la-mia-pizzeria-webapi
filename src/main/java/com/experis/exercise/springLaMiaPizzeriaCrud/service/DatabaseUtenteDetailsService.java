package com.experis.exercise.springLaMiaPizzeriaCrud.service;

import com.experis.exercise.springLaMiaPizzeriaCrud.model.Utente;
import com.experis.exercise.springLaMiaPizzeriaCrud.repository.UtenteRepository;
import com.experis.exercise.springLaMiaPizzeriaCrud.security.DatabaseUtenteDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class DatabaseUtenteDetailsService implements UserDetailsService {
    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Utente> utente = utenteRepository.findByEmail(username);

        if (utente.isPresent()) {
            return new DatabaseUtenteDetails(utente.get());
        } else {
            throw new UsernameNotFoundException("Utente non trovato");
        }

    }
}
