package com.example.currencyConverter.dao;

import com.example.currencyConverter.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

    Currency getByName(String name);
}
