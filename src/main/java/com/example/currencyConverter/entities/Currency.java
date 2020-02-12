package com.example.currencyConverter.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Currency {

    @Id
    private String valuteId;

    private Integer numCode;

    private String charCode;

    private Integer nominal;

    private String name;

    private String value;

    private String date;

    public String getValuteId() {
        return valuteId;
    }

    public Currency setValuteId(String valuteId) {
        this.valuteId = valuteId;
        return this;
    }

    public String getCharCode() {
        return charCode;
    }

    public Currency setCharCode(String charCode) {
        this.charCode = charCode;
        return this;
    }

    public Integer getNominal() {
        return nominal;
    }

    public Currency setNominal(Integer nominal) {
        this.nominal = nominal;
        return this;
    }

    public String getName() {
        return name;
    }

    public Currency setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Currency setValue(String value) {
        this.value = value;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Currency setDate(String date) {
        this.date = date;
        return this;
    }

    public Integer getNumCode() {
        return numCode;
    }

    public Currency setNumCode(Integer numCode) {
        this.numCode = numCode;
        return this;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "valuteId='" + valuteId + '\'' +
                ", numCode=" + numCode +
                ", charCode='" + charCode + '\'' +
                ", nominal=" + nominal +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
