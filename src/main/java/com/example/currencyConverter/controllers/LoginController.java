package com.example.currencyConverter.controllers;


import com.example.currencyConverter.entities.User;
import com.example.currencyConverter.services.UserService;
import com.example.currencyConverter.services.impl.UserServiceImpl;
import com.example.currencyConverter.utils.CurrencyXmlHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    UserService userService;


    @GetMapping("/login")
    public String greetings() {
        return "loginForm";
    }

    @PostMapping(value = "/loginHandler")
    public ModelAndView handleLoginRequest(@ModelAttribute("loginForm") @Valid User loginForm, ModelMap model) {

        if (userService.checkUserByUsernameAndPassword(loginForm.getLogin(), loginForm.getPassword())) {
            return new ModelAndView(new RedirectView("/login"));
        } else {
            return new ModelAndView(new RedirectView("/converter"));
        }
    }

}
