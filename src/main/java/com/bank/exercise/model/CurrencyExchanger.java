package com.bank.exercise.model;


import com.bank.exercise.dto.CurrencyDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@AllArgsConstructor
public class CurrencyExchanger {

    private NbpHttpClient nbpHttpClient;


    public BigDecimal convert(Account accountUSD, Account accountPLN, BigDecimal amount, Currency convertTo) {
        BigDecimal convertedAmount = calculateAmount(amount, convertTo);
        switch (convertTo) {
            case PLN:
                if (accountUSD.withdrawAmount(amount)) {
                    accountPLN.addAmount(convertedAmount);
                    return convertedAmount;
                }
                break;
            case USD:
                if (accountPLN.withdrawAmount(amount)) {
                    accountUSD.addAmount(convertedAmount);
                    return convertedAmount;
                }
                break;
        }
        return BigDecimal.ZERO;
    }

    private BigDecimal calculateAmount(BigDecimal amount, Currency convertTo) {
        CurrencyDTO nbpRates = nbpHttpClient.getNbpRates();
        return convertTo.equals(Currency.PLN) ?
                amount.multiply(BigDecimal.valueOf(Double.parseDouble(nbpRates.getRates().get(0).bid))).setScale(2, RoundingMode.DOWN)
                : amount.divide(BigDecimal.valueOf(Double.parseDouble(nbpRates.getRates().get(0).ask)), 2, RoundingMode.DOWN);
    }
}
