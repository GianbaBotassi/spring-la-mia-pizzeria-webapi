package com.experis.exercise.springLaMiaPizzeriaCrud.security;

import com.experis.exercise.springLaMiaPizzeriaCrud.model.Ruolo;
import com.experis.exercise.springLaMiaPizzeriaCrud.model.Utente;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DatabaseUtenteDetails implements UserDetails {

    private Integer Id;
    private String username;
    private String password;
    private Set<GrantedAuthority> authorities = new HashSet<>();

    public DatabaseUtenteDetails(Utente utente) {
        Id = utente.getId();
        this.username = utente.getEmail();
        this.password = utente.getPassword();

        for (Ruolo role : utente.getRuoli()
        ) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
