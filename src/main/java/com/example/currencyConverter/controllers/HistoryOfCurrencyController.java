package com.example.currencyConverter.controllers;


import com.example.currencyConverter.dao.CurrencyRepository;
import com.example.currencyConverter.dao.HistoryOfCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HistoryOfCurrencyController {

    @Autowired
    private HistoryOfCurrencyRepository historyOfCurrencyRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @GetMapping("/history")
    public String getHistory(Model modelMap) {

        modelMap.addAttribute("history", historyOfCurrencyRepository.findAll());
        modelMap.addAttribute("currencies", currencyRepository.findAll());

        return "history";
    }

    @PostMapping("/history")
    public String getHistory(Model modelMap,
                             @RequestParam(name = "calendar") String date,
                             @RequestParam(name = "targetCurrency") String targetCurrency,
                             @RequestParam(name = "sourceCurrency") String sourceCurrency) {


        modelMap.addAttribute("history",
                historyOfCurrencyRepository.findAllByTargetCurrencyAndSourceCurrencyAndDate(targetCurrency, sourceCurrency, date));

        modelMap.addAttribute("currencies", currencyRepository.findAll());

        return "history";
    }

}


