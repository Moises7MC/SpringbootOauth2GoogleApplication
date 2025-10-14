package com.example.springboot_oauth2_google.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal OAuth2User user) {
        if (user != null) {
            System.out.println("Usuario autenticado: " + user.getAttributes());
            model.addAttribute("name", user.getAttribute("name"));
            model.addAttribute("email", user.getAttribute("email"));
            model.addAttribute("picture", user.getAttribute("picture"));
        } else {
            System.out.println("Usuario no autenticado.");
        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}