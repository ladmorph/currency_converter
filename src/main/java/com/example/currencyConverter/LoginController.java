package com.example.currencyConverter;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


    @GetMapping("/hello")
    public String greetings() {
        return "loginForm";
    }

}
