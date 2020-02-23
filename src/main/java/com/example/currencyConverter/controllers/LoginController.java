package com.example.currencyConverter.controllers;

import com.example.currencyConverter.entities.Role;
import com.example.currencyConverter.entities.User;
import com.example.currencyConverter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/login", "/"})
    public String login() {

        User user = new User()
                .setUsername("admin")
                .setPassword("admin")
                .setRoles(Collections.singleton(Role.ADMIN));
        if (!userService.checkUserByUsername(user.getUsername())) {
            userService.save(user);
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(WebRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (userService.checkUserByUsernameAndPassword(username, password)) {
            return "redirect:/converter"; // // if the user does not exist
        } else {
            return "registration"; // if a user exist
        }
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("error", true);
        return "login";
    }

}
