package com.example.currencyConverter.controllers;


import com.example.currencyConverter.dao.CurrencyRepository;
import com.example.currencyConverter.dao.HistoryOfCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HistoryOfCurrencyController {

    @Autowired
    private HistoryOfCurrencyRepository repository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @GetMapping("/history")
    public String getHistory(Model modelMap, @RequestParam(name = "calendar", required = false) String date,
                             @RequestParam(name = "targetCurrency", required = false) String targetCurency) {


        if (date != null) {
            modelMap.addAttribute("history", repository.findAllByDate(date));
            modelMap.addAttribute("currencies", currencyRepository.findAll());
        }
        if (date != null && targetCurency != null) {
            System.out.println(date);
            modelMap.addAttribute("history", repository.findAllByDateAndTargetCurrency(date, targetCurency));
            modelMap.addAttribute("currencies", currencyRepository.findAll());
        }
        if (targetCurency != null) {
            modelMap.addAttribute("history", repository.findAllByTargetCurrency(targetCurency));
            modelMap.addAttribute("currencies", currencyRepository.findAll());
        } else {
            modelMap.addAttribute("history", repository.findAll());
            modelMap.addAttribute("currencies", currencyRepository.findAll());
        }

        return "history";
    }
}


