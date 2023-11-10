package com.experis.exercise.springLaMiaPizzeriaCrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //Redirect to Home quando l'url è solo localhost
    @GetMapping("/")
    public String redirectHome() {
        return "redirect:/home";
    }

    //template home con url home
    @GetMapping("/home")
    public String home() {
        return "home";
    }

}
