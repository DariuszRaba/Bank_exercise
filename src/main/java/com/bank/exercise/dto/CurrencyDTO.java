package com.bank.exercise.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyDTO {
    @JsonSetter("currency")
    private String name;

    @JsonSetter("code")
    private String currencyCode;

    @JsonSetter("rates")
    private List<RatesDTO> rates;


    public List<RatesDTO> getRates() {
        return rates;
    }

    public void setRates(List<RatesDTO> rates) {
        this.rates = rates;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

}
