package com.example.currencyConverter.controllers;

import com.example.currencyConverter.services.UserService;
import com.example.currencyConverter.services.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @PostMapping("/login")
    public String auth(@RequestParam("username") String username, @RequestParam("password") String password) {
        UserService userService = new UserServiceImpl();
        if (userService.checkUserByUsernameAndPassword(username, password))
            return "converter";
        else
            return "loginForm";
    }
}
