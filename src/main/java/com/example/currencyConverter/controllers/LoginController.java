package com.example.currencyConverter.controllers;

import com.example.currencyConverter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;


@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping(value = {"/login", "/"})
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @PostMapping("/login")
    public String login(WebRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(userService.checkUserByUsernameAndPassword(username, password)) {
            return "redirect:/converter";
        } else {
            return "login";
        }
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("error", true);
        return "login";
    }

}
