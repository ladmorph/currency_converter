package com.example.currencyConverter.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HistoryOfCurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String sourceCurrency;

    private String targetCurrency;

    private String sourceAmount;

    private String targetAmount;

    private String date;

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public HistoryOfCurrency setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
        return this;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public HistoryOfCurrency setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
        return this;
    }

    public String getSourceAmount() {
        return sourceAmount;
    }

    public HistoryOfCurrency setSourceAmount(String sourceAmount) {
        this.sourceAmount = sourceAmount;
        return this;
    }

    public String getTargetAmount() {
        return targetAmount;
    }

    public HistoryOfCurrency setTargetAmount(String targetAmount) {
        this.targetAmount = targetAmount;
        return this;
    }

    public String getDate() {
        return date;
    }

    public HistoryOfCurrency setDate(String date) {
        this.date = date;
        return this;
    }
}
