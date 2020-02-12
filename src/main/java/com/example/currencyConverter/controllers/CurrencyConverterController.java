package com.example.currencyConverter.controllers;

import com.example.currencyConverter.dao.CurrencyRepository;
import com.example.currencyConverter.dao.HistoryOfCurrencyRepository;
import com.example.currencyConverter.entities.Currency;
import com.example.currencyConverter.entities.HistoryOfCurrency;
import com.example.currencyConverter.utils.CurrencyXmlHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.text.DecimalFormat;
import java.time.LocalDate;

@Controller
public class CurrencyConverterController {


    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private HistoryOfCurrencyRepository historyOfCurrencyRepository;

    @GetMapping("/converter")
    public String getConverter(Model modelMap) {
        if (!currencyRepository.findAll().isEmpty()) {
            if (currencyRepository.findAll().get(0).getDate() != LocalDate.now().toString()) {
                addCurrencies();
            }
        } else {
            addCurrencies();
        }

        modelMap.addAttribute("currencies", currencyRepository.findAll());
        return "converter";
    }

    @PostMapping("converterHandler")
    public ModelAndView converterHandler(@RequestParam("toValute") String toValute,
                                         @RequestParam("currencyValue") Integer amount) {

        Currency currency = currencyRepository.getByName(toValute);

        double targetAmountTemp = amount / Double.valueOf(currency.getValue().replace(",", "."));
        String targetAmount = new DecimalFormat("##0.00").format(targetAmountTemp);

        HistoryOfCurrency historyOfCurrency = new HistoryOfCurrency()
                .setSourceCurrency("RUB")
                .setTargetCurrency(currency.getCharCode())
                .setSourceAmount(String.valueOf(amount))
                .setTargetAmount(targetAmount)
                .setDate(currency.getDate());

        historyOfCurrencyRepository.save(historyOfCurrency);

        return new ModelAndView(new RedirectView("/history"));
    }

    public void addCurrencies() {
        Iterable<Currency> iterable = new CurrencyXmlHandler().parse().getCurrencies();
        currencyRepository.saveAll(iterable);
    }

}
