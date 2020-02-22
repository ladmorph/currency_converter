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
        if (!currencyRepository.findAll().isEmpty()) { // if there are records in the database
            if (currencyRepository.findAll().get(0).getDate() != LocalDate.now().toString()) { // we check for their relevance
                addCurrencies();
            }
        } else {
            addCurrencies();
        }

        modelMap.addAttribute("currencies", currencyRepository.findAll());
        return "converter";
    }


    @PostMapping("converter")
    public ModelAndView converterHandler(@RequestParam("fromValute") String fromValute, @RequestParam("toValute") String toValute,
                                         @RequestParam("currencyValue") Integer amount) {

        if (fromValute.equals(toValute)) {
            return new ModelAndView(new RedirectView("/converter"));
        }

        Currency fromCurrency = currencyRepository.getByName(fromValute);
        Currency toCurrency = currencyRepository.getByName(toValute);

        double result;

        if (fromCurrency.getCharCode().equals("RUB")) {
            // We put the currency in a valid form and then calculate the target currency.
            result = amount /
                    Double.parseDouble(toCurrency.getValue().replace(",", "."));
        } else {
            // The same is true for all other currencies.
            result = (Double.parseDouble(fromCurrency.getValue().replace(",", ".")) /
                    Double.parseDouble(toCurrency.getValue().replace(",", "."))) * amount;
        }

        // We give the double type in 2 characters after the point.
        String targetAmount = new DecimalFormat("##0.00").format(result);

        HistoryOfCurrency historyOfCurrency = new HistoryOfCurrency()
                .setSourceCurrency(fromCurrency.getCharCode())
                .setTargetCurrency(toCurrency.getCharCode())
                .setSourceAmount(String.valueOf(amount))
                .setTargetAmount(targetAmount)
                .setDate(toCurrency.getDate());

        historyOfCurrencyRepository.save(historyOfCurrency);

        return new ModelAndView(new RedirectView("/history"));
    }

    public void addCurrencies() {
        Iterable<Currency> iterable = new CurrencyXmlHandler().parse().getCurrencies();
        currencyRepository.saveAll(iterable);
    }


}
