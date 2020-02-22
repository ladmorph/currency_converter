package com.example.currencyConverter.controllers;

import com.example.currencyConverter.dao.UserRepository;
import com.example.currencyConverter.entities.Role;
import com.example.currencyConverter.entities.User;
import com.example.currencyConverter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String showRegistrationForm(@ModelAttribute("user") User user, Model model) {
        if (userService.checkUserByUsername(user.getUsername())) { // checking the presence of a user in the database
            model.addAttribute("error", true);
            return "registration";
        } else {
            user.setRoles(Collections.singleton(Role.USER));
            userRepository.save(user);
            return "redirect:/login";
        }
    }
}
