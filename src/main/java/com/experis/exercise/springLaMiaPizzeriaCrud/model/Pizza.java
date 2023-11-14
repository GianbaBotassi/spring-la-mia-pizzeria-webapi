package com.experis.exercise.springLaMiaPizzeriaCrud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Il nome non può essere vuoto")
    @Size(max = 64)
    private String name;
    @NotBlank(message = "La descrizione non può essere vuota")
    @Size(max = 255)
    private String description;
    @NotBlank(message = "L url dell immagine non può essere vuoto")
    @Size(max = 255)
    private String picture;
    @NotNull(message = "Non deve essere vuoto")
    @DecimalMin(value = "0.01", message = "Il prezzo non può essere inferiore a 0,01 €")
    private BigDecimal price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
