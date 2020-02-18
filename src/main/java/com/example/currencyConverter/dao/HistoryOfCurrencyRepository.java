package com.example.currencyConverter.dao;

import com.example.currencyConverter.entities.HistoryOfCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryOfCurrencyRepository extends JpaRepository<HistoryOfCurrency, Integer> {
    List<HistoryOfCurrency> findAllByDate(String date);

    List<HistoryOfCurrency> findAllByDateAndTargetCurrency(String date, String targetCurrency);

    List<HistoryOfCurrency> findAllByTargetCurrency(String targetCurrency);

    List<HistoryOfCurrency> findAllByTargetCurrencyAndSourceCurrencyAndDate(String targetCurrency, String sourceCurrency, String date);
}
